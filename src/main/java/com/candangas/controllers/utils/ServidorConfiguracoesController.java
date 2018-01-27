package com.candangas.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.candangas.DAO.UsuarioDAO;
import com.candangas.DAO.util.ServidorConfiguracoesDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.Usuario;
import com.candangas.model.util.ServidorConfiguracoes;

@RestController
@RequestMapping("/v0/servidor")
public class ServidorConfiguracoesController {
	
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping(produces="application/json")
	public String buscaServidor()
	{
		try
		{
			int id= servidorDao.findAll().size();
			ServidorConfiguracoes srv= servidorDao.findById((long) id);
			return JsonString.geraJsonString(srv);
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String salvaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		try
		{
			if(servidorDao.save(servidor).getId()>0)
				return JsonString.geraJsonOK();
			else 
				return JsonString.jsonErroMensagem("Não foi possível salvar as informaçoes.");
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
		
	}
	
	@PostMapping(value="/token-impressora",produces="applicaton/json")
	public String salvaIdResponsavelImpressoesPedidos(@RequestBody String username)
	{
		try
		{
			String limpo =username.replace("\n", "");
			int id= servidorDao.findAll().size();
			ServidorConfiguracoes srv= servidorDao.findById((long) id);
			Usuario user =usuarioDao.findByUsername(limpo);
			String token = user.getTokenPushNotification();
			srv.setTokenResponsavelRecebimentoPedidos(token);
			servidorDao.save(srv);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PutMapping(produces="application/json")
	public String atualizaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		try
		{
			if(servidorDao.save(servidor).getId()>0)
				return JsonString.geraJsonOK();
			else 
				return JsonString.jsonErroMensagem("Não foi possível atualizar as informaçoes.");
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@DeleteMapping(produces="application/json")
	public String  apagaServidor(@RequestBody ServidorConfiguracoes servidor)
	{
		try
		{
			servidorDao.delete(servidor);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}

}
