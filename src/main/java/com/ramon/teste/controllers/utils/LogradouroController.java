package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ramon.teste.model.util.Logradouro;
import com.ramon.teste.DAO.util.LogradouroDAO;

@RestController
@RequestMapping("/logradouro")
public class LogradouroController {

	@Autowired
	private LogradouroDAO logradouroDao;
	
	@GetMapping
	public List<Logradouro> findAll()
	{
		return logradouroDao.findAll();
	}
	@GetMapping("/{nomeBairro}")
	public List<Logradouro> findByBairro(@PathVariable String nomeBairro)
	{
		return logradouroDao.findByDescricaoBairro(nomeBairro);
	}
}
