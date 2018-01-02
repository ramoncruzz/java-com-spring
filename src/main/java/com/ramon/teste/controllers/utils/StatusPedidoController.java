package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.DAO.util.FirebaseNotificationsDAO;
import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.DAO.util.StatusPedidoDAO;
import com.ramon.teste.helpers.StringData;
import com.ramon.teste.model.Usuario;
import com.ramon.teste.model.util.FirebaseNotifications;
import com.ramon.teste.model.util.PedidosMobileRequest;
import com.ramon.teste.model.util.StatusPedido;

@RestController
@RequestMapping("/v0/statuspedido")
public class StatusPedidoController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private StatusPedidoDAO statusDao;
	
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	
	@Autowired 
	private FirebaseNotificationsDAO firebaseDao;
	
	@GetMapping
	public List<StatusPedido> getTodosStatus()
	{
		return statusDao.findAll();
	}
	
	@GetMapping("/numero-pedido-{numeroPedido}")
	public StatusPedido getStatusPorPedido(@PathVariable String numeroPedido)
	{
		StatusPedido status = statusDao.findByNumeroPedido(numeroPedido);
		return status;
	}
	
	@GetMapping("/username-{username}")
	public List<StatusPedido> getStatusPorUsuario(@PathVariable String username)
	{
		List<StatusPedido> status = statusDao.findByUsername(username);
		return status;
	}
	
	public void envaNotificacaoRecebimentoNoServidor(PedidosMobileRequest pedido,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao,Usuario usuario)
	{
		try
		{
			StatusPedido statusLog = new StatusPedido();
			statusLog.setDatahoraEnvioPeloUsuario(StringData.getStringData());
			statusLog.setIdPedido(pedido.getId());
			statusLog.setUsername(pedido.getUserName());
			statusLog.setNumeroPedido(pedido.getNumeroPedido().toString());
			statusDao.saveAndFlush(statusLog);
			
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			FirebaseNotifications mensagem = new FirebaseNotifications();
			
			mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+", seu pedido foi enviado para o Restaurante, em breve avisaremos você sobre o recebimento.");
			mensagem.setTituloMensagem("Pedido Enviado");
			mensagem.setTokenUsuario(usuario.getTokenPushNotification());
			
			firebaseController.enviaNotificacaoFireBase(mensagem,servidorDao,firebaseDao);	
		}catch (Exception e) {}
		
	}
	
	public HttpStatus enviaNotificaoRecebimentoNoRestaurante(PedidosMobileRequest pedido,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao,Usuario usuario)
	{
		try
		{
			String numeroPedido=pedido.getNumeroPedido();
			StatusPedido statusLog = statusDao.findByNumeroPedido(numeroPedido);
			statusLog.setDataHoraRecebimentoNoRestaurante(StringData.getStringData());
			statusLog.setIdPedido(pedido.getId());
			statusLog.setUsername(pedido.getUserName());
			statusLog.setNumeroPedido(pedido.getNumeroPedido().toString());
			statusDao.saveAndFlush(statusLog);
			
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			FirebaseNotifications mensagem = new FirebaseNotifications();
			
			mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+", seu pedido foi recebido no Restaurante Vitória, em breve nosso entregador irá até o endereço informado. Assim que ele sair nós avisaremos.");
			mensagem.setTituloMensagem("Pedido Enviado");
			mensagem.setTokenUsuario(usuario.getTokenPushNotification());
			
			firebaseController.enviaNotificacaoFireBase(mensagem,servidorDao,firebaseDao);
			return HttpStatus.OK;
			
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
	}
	
	public void enviaNotificacaoTodosUsuarios(String titulo,String mensagemTexto)
	{
		try
		{
			List<Usuario>listaTodosUsuarios = usuarioDao.findAll();
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			
			if(listaTodosUsuarios!=null)
			{
				for(Usuario usuario: listaTodosUsuarios)
				{
					FirebaseNotifications mensagem = new FirebaseNotifications();
					
					mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+ ", tudo bem? "+mensagemTexto);
					mensagem.setTituloMensagem(titulo);
					mensagem.setTokenUsuario(usuario.getTokenPushNotification());
					
					firebaseController.enviaNotificacaoFireBase(mensagem,servidorDao,firebaseDao);
				}
			}
			
		}catch (Exception e) {
			
		}
	}
	
	@PostMapping("/saida-entrega")
	public HttpStatus enviaNotificacaoSaidaParaEntrega(@RequestBody String numeroPedido)
	{
		try
		{
			String limpo =numeroPedido.replace("\n", "");
			System.out.println(limpo);
			StatusPedido statusLog = statusDao.findByNumeroPedido(limpo);
			statusLog.setDataHoraSaidaParaEntrega(StringData.getStringData());
			statusDao.save(statusLog);
			
			Usuario usuario = usuarioDao.findByUsername(statusLog.getUsername());
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			FirebaseNotifications mensagem = new FirebaseNotifications();
			
			mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+", Nosso entregador já está indo entregar seu pedido. A qualquer momento ele lhe chamará.");
			mensagem.setTituloMensagem("Pedido saiu para entrega");
			mensagem.setTokenUsuario(usuario.getTokenPushNotification());
			
			firebaseController.enviaNotificacaoFireBase(mensagem,servidorDao,firebaseDao);
		
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		
	}
}
