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

import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.model.Usuario;
import com.ramon.teste.services.UserService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	UserService registro;
	
	@GetMapping
	public List<Usuario> listarTodos()
	{
		return usuarioDao.findAll();
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Usuario usuario)
	{
		Long id=registro.registerUser(usuario);
		if(id>0)
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
	
	@DeleteMapping("/{username}")
	public HttpStatus apagar(@PathVariable String username)
	{
		try
		{
			Usuario usuario= usuarioDao.findByUsername(username);
			usuarioDao.delete(usuario);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
		
	
}
