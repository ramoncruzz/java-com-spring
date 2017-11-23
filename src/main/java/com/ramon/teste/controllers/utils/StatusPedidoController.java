package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.DAO.util.StatusPedidoDAO;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.util.StatusPedido;

@RestController
@RequestMapping("/statuspedido")
public class StatusPedidoController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private StatusPedidoDAO statusDao;
	
	@GetMapping
	public List<StatusPedido> getTodosStatus()
	{
		return statusDao.findAll();
	}
	
	@PostMapping("/recebimento")
	public HttpStatus enviaNotificaoRecebimentoNoRestaurante(Pedido pedido)
	{
		StatusPedido statusLog = new StatusPedido();
		statusLog.setDataHoraRecebimentoNoRestaurante(" ");
		statusLog.setIdPedido(pedido.getId());
		statusLog.setUsername(pedido.getUserName());
		statusLog.setNumeroPedido(pedido.getNumeroPedido());
		statusDao.save(statusLog);
		
		String token = usuarioDao.findByUsername(pedido.getUserName()).getTokenPushNotification();
		enviaPushNotification(token, "Pedido recebido no restaurante", "Aguarte que em breve enviaremos uma mensagem avisando que o entregador está indo até a sua residência.");
		return HttpStatus.OK;
	}
	
	@PostMapping("/entrega")
	public HttpStatus enviaNotificacaoSaidaParaEntrega(Pedido pedido)
	{
		StatusPedido statusLog = statusDao.findByUsernameAndIdPedido(pedido.getUserName(), pedido.getId());
		statusLog.setDataHoraSaidaParaEntrega("");
		statusDao.save(statusLog);
		
		String token = usuarioDao.findByUsername(pedido.getUserName()).getTokenPushNotification();
		enviaPushNotification(token, "Pedido saiu para entrega", "Nosso entregador já está indo entregar seu pedido. A qualquer momento ele lhe chamará");
		return HttpStatus.OK;
		
	}
	
	private void enviaPushNotification(String token,String titulo, String mensagem)
	{
		System.out.println("Envia pedido de push notification para " + token);
	}
	
}
