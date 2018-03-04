package com.base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.base.DAO.AutorizacaoDAO;
import com.base.helpers.JsonString;
import com.base.security.Autorizacao;

@RestController
@RequestMapping("/v0/autorizacao")
public class AutorizacaoController {
	
	@Autowired
	private AutorizacaoDAO autorizacaoDao;
	
	@GetMapping(produces="application/json")
	public String listarTodos()
	{
		try {
			return JsonString.geraJsonArray(autorizacaoDao.findAll());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@GetMapping(value="/criar-autorizacoes-padrao",produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String popular()
	{
		try
		{
			Autorizacao a = new Autorizacao();
			a.setNome("USER");
			autorizacaoDao.save(a);
			Autorizacao b = new Autorizacao();
			b.setNome("ADMIN");
			autorizacaoDao.save(b);
			
			return JsonString.geraJsonArray(autorizacaoDao.findAll());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Autorizacao auth)
	{
		try
		{
			Autorizacao a= autorizacaoDao.save(auth);
			return JsonString.geraJsonCreatedUpdated(a.getId());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PutMapping(produces="application/json")
	public String atualizar(@RequestBody Autorizacao auth)
	{
		try {
			Autorizacao a= autorizacaoDao.save(auth);
			return JsonString.geraJsonCreatedUpdated(a.getId());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
			
		}

	}
	
	@DeleteMapping(produces="application/json")
	public String apagar(@RequestBody Autorizacao auth)
	{
		try
		{
			autorizacaoDao.delete(auth);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
		
	}

}
