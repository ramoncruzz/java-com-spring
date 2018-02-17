package com.candangas.controllers.utils;

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

import com.candangas.DAO.util.CidadeDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.Cidade;

@RestController
@RequestMapping("/v0/cidade")
public class CidadeController {
	
	@Autowired
	private CidadeDAO cidadeDao;
	
	@GetMapping( produces="application/json")
	public String listaTodasCidades()
	{
		try
		{
			return JsonString.geraJsonArray(cidadeDao.findAll());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value="/{codigoIbge}", produces="application/json")
	public String buscaCidadePorCodidgoIbge(@PathVariable Integer codigoIbge)
	{
		try
		{
			Cidade cidade = cidadeDao.findByCodigoIbge(codigoIbge);
			if(cidade==null)
				return JsonString.geraJsonString(cidade);
			else 
				return JsonString.jsonErroMensagem("Cidade não encontrada.");
			
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@PostMapping( produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastraCidade(@RequestBody Cidade cidade)
	{
		try
		{
			cidadeDao.save(cidade);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@PutMapping( produces="application/json")
	public String atualizaCidade(@RequestBody Cidade cidade)
	{
		try
		{
			cidadeDao.save(cidade);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@DeleteMapping( produces="application/json")
	public String apagarCidade(@RequestBody Cidade cidade)
	{
		try
		{
			cidadeDao.delete(cidade);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}

}
