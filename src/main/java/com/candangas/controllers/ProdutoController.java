package com.candangas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.candangas.DAO.ProdutoDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.Produto;

@RestController
@RequestMapping("/v0/produto")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Produto produto) 
	{
		
	try {
			Produto p = produtoDao.save(produto);
		    return JsonString.geraJsonCreatedUpdated(p.getId());
		} 
		catch (DataIntegrityViolationException e) {
			return JsonString.jsonErroMensagem("Codigo referencia ja cadastrado");
		}
		catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@GetMapping(produces="application/json")
	public String listaProdutos()
	{
		try {
			 return JsonString.geraJsonArray(produtoDao.findAll());
		} catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
		
	}
	
	@GetMapping(value="/{codigoReferencia}",produces="application/json")
	public String buscaProduto(@PathVariable String codigoReferencia)
	{
		try {
			Produto p =produtoDao.findByCodigoReferencia(codigoReferencia);
			return p.toString();
		} catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@PutMapping(produces="application/json")
	public String atualizarProduto(@RequestBody Produto produto)
	{
		
		try {
				Produto p=produtoDao.save(produto);
				return JsonString.geraJsonCreatedUpdated(p.getId());
			}
			catch (DataIntegrityViolationException e) {
			return JsonString.jsonErroMensagem("Codigo referencia ja cadastrado");
		}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
		
	}
	
	@DeleteMapping(produces="application/json")
	public String apagarProduto(@RequestBody Produto produto)
	{
		try {
				produtoDao.delete(produto);
			    
				return JsonString.geraJsonOK();
			} 
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
		
	}
	
	
}
