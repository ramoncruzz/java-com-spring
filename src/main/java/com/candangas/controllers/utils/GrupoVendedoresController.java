package com.candangas.controllers.utils;

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
import com.candangas.DAO.util.GrupoVendedoresDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.GrupoVendedores;

@RestController
@RequestMapping("/v0/grupo-vendedores")
public class GrupoVendedoresController {
	
	@Autowired
	private GrupoVendedoresDAO grupoDao;
	
	@GetMapping
	public List<GrupoVendedores> listaTodos()
	{
		return grupoDao.findAll();
	}
	
	@GetMapping("/{nome}")
	public GrupoVendedores buscaGrupo(@PathVariable String nome)
	{
		try {    
			   return grupoDao.findByNome(nome);
			} catch (Exception e) {
			
				return null;
			}	
	}
	
	@PostMapping
	public String cadastraGrupo(@RequestBody GrupoVendedores grupo)
	{
		JSONObject resposta = new JSONObject();
		try {
				GrupoVendedores g=grupoDao.save(grupo);
			    resposta.put("status", "OK");
			    resposta.put("id", g.getId());
				return resposta.toString();
			} catch (JSONException e) {
				
				return JsonString.jsonErroMensagem( e.getMessage());
				
			}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}
	
	@PutMapping(produces="application/json")
	public String atualizarGrupo(@RequestBody GrupoVendedores grupo)
	{
		JSONObject resposta = new JSONObject();
		try {
				GrupoVendedores g=grupoDao.save(grupo);
			    resposta.put("status", "OK");
			    resposta.put("id", g.getId());
				return resposta.toString();
			} catch (JSONException e) {
				
				return JsonString.jsonErroMensagem( e.getMessage());
				
			}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}
	
	@DeleteMapping(produces="application/json")
	public String apagarGrupo(GrupoVendedores grupo)
	{
		JSONObject resposta = new JSONObject();
		try {
				grupoDao.delete(grupo);
			    resposta.put("status", "OK");
				return resposta.toString();
			} catch (JSONException e) {
				
				return JsonString.jsonErroMensagem( e.getMessage());
			}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}

}
