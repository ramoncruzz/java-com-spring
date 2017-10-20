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
import com.ramon.teste.DAO.AvaliacaoDAO;
import com.ramon.teste.model.Avaliacao;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
	private AvaliacaoDAO avaliacaoDao;
	
	@GetMapping
	public List<Avaliacao> listarTodos()
	{
		return avaliacaoDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Avaliacao avaliacao)
	{
		Avaliacao a = avaliacaoDao.save(avaliacao);
		if(a.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Avaliacao avaliacao)
	{
		Avaliacao a = avaliacaoDao.save(avaliacao);
		if(a.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Avaliacao avaliacao)
	{
		try
		{
			avaliacaoDao.delete(avaliacao);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
}
