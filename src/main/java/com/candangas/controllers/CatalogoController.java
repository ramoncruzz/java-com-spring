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
import com.candangas.DAO.CatalogoDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.Catalogo;

@RestController
@RequestMapping("/v0/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoDAO catalogoDao;
	
	@GetMapping//(produces="application/json")
	public String listaCatalogos()
	{
		try
		{
			return JsonString.geraJsonArray(catalogoDao.findAll());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@GetMapping(value="/{codigoReferencia}",produces="application/json")
	public String buscaPorCodigoReferencia(@PathVariable String codigoReferencia)
	{
		try
		{
			return catalogoDao.findByCodigoReferencia(codigoReferencia).toString();
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@GetMapping(value="/{nome}",produces="application/json")
	public String buscaPorNome(@PathVariable String nome)
	{
		try
		{
			return JsonString.geraJsonArray(catalogoDao.findByNome(nome));
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Catalogo catalogo)
	{
		try
		{
			Catalogo c = catalogoDao.save(catalogo); 
			return JsonString.geraJsonCreatedUpdated(c.getId());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@PutMapping(produces="application/json")
	public String atualizar(@RequestBody Catalogo catalogo)
	{
		try
		{
			Catalogo c = catalogoDao.save(catalogo);
			return JsonString.geraJsonCreatedUpdated(c.getId());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@DeleteMapping(produces="application/json")
	public String apagar(@RequestBody Catalogo catalogo)
	{
		try
		{
			catalogoDao.delete(catalogo);
			return JsonString.geraJsonOK();
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
}
