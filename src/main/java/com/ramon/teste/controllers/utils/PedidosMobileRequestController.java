package com.ramon.teste.controllers.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.CardapioDAO;
import com.ramon.teste.DAO.util.FirebaseNotificationsDAO;
import com.ramon.teste.DAO.util.MarmitaDao;
import com.ramon.teste.DAO.util.PedidosMobileRequestDAO;
import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.helpers.StringData;
import com.ramon.teste.model.Marmita;
import com.ramon.teste.model.util.*;

@RestController
@RequestMapping("/v0/pedidosMobile")
public class PedidosMobileRequestController {
	
	@Autowired
	private PedidosMobileRequestDAO pedidoMobileDao;
	
	@Autowired
	private PedidosPoolController pool;
	@Autowired
	private MarmitaDao marmitaDao;
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	@Autowired
	private FirebaseNotificationsDAO firebaseDao;
	
	
	@GetMapping
	public List<PedidosMobileRequest> getTodos()
	{
		return pedidoMobileDao.findAll();
	}
	
	@GetMapping("/limpa")
	public HttpStatus limpa()
	{
		 pedidoMobileDao.deleteAll();
		 return HttpStatus.OK;
	}
	
	@PostMapping
	public String postPedido(@RequestBody PedidosMobileRequest pedidos)
	{
		Long numeroPedido = pedidoMobileDao.count();
		numeroPedido++;
		for(Marmita m: pedidos.getMarmitas())
			{
				Long id=marmitaDao.save(m).getId();
				m.setId(id);
			}
		pedidos.setNumeroPedido(StringData.getDataHoraNumeros()+formaNumeroPedido(numeroPedido));
		PedidosMobileRequest p=pedidoMobileDao.save(pedidos);
		pool.recebePedido(pedidos,servidorDao,firebaseDao);
		
		if(p.getId()>0)
			return StringData.getDataHoraNumeros()+formaNumeroPedido(numeroPedido);
		else 
			return StringData.getDataHoraNumeros()+000;
		
	}
	
	private String formaNumeroPedido(Long numero)
	{
		String numeroformantado="";
		if(numero.toString().length()==1)
			numeroformantado+="00"+numero;
		if(numero.toString().length()==2)
			numeroformantado+="0"+numero;
		return numeroformantado;
	}

}
