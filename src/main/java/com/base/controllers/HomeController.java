package com.base.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.DAO.util.ServidorConfiguracoesDAO;
import com.base.model.util.ServidorConfiguracoes;


@RestController
@RequestMapping("/v0")
public class HomeController {

	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	
	@GetMapping
	public String index()
	{
		
		return "Olá";
	}
	
	@GetMapping("/carregar")
	public void carregar()
	{
		ServidorConfiguracoes srv = new ServidorConfiguracoes();
		srv.setCodRemetente("153101293432");
		srv.setTokenServer("AAAAI6WMV3g:APA91bFe5AyDOh9KO5CyviIcoRZXYWApuC9wCKYg8_1PiVfadlm6f8Bf9akeyc58_moy9v6RekJPAw6KgK1gVu6rZUxsA2uhYKIA225ra7Ov_mCKgeUD-qDgBtUA7_X5DUTJcf3FncLE");
		srv.setUlrTotalVoiceSMS("https://api.totalvoice.com.br/sms");
		srv.setAccesTokenTotalVoiceSMS("b4e5c480634ccd2903b2ef80aee7fa40");
		servidorDao.save(srv);
	}
	
}
