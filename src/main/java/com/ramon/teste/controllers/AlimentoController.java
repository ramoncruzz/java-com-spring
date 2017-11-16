package com.ramon.teste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.AlimentoDAO;
import com.ramon.teste.model.Alimento;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {
 
	@Autowired
	private AlimentoDAO alimentoDao;
	
	@GetMapping
	public List<Alimento> getTodosAlimentos()
	{
		return alimentoDao.findAll();
	}
	
	@PutMapping
	public HttpStatus atualiza(@RequestBody Alimento alimento)
	{
		
		Alimento a =  alimentoDao.save(alimento);
		if(a.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Alimento alimento)
	{
		Alimento a =alimentoDao.save(alimento);
		if(a.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Alimento alimento)
	{
	  Alimento a=	alimentoDao.save(alimento);
		if(!a.isDisponivel())
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
}
