package com.base.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.base.DAO.UsuarioDAO;
import com.base.DAO.util.EnderecoDAO;
import com.base.helpers.JsonString;
import com.base.model.Usuario;
import com.base.model.util.Endereco;
import com.base.services.UserService;

@RestController
@RequestMapping("/v0/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	UserService registro;
	
	@Autowired
	private EnderecoDAO enderecoDao;
	
	@GetMapping(value="/busca/{username}",produces="application/json")
	public String getUsuarioPorUserName(@PathVariable String username)
	{
		try
		{
			Usuario usuario= usuarioDao.findByUsername(username);
			if(usuario!=null)
				return JsonString.geraJsonString(usuario);
			else
				return JsonString.jsonErroMensagem("Usuário não encontrado.");
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PostMapping(value="/consulta",produces="application/json")
	public String consultaExistenciadeUsuario(@RequestBody Usuario usuarioPost)
	{
		try
		{
			String username=usuarioPost.getUsername();
			Usuario usuario= usuarioDao.findByUsername(username);
			if(usuario!=null)
				return JsonString.geraJsonOKWithMensage("Usuario existe.");
			else 
				return JsonString.jsonErroMensagem("Usuario não existe");
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
	
	@GetMapping(value="/vendedores",produces="application/json")
	public String listaVendedores()
	{
		try
		{
			ArrayList<Usuario> listaVendedores = new ArrayList<>();
			List<Usuario> usuariosSalvos = usuarioDao.findAll();
			if(usuariosSalvos!=null && usuariosSalvos.size()>0)
			{
				for(Usuario user:usuariosSalvos)
					if(user.getTipo().equalsIgnoreCase("USER"))
						listaVendedores.add(user);	
			}else
				return JsonString.jsonErroMensagem("Nenhum vendedor cadastrado.");
			return JsonString.geraJsonArray(listaVendedores);
		}catch (IndexOutOfBoundsException e) {
			return JsonString.jsonErroMensagem("Nenhum vendedor cadastrado.");
		}catch(Exception e)
		{
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value="/administradores",produces="application/json")
	public String listaAdministradores()
	{
		try
		{
			ArrayList<Usuario> listaVendedores = new ArrayList<>();
			List<Usuario> usuariosSalvos = usuarioDao.findAll();
			if(usuariosSalvos!=null && usuariosSalvos.size()>0)
			{
				for(Usuario user:usuariosSalvos)
					if(user.getTipo().equalsIgnoreCase("ADMIN"))
						listaVendedores.add(user);	
			}else
				return JsonString.jsonErroMensagem("Nenhum vendedor cadastrado.");
			return JsonString.geraJsonArray(listaVendedores);
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
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
	
	@PostMapping(value="/admin",produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrarAdmin(@RequestBody Usuario usuario)
	{
		try
		{
			if(usuario.getIdEndereco()!=null)
			{
				Endereco endereco = enderecoDao.findById(usuario.getIdEndereco());
				usuario.setEndereco(endereco);
			}
			Long id=registro.registerAdminUser(usuario);
			
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
			Usuario usuarioSalvo = usuarioDao.findByUsername(usuario.getUsername());
			Long idSalvo = usuarioSalvo.getId();
			String nome = usuarioSalvo.getNome();
			String sobrenome = usuarioSalvo.getSobreNome();
			usuario.setId(idSalvo);
			usuario.setNome(nome);
			usuario.setSobreNome(sobrenome);
			usuario.setCpf(usuarioSalvo.getCpf());
			usuario.setLinkfotoUsuario(usuarioSalvo.getLinkfotoUsuario());
			usuario.setEndereco(usuarioSalvo.getEndereco());
			usuario.setTelefoneCelular(usuarioSalvo.getTelefoneCelular());
			usuario.setAtivo(usuarioSalvo.isAtivo());
			Long id=0L;
			if(usuarioSalvo.getTipo().equalsIgnoreCase("USER"))
				id=registro.registerUser(usuario);
			if(usuarioSalvo.getTipo().equalsIgnoreCase("ADMIN"))
				id=registro.registerAdminUser(usuario);
			
			return JsonString.geraJsonCreatedUpdated(id);
				
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
			usuario.setCpf(usuarioSalvo.getCpf());
			usuario.setLinkfotoUsuario(usuarioSalvo.getLinkfotoUsuario());
			usuario.setEndereco(usuarioSalvo.getEndereco());
			usuario.setTelefoneCelular(usuarioSalvo.getTelefoneCelular());
			Long id=0L;
			if(usuarioSalvo.getTipo().equalsIgnoreCase("USER"))
				id=registro.registerUser(usuario);
			if(usuarioSalvo.getTipo().equalsIgnoreCase("ADMIN"))
				id=registro.registerAdminUser(usuario);
			
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
