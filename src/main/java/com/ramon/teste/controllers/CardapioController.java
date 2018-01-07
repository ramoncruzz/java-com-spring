package com.ramon.teste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.CardapioDAO;
import com.ramon.teste.controllers.utils.StatusPedidoController;
import com.ramon.teste.model.Cardapio;

@RestController
@RequestMapping("/v0/cardapio")
public class CardapioController {

	@Autowired
	private CardapioDAO cardapioDao;
	
	@Autowired
	private StatusPedidoController statusController;
	
	@GetMapping("/todos")
	public List<Cardapio> listarTodos()
	{
		return cardapioDao.findAll();
	}
	
	@GetMapping("/id-corrente")
	public Long idCorrente()
	{
		Cardapio cardapio = cardapioDao.findFirstByOrderByIdDesc();
		if(cardapio!=null)
			return cardapio.getId();
		else
			return 0L;
	}
	
	@GetMapping
	public Cardapio cardapioCorrente()
	{
		Cardapio cardapio = cardapioDao.findFirstByOrderByIdDesc();
		
		return cardapio;
	}
	
	@PostMapping
	public HttpStatus cadastrar(@RequestBody Cardapio cardapio)
	{
		Cardapio c = cardapioDao.save(cardapio);
		if(c.getId()>0)
			{
				statusController.enviaNotificacaoTodosUsuarios("Cardápio do Dia", "Hoje temos como destaque do dia "+cardapio.getTextoDestaque()+" e muito mais.. Você pode já pode fazer seu pedido.");
				return HttpStatus.CREATED;
			}
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@PutMapping
	public HttpStatus atualizar(@RequestBody Cardapio cardapio)
	{
		Cardapio c = cardapioDao.save(cardapio);
		if(c.getId()>0)
			return HttpStatus.OK;
		else 
			return HttpStatus.BAD_REQUEST;
	}
	
	@DeleteMapping
	public HttpStatus apagar(@RequestBody Cardapio cardapio)
	{
		try
		{
			cardapioDao.delete(cardapio);
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.BAD_REQUEST;
		}
	}
	
	public void atualizarCodigoAtualizacao()
	{
		
	}
		
	
}
