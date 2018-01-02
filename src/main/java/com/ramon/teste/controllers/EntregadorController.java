package com.ramon.teste.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ramon.teste.DAO.EntregadorDAO;
import com.ramon.teste.model.Entregador;

@RestController
@RequestMapping("/v0/entregador")
public class EntregadorController {

	@Autowired
	private EntregadorDAO entregadoDao;
	
	@GetMapping
	public List<Entregador> listarTodos()
	{
		return entregadoDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Entregador entregador)
	{
		Entregador e = entregadoDao.save(entregador);
		if(e.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Entregador entregador)
	{
		Entregador e = entregadoDao.save(entregador);
		if(e.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Entregador entregador)
	{
		try
		{
			entregadoDao.delete(entregador);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
