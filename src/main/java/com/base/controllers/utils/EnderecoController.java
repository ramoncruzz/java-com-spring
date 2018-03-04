package com.base.controllers.utils;

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

import com.base.DAO.util.CidadeDAO;
import com.base.DAO.util.EnderecoDAO;
import com.base.DAO.util.LogradouroDAO;
import com.base.helpers.JsonString;
import com.base.model.util.Cidade;
import com.base.model.util.Endereco;
import com.base.model.util.Logradouro;

@RestController
@RequestMapping("/v0/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoDAO enderecoDao;
	@Autowired
	private LogradouroDAO logradouroDao;
	@Autowired
	private CidadeDAO cidadeDao;
	
	
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
				endereco.setCep(cep);
				endereco.setLogradouro(logradouro.getDescricao());
				endereco.setBairro(logradouro.getDescricaoBairro());
				endereco.setNomeCidade(logradouro.getCidade().getDescricao());
				endereco.setCodIbgeCidde(logradouro.getCidade().getCodigoIbge());
				endereco.setIdCidade(logradouro.getCidade().getIdCidade());
				
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
			String cep= endereco.getCep();
			String numCasa = endereco.getNumCasa();
			
			Logradouro logradouro = logradouroDao.findByCep(cep);
			if(logradouro==null) return JsonString.jsonErroMensagem("Não foi possível registrar o endereço. Tente novamente!");
			Cidade cidade = cidadeDao.findByIdCidade(logradouro.getCidade().getIdCidade());
			if(cidade==null) return JsonString.jsonErroMensagem("Não foi possível registrar o endereço. Tente novamente!");
			
			Endereco salvar = new Endereco();
			salvar.setBairro(logradouro.getDescricaoBairro());
			salvar.setCep(cep);
			salvar.setCidade(cidade);
		    salvar.setNumCasa(numCasa);		
			
			Endereco e = enderecoDao.save(salvar);
			if (e.getId() > 0)
				return JsonString.geraJsonString(e);
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
