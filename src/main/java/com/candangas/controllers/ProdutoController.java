package com.candangas.controllers;

import java.util.List;

import org.json.JSONException;
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
import com.candangas.DAO.ProdutoDAO;
import com.candangas.helpers.ErrosMensagens;
import com.candangas.model.Produto;

@RestController
@RequestMapping("/v0/produto")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@PostMapping(produces="application/json")
	public String cadastrar(@RequestBody Produto produto) 
	{
		JSONObject resposta = new JSONObject();
	try {
			Produto p = produtoDao.save(produto);
		    resposta.put("status", "OK");
		    resposta.put("id", p.getId());
			return resposta.toString();
		} catch (JSONException e) {
			
			return ErrosMensagens.erroMensagem("erro", e.getMessage());
			
		}
		catch (Exception e) {
			return ErrosMensagens.erroMensagem("erro", e.getMessage());
		}
		
	}
	
	@GetMapping
	public List<Produto> listaProdutos()
	{
		try {
			return produtoDao.findAll();
		} catch (Exception e) {
			
			return null;
		}
		
	}
	
	@GetMapping("/{codigoReferencia}")
	public Produto buscaProduto(@PathVariable String codigoReferencia)
	{
		try {
			return produtoDao.findByCodigoReferencia(codigoReferencia);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping(produces="application/json")
	public String atualizarProduto(@RequestBody Produto produto)
	{
		JSONObject resposta = new JSONObject();
		try {
				Produto p=produtoDao.save(produto);
			    resposta.put("status", "OK");
			    resposta.put("id", p.getId());
				return resposta.toString();
			} catch (JSONException e) {
				
				return ErrosMensagens.erroMensagem("erro", e.getMessage());
				
			}
			catch (Exception e) {
				return ErrosMensagens.erroMensagem("erro", e.getMessage());
			}
		
	}
	
	@DeleteMapping(produces="application/json")
	public String apagarProduto(@RequestBody Produto produto)
	{
		JSONObject resposta = new JSONObject();
		try {
				produtoDao.delete(produto);
			    resposta.put("status", "OK");
				return resposta.toString();
			} catch (JSONException e) {
				
				return ErrosMensagens.erroMensagem("erro", e.getMessage());
				
			}
			catch (Exception e) {
				return ErrosMensagens.erroMensagem("erro", e.getMessage());
			}
		
	}
	
	
}
