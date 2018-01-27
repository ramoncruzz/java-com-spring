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
import com.candangas.DAO.util.GrupoVendedoresDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.GrupoVendedores;

@RestController
@RequestMapping("/v0/grupo-vendedores")
public class GrupoVendedoresController {
	
	@Autowired
	private GrupoVendedoresDAO grupoDao;
	
	@GetMapping(produces="application/json")
	public String listaTodos()
	{
		try {
		return JsonString.geraJsonArray(grupoDao.findAll());
		}catch (Exception e) {
			// TODO: handle exception
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value="/{nome}", produces="application/json")
	public String buscaGrupo(@PathVariable String nome)
	{
		try {    
			   GrupoVendedores g =grupoDao.findByNome(nome);
			   return JsonString.geraJsonString(g);
			} catch (Exception e) {
			
				return JsonString.jsonErroMensagem(e.getMessage());
			}	
	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastraGrupo(@RequestBody GrupoVendedores grupo)
	{
		
		try {
				GrupoVendedores g=grupoDao.save(grupo);
				return JsonString.geraJsonCreatedUpdated(g.getId());
		}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}
	
	@PutMapping(produces="application/json")
	public String atualizarGrupo(@RequestBody GrupoVendedores grupo)
	{
		
		try {
				GrupoVendedores g=grupoDao.save(grupo);
				return JsonString.geraJsonCreatedUpdated(g.getId());
		}
			
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}
	
	@DeleteMapping(produces="application/json")
	public String apagarGrupo(GrupoVendedores grupo)
	{
	try {
				grupoDao.delete(grupo);
			    return JsonString.geraJsonOK();
		}
			catch (Exception e) {
				return JsonString.jsonErroMensagem( e.getMessage());
			}
	}

}
