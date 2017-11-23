package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.util.PedidosPoolDAO;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.util.*;

@RestController
@RequestMapping("/pedidospool")
public class PedidosPoolController {
	
	@Autowired
	private PedidosPoolDAO poolDao;
	
	@Autowired
	private StatusPedidoController status;
	
	@GetMapping
	public List<PedidosPool> getPedidosNaoRecebidos()
	{
		return poolDao.findByEnviadoParaRestauranteFalse();
	}
	
	public HttpStatus recebePedido(Pedido pedido)
	{
		PedidosPool p = new PedidosPool();
		p.setDataHora(" ");
		p.setPedido(pedido);
		p.setEnviadoParaRestaurante(false);
		
		if(poolDao.save(p).getId()>0)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus sinalizaRecebimentoDoPedidoNoRestaurante(@RequestBody PedidosPool pedidoPool)
	{
		PedidosPool p= poolDao.saveAndFlush(pedidoPool);
		if(p.isEnviadoParaRestaurante())
		 {
			status.enviaNotificaoRecebimentoNoRestaurante(pedidoPool.getPedido());
			return HttpStatus.OK;
		 }
		else 
		return HttpStatus.BAD_REQUEST;
		
	}
	

}
