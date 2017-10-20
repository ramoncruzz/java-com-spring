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

import com.ramon.teste.DAO.EnderecoDAO;
import com.ramon.teste.model.Endereco;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired 
	private EnderecoDAO enderecoDao;
	
	@GetMapping
	public List<Endereco> listarTodos()
	{
		return enderecoDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Endereco endereco)
	{
		Endereco e = enderecoDao.save(endereco);
		if(e.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Endereco endereco)
	{
		Endereco e = enderecoDao.save(endereco);
		if(e.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Endereco endereco)
	{
		try
		{
			enderecoDao.delete(endereco);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
