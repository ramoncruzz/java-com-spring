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

import com.ramon.teste.DAO.BebidaDAO;
import com.ramon.teste.model.Bebida;

@RestController
@RequestMapping("/v0/bebida")
public class BebidaController {

	@Autowired
	private BebidaDAO bebidaDao;
	
	@GetMapping
	public List<Bebida> listarTodos()
	{
		return bebidaDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Bebida bebida)
	{
		Bebida b = bebidaDao.save(bebida);
		if(b.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Bebida bebida)
	{
		Bebida b = bebidaDao.save(bebida);
		if(b.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Bebida bebida)
	{
		try {
			
			bebidaDao.delete(bebida);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
}
