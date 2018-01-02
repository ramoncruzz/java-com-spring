package com.ramon.teste.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.model.util.ServidorConfiguracoes;

@RestController
@RequestMapping("/v0/servidor")
public class ServidorConfiguracoesController {
	
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	
	@GetMapping
	public ServidorConfiguracoes buscaServidor()
	{
		int id= servidorDao.findAll().size();
		return servidorDao.findById((long) id);
	}
	
	@PostMapping
	public HttpStatus salvaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		if(servidorDao.save(servidor).getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		if(servidorDao.save(servidor).getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		try
		{
			servidorDao.delete(servidor);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
