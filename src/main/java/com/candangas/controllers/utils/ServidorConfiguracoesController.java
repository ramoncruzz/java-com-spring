package com.candangas.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candangas.DAO.UsuarioDAO;
import com.candangas.DAO.util.ServidorConfiguracoesDAO;
import com.candangas.model.Usuario;
import com.candangas.model.util.ServidorConfiguracoes;

@RestController
@RequestMapping("/v0/servidor")
public class ServidorConfiguracoesController {
	
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping
	public ServidorConfiguracoes buscaServidor()
	{
		int id= servidorDao.findAll().size();
		return servidorDao.findById((long) id);
	}
	
	@PostMapping
	public HttpStatus salvaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		if(servidorDao.save(servidor).getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PostMapping("/token-impressora")
	public HttpStatus salvaIdResponsavelImpressoesPedidos(@RequestBody String username)
	{
		try
		{
			String limpo =username.replace("\n", "");
			ServidorConfiguracoes srv =this.buscaServidor();
			Usuario user =usuarioDao.findByUsername(limpo);
			String token = user.getTokenPushNotification();
			srv.setTokenResponsavelRecebimentoPedidos(token);
			servidorDao.save(srv);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
	}
	
	@PutMapping
	public HttpStatus atualizaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		if(servidorDao.save(servidor).getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		try
		{
			servidorDao.delete(servidor);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
