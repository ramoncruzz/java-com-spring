package com.ramon.teste.controllers.utils;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.PedidoDAO;
import com.ramon.teste.DAO.util.MarmitaDao;
import com.ramon.teste.DAO.util.PedidosMobileRequestDAO;
import com.ramon.teste.model.Marmita;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.util.*;

@RestController
@RequestMapping("/pedidosMobile")
public class PedidosMobileRequestController {
	
	@Autowired
	private PedidosMobileRequestDAO pedidoMobileDao;
	@Autowired
	private PedidoDAO pedidoDao;
	@Autowired
	private PedidosPoolController pool;
	@Autowired
	private MarmitaDao marmitaDao;
	
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
	public Long postPedido(@RequestBody PedidosMobileRequest pedidos)
	{
		Long numeroPedido = pedidoMobileDao.count();
		numeroPedido++;
		for(Marmita m: pedidos.getMarmitas())
			{
				Long id=marmitaDao.save(m).getId();
				m.setId(id);
			}
		pedidos.setNumeroPedido(numeroPedido);
		PedidosMobileRequest p=pedidoMobileDao.save(pedidos);
		pool.recebePedido(pedidos);
		
		if(p.getId()>0)
			return numeroPedido;
		else 
			return 0L;
		
	}
	
	private Pedido convertParaPedido(PedidosMobileRequest pedidos)
	{
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Bebida 01");
		lista.add("Bebida 02");
		lista.add("Bebida 03");
		
		Pedido p = new Pedido();
		p.setBebidas(null);
		p.setMarmitas(pedidos.getMarmitas());
		p.setDataHora(pedidos.getDataHora());
		p.setEndereco(pedidos.getEndereco());
		p.setPontoReferencia(pedidos.getPontoReferencia());
		p.setRegiaoNome(pedidos.getRegiaoNome());
		p.setNomeCompleto(pedidos.getNomeCompleto());
		p.setNumeroPedido(formaNumeroPedido(pedidos.getNumeroPedido()));
		p.setPrecoFinal(pedidos.getPrecoFinal());
		p.setTaxaConveniencia(pedidos.getTaxaConveniencia());
		p.setTaxaEntrega(pedidos.getTaxaEntrega());
		p.setUserName(pedidos.getUserName());
		p.setId(pedidoDao.save(p).getId());
		return p;
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
