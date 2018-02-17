package com.candangas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.UsuarioDAO;
import com.candangas.DAO.util.EnderecoDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.Usuario;
import com.candangas.model.util.Endereco;
import com.candangas.services.UserService;

@RestController
@RequestMapping("/v0/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	UserService registro;
	
	@Autowired
	private EnderecoDAO enderecoDao;
	
	@GetMapping(value="/{username}",produces="application/json")
	public String getUsuarioPorUserName(@PathVariable String username)
	{
		try
		{
			Usuario usuario= usuarioDao.findByUsername(username);
			return usuario.toString();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@GetMapping(produces="application/json")
	public String listarTodos()
	{
		try {
			return JsonString.geraJsonArray(usuarioDao.findAll());
		}catch (Exception e) {
		
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Usuario usuario)
	{
		try
		{
			if(usuario.getIdEndereco()!=null)
			{
				Endereco endereco = enderecoDao.findById(usuario.getIdEndereco());
				usuario.setEndereco(endereco);
			}
			Long id=registro.registerUser(usuario);
			
			return JsonString.geraJsonCreatedUpdated(id);
		}catch (Exception e) {

			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PostMapping(value="/ativar",produces="application/json")
	public String ativarUsuario(@RequestBody Usuario usuario)
	{
		try {
			String username=usuario.getUsername();
			Usuario usuarioSalvo= usuarioDao.findByUsername(username);
			usuarioSalvo.setAtivo(true);
			usuarioDao.save(usuarioSalvo);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PostMapping(value="/desativar",produces="application/json")
	public String desativarUsuario(@RequestBody Usuario usuario)
	{
		try {
			String username=usuario.getUsername();
			Usuario usuarioSalvo= usuarioDao.findByUsername(username);
			usuarioSalvo.setAtivo(false);
			usuarioDao.save(usuarioSalvo);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PutMapping(produces="application/json")
	public String atualizar(@RequestBody Usuario usuario)
	{
		try
		{
			
			Usuario usuarioAtualizado = usuarioDao.save(usuario);
			return JsonString.geraJsonString(usuarioAtualizado);
				
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@PutMapping(value="/recuperar",produces="application/json")
	public String recuperar(@RequestBody Usuario usuario)
	{
		try
		{	
			Usuario usuarioSalvo = usuarioDao.findByUsername(usuario.getUsername());
			Long idSalvo = usuarioSalvo.getId();
			String nome = usuarioSalvo.getNome();
			String sobrenome = usuarioSalvo.getSobreNome();
			usuario.setId(idSalvo);
			usuario.setNome(nome);
			usuario.setSobreNome(sobrenome);
			Long id=registro.registerUser(usuario);
			
			return JsonString.geraJsonCreatedUpdated(id);
				
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@DeleteMapping(value="/id-{id}",produces="application/json")
	public String apagarPeloId(@PathVariable Long id)
	{
		try
		{
			Usuario usuario= usuarioDao.findById(id);
			usuarioDao.delete(usuario);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage()); 
		}
	}
	
	@DeleteMapping(value="/{username}",produces="application/json")
	public String apagar(@PathVariable String username)
	{
		try
		{
			Usuario usuario= usuarioDao.findByUsername(username);
			usuarioDao.delete(usuario);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
		
	
}
