package com.candangas.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.CatalogoDAO;
import com.candangas.helpers.ErrosMensagens;
import com.candangas.model.Catalogo;

@RestController
@RequestMapping("/v0/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoDAO catalogoDao;
	
	@GetMapping
	public List<Catalogo> listaCatalogos()
	{
		try
		{
			return catalogoDao.findAll();
		}catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/{codigoReferencia}")
	public Catalogo buscaPorCodigoReferencia(@PathVariable String codigoReferencia)
	{
		try
		{
			return catalogoDao.findByCodigoReferencia(codigoReferencia);
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@GetMapping("/{nome}")
	public List<Catalogo> buscaPorNome(@PathVariable String nome)
	{
		try
		{
			return catalogoDao.findByNome(nome);
		}catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping(produces="application/json")
	public String cadastrar(@RequestBody Catalogo catalogo)
	{
		JSONObject resposta = new JSONObject();
		try
		{
			Catalogo c = catalogoDao.save(catalogo);
			 resposta.put("status", "OK");
			 resposta.put("id", c.getId());
			 
			return resposta.toString();
		}catch (Exception e) {
			// TODO: handle exception
			return ErrosMensagens.erroMensagem("erro", e.getMessage());
		}
		
	}
	
	@PutMapping(produces="application/json")
	public String atualizar(@RequestBody Catalogo catalogo)
	{
		JSONObject resposta = new JSONObject();
		try
		{
			Catalogo c = catalogoDao.save(catalogo);
			 resposta.put("status", "OK");
			 resposta.put("id", c.getId());
			 
			return resposta.toString();
		}catch (Exception e) {
			// TODO: handle exception
			return ErrosMensagens.erroMensagem("erro", e.getMessage());
		}
	}
	
	@DeleteMapping(produces="application/json")
	public String apagar(@RequestBody Catalogo catalogo)
	{
		JSONObject resposta = new JSONObject();
		try
		{
			catalogoDao.delete(catalogo);
			 resposta.put("status", "OK");
			 
			return resposta.toString();
		}catch (Exception e) {
			// TODO: handle exception
			return ErrosMensagens.erroMensagem("erro", e.getMessage());
		}
	}
	
}
