package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.DAO.util.FirebaseNotificationsDAO;
import com.ramon.teste.DAO.util.PedidosPoolDAO;
import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.helpers.StringData;
import com.ramon.teste.model.Usuario;
import com.ramon.teste.model.util.*;

@RestController
@RequestMapping("/pedidospool")
public class PedidosPoolController {
	
	@Autowired
	private PedidosPoolDAO poolDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private StatusPedidoController status;
	
	@GetMapping
	public List<PedidosPool> getPedidosNaoRecebidos()
	{
		return poolDao.findByEnviadoParaRestauranteFalse();
	}
	
	public HttpStatus recebePedido(PedidosMobileRequest pedido,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao)
	{
		try
		{
			PedidosPool p = new PedidosPool();
			p.setDataHora(StringData.getStringData());
			p.setPedido(pedido);
			p.setEnviadoParaRestaurante(false);
			
			Usuario usuario = usuarioDao.findByUsername(pedido.getUserName());
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			FirebaseNotifications mensagem = new FirebaseNotifications();
			
			mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+", seu pedido foi enviado para o Restaurante, em breve avisaremos você sobre o recebimento.");
			mensagem.setTituloMensagem("Pedido Enviado");
			mensagem.setTokenUsuario(usuario.getTokenPushNotification());
			
			firebaseController.enviaNotificacaoFireBase(mensagem,servidorDao,firebaseDao);
			
			if(poolDao.save(p).getId()>0)
				return HttpStatus.OK;
			else
				return HttpStatus.INTERNAL_SERVER_ERROR;
		}catch (Exception e) {
			// TODO: handle exception
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
	}
	
	@PutMapping
	public HttpStatus sinalizaRecebimentoDoPedidoNoRestaurante(@RequestBody PedidosPool pedidoPool)
	{
		PedidosPool p= poolDao.saveAndFlush(pedidoPool);
		if(p.isEnviadoParaRestaurante())
		 {
			status.enviaNotificaoRecebimentoNoRestaurante(pedidoPool.getPedido());
			Usuario usuario = usuarioDao.findByUsername(pedidoPool.getPedido().getUserName());
			FirebaseNotificationsController firebaseController = new FirebaseNotificationsController();
			FirebaseNotifications mensagem = new FirebaseNotifications();
			
			mensagem.setMensagem("Olá "+usuario.getNomeCompleto()+", seu pedido foi enviado para o Restaurante, em breve você avisaremos o recebimento.");
			mensagem.setTituloMensagem("Pedido Enviado");
			mensagem.setTokenUsuario(usuario.getTokenPushNotification());
			
			firebaseController.enviaNotificacaoFireBase(mensagem);
			
			return HttpStatus.OK;
		 }
		else 
		return HttpStatus.BAD_REQUEST;
		
	}
	

}
