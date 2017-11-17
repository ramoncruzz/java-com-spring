package com.ramon.teste.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.PedidoDAO;
import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.Usuario;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired 
	private PedidoDAO pedidoDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping(value = "/{userName}")
	public Pedido listarTodosPorUsuario(@PathVariable String userName)
	{
		String username =userName.replace("-", ".");
		Usuario usuario = usuarioDao.findByUsername(username);
		return pedidoDao.findByUsuario(usuario);
	}
	
	@GetMapping
	public List<Pedido> listarTodos()
	{
		return pedidoDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Pedido pedido)
	{
		Pedido p = pedidoDao.save(pedido);
		if(p.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Pedido pedido)
	{
		Pedido p = pedidoDao.save(pedido);
		if(p.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Pedido pedido)
	{
		try 
		{
			pedidoDao.delete(pedido);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
		
	
}
