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

import com.candangas.DAO.util.EnderecoDAO;
import com.candangas.DAO.util.LogradouroDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.Endereco;
import com.candangas.model.util.Logradouro;

@RestController
@RequestMapping("/v0/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoDAO enderecoDao;
	@Autowired
	private LogradouroDAO logradouroDao;
	
	@GetMapping(produces="application/json")
	public String listarTodos() {
		try
		{
			return JsonString.geraJsonArray(enderecoDao.findAll());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}

	@GetMapping(value="/{cep}",produces="application/json")
	public String buscaPorCep(@PathVariable String cep) {
		
		try
		{
			Endereco endereco = new Endereco();
			Logradouro logradouro =logradouroDao.findByCep(cep);
			if(logradouro!=null)
			{
				endereco.setId(-1L);
				endereco.setCEP(cep);
				endereco.setLogradouro(logradouro.getDescricao());
				endereco.setBairro(logradouro.getDescricaoBairro());
				endereco.setNomeCidade(logradouro.getCidade().getDescricao());
				endereco.setCodIbgeCidde(logradouro.getCidade().getCodigoIbge());
				
				return JsonString.geraJsonString(endereco);
			}else
				return JsonString.jsonErroMensagem("Endereço não localizado!");
			
		}catch(Exception ex)
		{
			return JsonString.jsonErroMensagem(ex.getMessage());
		}
		
	}

	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Endereco endereco) {
		try
		{
			Endereco e = enderecoDao.save(endereco);
			if (e.getId() > 0)
				return JsonString.geraJsonOK();
			else
				return JsonString.jsonErroMensagem("Erro ao tentar salvar Endereço");
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}

	@PutMapping(produces="application/json")
	public String atualizar(@RequestBody Endereco endereco) {
		try
		{
			Endereco e = enderecoDao.save(endereco);
			if (e.getId() > 0)
				return JsonString.geraJsonOK();
			else
				return JsonString.jsonErroMensagem("Erro ao atualizar endereço, tente novamente.");
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}

	@DeleteMapping(produces="application/json")
	public String apagar(@RequestBody Endereco endereco) {
		try {
			enderecoDao.delete(endereco);
			return JsonString.geraJsonOK();
		} catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
}
