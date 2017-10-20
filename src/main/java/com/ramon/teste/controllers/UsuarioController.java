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

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.model.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping
	public List<Usuario> listarTodos(@RequestBody Usuario usuario)
	{
		return usuarioDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Usuario usuario)
	{
		Usuario u = usuarioDao.save(usuario);
		if(u.getId()>0)
			return HttpStatus.CREATED;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Usuario usuario)
	{
		Usuario u = usuarioDao.save(usuario);
		if(u.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Usuario usuario)
	{
		try
		{
			usuarioDao.delete(usuario);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
		
	
}
