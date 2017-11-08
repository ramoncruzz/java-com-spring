package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.util.MarmitaDao;
import com.ramon.teste.DAO.util.PedidosMobileRequestDAO;
import com.ramon.teste.model.util.*;

@RestController
@RequestMapping("/pedidosMobile")
public class PedidosMobileRequestController {
	
	@Autowired
	private PedidosMobileRequestDAO pedidoMobileDao;
	
	@Autowired
	private MarmitaDao marmitaDao;
	
	@GetMapping
	public List<PedidosMobileRequest> getTodos()
	{
		return pedidoMobileDao.findAll();
	}
	
	@PostMapping
	public HttpStatus postPedido(@RequestBody PedidosMobileRequest pedidos)
	{
		for(MarmitaMobileRequest m: pedidos.getMarmitas())
			{
				Long id=marmitaDao.save(m).getId();
				m.setId(id);
			}
		PedidosMobileRequest p=pedidoMobileDao.save(pedidos);
		
		if(p.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
		
	}

}
