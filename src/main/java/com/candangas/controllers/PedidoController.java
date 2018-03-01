package com.candangas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.PedidoDAO;
import com.candangas.helpers.JsonString;
import com.candangas.helpers.StringData;
import com.candangas.model.Pedido;

@RestController
@RequestMapping("/v0/pedido")
public class PedidoController {

	@Autowired
	private PedidoDAO pedidoDao;
	
	@GetMapping(produces="applicaton/json")
	public String listaTodosPedidos()
	{
		try
		{
			return JsonString.geraJsonArray(pedidoDao.findAll());	
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value ="/{usernameVendedor}",produces="aaplication/json")
	public String listaPorUsuario(@PathVariable String usernameVendedor)
	{
		try
		{
			return JsonString.geraJsonArray(pedidoDao.findByUsernameVendedor(usernameVendedor));	
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value ="/historico",produces="aaplication/json")
	public String historico()
	{
		try
		{
			return JsonString.geraJsonArray(pedidoDao.findAll());	
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value ="/historico/{usernameVendedor}",produces="aaplication/json")
	public String historicoPorUsuario(@PathVariable String usernameVendedor)
	{
		try
		{
			return JsonString.geraJsonArray(pedidoDao.findByFinalizadoTrueAndUsernameVendedor(usernameVendedor));	
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@PostMapping(produces="applicaton/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String cadastrar(@RequestBody Pedido pedido)
	{
		try
		{
			long qtd=pedidoDao.count();
			String codigo=null;
			
			codigo=StringData.getDataHoraNumeros()+(qtd+1);
			
			pedido.setCodigoPedido(codigo);
			pedido.setFinalizado(false);
			pedidoDao.save(pedido);
			
			return JsonString.geraJsonOKWithMensage(codigo);
			
		}catch (Exception e) {
		    return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PostMapping(value="/cancelar/{codigoPedido}",produces="applicaton/json")
	public String CancelarPedido(@PathVariable String codigoPedido)
	{
		try
		{
			Pedido pedido= pedidoDao.findByCodigoPedido(codigoPedido);
			if(pedido==null)
				return JsonString.jsonErroMensagem("Pedido não encontrado!");
			else 
			{
				pedido.setCancelado(true);
				pedidoDao.save(pedido);
				return JsonString.geraJsonOK();
			}
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
		
	}
	
	@PostMapping(value="/finalizar/{codigoPedido}",produces="applicaton/json")
	public String FinalizarPedido(@PathVariable String codigoPedido)
	{
		try
		{
			Pedido pedido= pedidoDao.findByCodigoPedido(codigoPedido);
			if(pedido==null)
				return JsonString.jsonErroMensagem("Pedido não encontrado!");
			else 
			{
				pedido.setFinalizado(true);
				pedidoDao.save(pedido);
				return JsonString.geraJsonOK();
			}
		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@PutMapping(produces="applicaton/json")
	public String atualizar(@RequestBody Pedido pedido)
	{
		try
		{
			return JsonString.geraJsonOK();
		}catch (Exception e) {
		    return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@PutMapping(value="/atualiza-status", produces="applicaton/json")
	public String inativar(@RequestBody Pedido pedido)
	{
		try
		{
			return JsonString.geraJsonOK();
		}catch (Exception e) {
		    return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	@GetMapping(value="/{codigoPedido}", produces="applicaton/json")
	public String buscarPorCodigoPedido(@PathVariable String codigoPedido)
	{
		try
		{
			Pedido pedido = pedidoDao.findByCodigoPedido(codigoPedido);
			if(pedido!=null && pedido.getId()>0)
			{
				return JsonString.geraJsonString(pedido);
			}else
			{
				return JsonString.jsonErroMensagem("Pedido não encontrado.");
			}
			
		}catch (Exception e) {
		    return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
}
