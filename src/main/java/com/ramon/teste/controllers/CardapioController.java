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

import com.ramon.teste.DAO.CardapioDAO;
import com.ramon.teste.model.Cardapio;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

	@Autowired
	private CardapioDAO cardapioDao;
	
	@GetMapping
	public List<Cardapio> listarTodos(@RequestBody Cardapio cardapio)
	{
		return cardapioDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Cardapio cardapio)
	{
		Cardapio c = cardapioDao.save(cardapio);
		if(c.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Cardapio cardapio)
	{
		Cardapio c = cardapioDao.save(cardapio);
		if(c.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Cardapio cardapio)
	{
		try
		{
			cardapioDao.delete(cardapio);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
		
	
}
