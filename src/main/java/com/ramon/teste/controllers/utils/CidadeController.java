package com.ramon.teste.controllers.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.util.CidadeDAO;
import com.ramon.teste.model.util.Cidade;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeDAO cidadeDao;
	
	@GetMapping
	public List<Cidade> getTodas()
	{
		return cidadeDao.findAll();
	}
}
