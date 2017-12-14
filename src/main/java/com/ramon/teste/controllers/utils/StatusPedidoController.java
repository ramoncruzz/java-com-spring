package com.ramon.teste.controllers.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.DAO.util.StatusPedidoDAO;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.util.PedidosMobileRequest;
import com.ramon.teste.model.util.ServidorConfiguracoes;
import com.ramon.teste.model.util.StatusPedido;
import com.ramon.teste.services.HttpRequests;

@RestController
@RequestMapping("/statuspedido")
public class StatusPedidoController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private StatusPedidoDAO statusDao;
	
	@Autowired
	private ServidorConfiguracoesDAO serverDao;
	
	@GetMapping
	public List<StatusPedido> getTodosStatus()
	{
		return statusDao.findAll();
	}
	
	@PostMapping("/recebimento")
	public HttpStatus enviaNotificaoRecebimentoNoRestaurante(PedidosMobileRequest pedido)
	{
		StatusPedido statusLog = new StatusPedido();
		statusLog.setDataHoraRecebimentoNoRestaurante(" ");
		statusLog.setIdPedido(pedido.getId());
		statusLog.setUsername(pedido.getUserName());
		statusLog.setNumeroPedido(pedido.getNumeroPedido().toString());
		statusDao.save(statusLog);
		
		enviaPushNotification( "Pedido recebido no restaurante", "Aguarte que em breve enviaremos uma mensagem avisando que o entregador está indo até a sua residência.");
		return HttpStatus.OK;
	}
	
	@PostMapping("/entrega")
	public HttpStatus enviaNotificacaoSaidaParaEntrega(Pedido pedido)
	{
		StatusPedido statusLog = statusDao.findByUsernameAndIdPedido(pedido.getUserName(), pedido.getId());
		statusLog.setDataHoraSaidaParaEntrega("");
		statusDao.save(statusLog);
		
		String token = usuarioDao.findByUsername(pedido.getUserName()).getTokenPushNotification();
		enviaPushNotification( "Pedido saiu para entrega", "Nosso entregador já está indo entregar seu pedido. A qualquer momento ele lhe chamará");
		return HttpStatus.OK;
		
	}
	
	private void enviaPushNotification(String titulo, String mensagem)
	{
		ServidorConfiguracoes srv = serverDao.findById(1L);
		String tokenUsuario="fiZnAZtFBys:APA91bGxwT-AAAE8_zurVn85vYXC2nsmnkBQVY3nfnmiUrMITv1y37AfOt6y7p-l6QgvElovUsID0MFOOwsjp3QZ-0ku6PytXGlToHQnyKC3O0Tt1H-k4CDi6790pTHj7CF6-D-9oqlg";
		
		HttpRequests request = new HttpRequests();
		try {
			request.notificaUsuario(srv.getTokenServer(), tokenUsuario, titulo, mensagem);
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (JSONException e) {
	
			e.printStackTrace();
		}
		
	}
	
}
