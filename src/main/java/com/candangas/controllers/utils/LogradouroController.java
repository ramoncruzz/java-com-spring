package com.candangas.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.util.LogradouroDAO;
import com.candangas.helpers.JsonString;

@RestController
@RequestMapping("/v0/logradouro")
public class LogradouroController {
	
	@Autowired
	private LogradouroDAO logradouroDao;
	
	@GetMapping(produces="application/json")
	public String findAll()
	{
		try {
			return JsonString.geraJsonArray(logradouroDao.findAll());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@GetMapping(value="/{nomeBairro}",produces="application/json")
	public String findByBairro(@PathVariable String nomeBairro)
	{
		try {
			return JsonString.geraJsonArray(logradouroDao.findByDescricaoBairro(nomeBairro));
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}

}
