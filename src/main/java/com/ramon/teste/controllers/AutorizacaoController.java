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

import com.ramon.teste.DAO.AutorizacaoDAO;
import com.ramon.teste.security.Autorizacao;

@RestController
@RequestMapping("/v0/autorizacao")
public class AutorizacaoController {
	
	@Autowired
	private AutorizacaoDAO autorizacaoDao;
	
	@GetMapping
	public List<Autorizacao> listarTodos()
	{
		return autorizacaoDao.findAll();
	}
	
	@GetMapping
	@RequestMapping("/popular")
	public HttpStatus popular()
	{
		
		Autorizacao a = new Autorizacao();
		a.setNome("USER");
		Autorizacao b = new Autorizacao();
		b.setNome("ADMIN");
		autorizacaoDao.save(b);
		
		if(autorizacaoDao.save(a).getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Autorizacao auth)
	{
		Autorizacao a= autorizacaoDao.save(auth);
		if(a.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
		
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Autorizacao auth)
	{
		Autorizacao a= autorizacaoDao.save(auth);
		if(a.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Autorizacao auth)
	{
		try
		{
			autorizacaoDao.delete(auth);
			return HttpStatus.OK;
		}catch (Exception e) {
			
			return HttpStatus.BAD_REQUEST;
		}
		
		
	}

}
