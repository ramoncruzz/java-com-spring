package com.ramon.teste.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.EnderecoDAO;
import com.ramon.teste.DAO.RegiaoDAO;
import com.ramon.teste.DAO.util.LogradouroDAO;
import com.ramon.teste.model.Endereco;
import com.ramon.teste.model.Regiao;
import com.ramon.teste.model.util.Logradouro;

@RestController
@RequestMapping("/v0/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoDAO enderecoDao;
	@Autowired
	private LogradouroDAO logradouroDao;
	@Autowired
	private RegiaoDAO regiaoDao;
	
	@GetMapping
	public List<Endereco> listarTodos() {
		return enderecoDao.findAll();
	}

	@GetMapping("/{cep}")
	public Endereco buscaPorCep(@PathVariable String cep) {
		Endereco endereco = new Endereco();
		Logradouro logradouro =logradouroDao.findByCep(cep);
		Regiao regiao=regiaoDao.findByCepIn(cep).get(0);
		endereco.setId(-1L);
		endereco.setCEP(cep);
		endereco.setLogradouro(logradouro.getDescricao());
		endereco.setRegiao(regiao);
		endereco.setBairro(logradouro.getDescricaoBairro());
		return endereco;
	}

	@PostMapping
	public HttpStatus cadastrar(@RequestBody Endereco endereco) {
		Endereco e = enderecoDao.save(endereco);
		if (e.getId() > 0)
			return HttpStatus.CREATED;
		else
			return HttpStatus.BAD_REQUEST;
	}

	@PutMapping
	public HttpStatus atualizar(@RequestBody Endereco endereco) {
		Endereco e = enderecoDao.save(endereco);
		if (e.getId() > 0)
			return HttpStatus.OK;
		else
			return HttpStatus.BAD_REQUEST;
	}

	@DeleteMapping
	public HttpStatus apagar(@RequestBody Endereco endereco) {
		try {
			enderecoDao.delete(endereco);
			return HttpStatus.OK;
		} catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
