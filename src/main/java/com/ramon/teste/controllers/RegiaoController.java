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

import com.ramon.teste.DAO.RegiaoDAO;
import com.ramon.teste.model.Regiao;

@RestController
@RequestMapping("/v0/regiao")
public class RegiaoController {

	@Autowired
	private RegiaoDAO regiaoDao;
	
	@GetMapping
	public List<Regiao> listarTodos()
	{
		return regiaoDao.findAll();
	}
	
	@GetMapping("/{cep}")
	public List<Regiao> buscarPorCep(@PathVariable String cep)
	{
		return regiaoDao.findByCepIn(cep);
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Regiao regiao)
	{
		Regiao r = regiaoDao.save(regiao);
		if(r.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Regiao regiao)
	{
		Regiao r = regiaoDao.save(regiao);
		if(r.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Regiao regiao)
	{
		try
		{
			regiaoDao.delete(regiao);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
}
