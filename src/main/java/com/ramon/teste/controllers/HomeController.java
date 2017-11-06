package com.ramon.teste.controllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.AlimentoDAO;
import com.ramon.teste.DAO.BebidaDAO;
import com.ramon.teste.DAO.CardapioDAO;
import com.ramon.teste.model.*;

import antlr.collections.List;

@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AlimentoDAO alimentoDao;
	@Autowired
	private BebidaDAO bebidaDao;
	@Autowired
	private CardapioDAO cardapioDao;
	
	@GetMapping
	@ResponseBody
	public String index()
	{
		
		return "Olá";
	}
	
	@GetMapping("/carregar")
	public void carregar()
	{
		long id=0L;
		ArrayList<Alimento> listaAlimentos = new ArrayList<Alimento>();
		ArrayList<Bebida> listaBebidas = new ArrayList<Bebida>();
		
		Alimento destaque = new Alimento(); 
		destaque.setDescricao("Spagett");
		destaque.setDisponivel(true);
		destaque.setCategoria("Acompanhamento");
		destaque.setNome("Macarronada");
		id=alimentoDao.save(destaque).getId();
		destaque.setId(id);
		listaAlimentos.add(destaque);
		
		Alimento arroz = new Alimento(); 
		arroz.setDescricao("Arroz Branco");
		arroz.setDisponivel(true);
		arroz.setCategoria("Acompanhamento");
		arroz.setNome("Arroz Branco");
		id=alimentoDao.save(arroz).getId();
		arroz.setId(id);
		listaAlimentos.add(arroz);
		
		Alimento arrozColorido = new Alimento(); 
		arrozColorido.setDescricao("Arroz Colorido");
		arrozColorido.setDisponivel(true);
		arrozColorido.setCategoria("Acompanhamento");
		arrozColorido.setNome("Arroz Colorido");
		id=alimentoDao.save(arrozColorido).getId();
		arrozColorido.setId(id);
		listaAlimentos.add(arrozColorido);
		
		Alimento feijaoPreto = new Alimento(); 
		feijaoPreto.setDescricao("Feijão Preto");
		feijaoPreto.setDisponivel(true);
		feijaoPreto.setCategoria("Acompanhamento");
		feijaoPreto.setNome("Feijão Preto");
		id=alimentoDao.save(feijaoPreto).getId();
		feijaoPreto.setId(id);
		listaAlimentos.add(feijaoPreto);
		
		Alimento feijaoTropeiro = new Alimento(); 
		feijaoTropeiro.setDescricao("Feijão Preto");
		feijaoTropeiro.setDisponivel(true);
		feijaoTropeiro.setCategoria("Acompanhamento");
		feijaoTropeiro.setNome("Feijão Preto");
		id=alimentoDao.save(feijaoTropeiro).getId();
		feijaoTropeiro.setId(id);
		listaAlimentos.add(feijaoTropeiro);
		
		Alimento aboboraCozida = new Alimento(); 
		aboboraCozida.setDescricao("Abobora Cozida");
		aboboraCozida.setDisponivel(true);
		aboboraCozida.setCategoria("Acompanhamento");
		aboboraCozida.setNome("Abobora Cozida");
		id=alimentoDao.save(aboboraCozida).getId();
		aboboraCozida.setId(id);
		listaAlimentos.add(aboboraCozida);
		
		Alimento pureBatata = new Alimento(); 
		pureBatata.setDescricao("Purê de Batatas");
		pureBatata.setDisponivel(true);
		pureBatata.setCategoria("Acompanhamento");
		pureBatata.setNome("Purê de Batatas");
		id=alimentoDao.save(pureBatata).getId();
		pureBatata.setId(id);
		listaAlimentos.add(pureBatata);
		
		Alimento carneBovina = new Alimento(); 
		carneBovina.setDescricao("Carne Bovina");
		carneBovina.setDisponivel(true);
		carneBovina.setCategoria("Churrasco");
		carneBovina.setNome("Carne Bovina");
		id=alimentoDao.save(carneBovina).getId();
		carneBovina.setId(id);
		listaAlimentos.add(carneBovina);
		
		Alimento costelaBovina = new Alimento(); 
		costelaBovina.setDescricao("Costela Bovina");
		costelaBovina.setDisponivel(true);
		costelaBovina.setCategoria("Churrasco");
		costelaBovina.setNome("Costela Bovina");
		id=alimentoDao.save(costelaBovina).getId();
		costelaBovina.setId(id);
		listaAlimentos.add(costelaBovina);
		
		Alimento costelaSuina = new Alimento(); 
		costelaSuina.setDescricao("Costela Suina");
		costelaSuina.setDisponivel(true);
		costelaSuina.setCategoria("Churrasco");
		costelaSuina.setNome("Costela Suina");
		id=alimentoDao.save(costelaSuina).getId();
		costelaSuina.setId(id);
		listaAlimentos.add(costelaSuina);
		
		Alimento linguicaSuina = new Alimento(); 
		linguicaSuina.setDescricao("Linguiça Suina");
		linguicaSuina.setDisponivel(true);
		linguicaSuina.setCategoria("Churrasco");
		linguicaSuina.setNome("Linguiça Suina");
		id=alimentoDao.save(linguicaSuina).getId();
		linguicaSuina.setId(id);
		listaAlimentos.add(linguicaSuina);
		
		Alimento saladaTomate = new Alimento(); 
		saladaTomate.setDescricao("Salada de Tomate");
		saladaTomate.setDisponivel(true);
		saladaTomate.setCategoria("Saladas");
		saladaTomate.setNome("Salada de Tomate");
		id=alimentoDao.save(saladaTomate).getId();
		saladaTomate.setId(id);
		listaAlimentos.add(saladaTomate);
		
		Alimento saladaAlface = new Alimento(); 
		saladaAlface.setDescricao("Salada de Alface");
		saladaAlface.setDisponivel(true);
		saladaAlface.setCategoria("Saladas");
		saladaAlface.setNome("Salada de Alface");
		id=alimentoDao.save(saladaAlface).getId();
		saladaAlface.setId(id);
		listaAlimentos.add(saladaAlface);
		
		Alimento salpicao = new Alimento(); 
		salpicao.setDescricao("Salpicão");
		salpicao.setDisponivel(true);
		salpicao.setCategoria("Saladas");
		salpicao.setNome("Salpicão");
		id=alimentoDao.save(salpicao).getId();
		salpicao.setId(id);
		listaAlimentos.add(salpicao);
		
		//Bebidas 
		Bebida sucoLaranja = new Bebida();
		sucoLaranja.setMarca("Restaurante Vitória");
		sucoLaranja.setNome("Suco de Laranja");
		sucoLaranja.setValor(4.50);
		sucoLaranja.setEscolhido(true);
		sucoLaranja.setDisponivel(true);
		id=bebidaDao.save(sucoLaranja).getId();
		sucoLaranja.setId(id);
		listaBebidas.add(sucoLaranja);
		
		Bebida sucoLimao = new Bebida();
		sucoLimao.setMarca("Restaurante Vitória");
		sucoLimao.setNome("Suco de Limão");
		sucoLimao.setValor(4.50);
		sucoLimao.setEscolhido(true);
		sucoLimao.setDisponivel(true);
		id=bebidaDao.save(sucoLimao).getId();
		sucoLimao.setId(id);
		listaBebidas.add(sucoLimao);
		
		Bebida cocacola = new Bebida();
		cocacola.setMarca("Coca Cola");
		cocacola.setNome("Coca Cola 500ml");
		cocacola.setValor(5.50);
		cocacola.setEscolhido(true);
		cocacola.setDisponivel(true);
		id=bebidaDao.save(cocacola).getId();
		cocacola.setId(id);
		listaBebidas.add(cocacola);
		
		Bebida goianinhoGuarana = new Bebida();
		goianinhoGuarana.setMarca("Goianinho");
		goianinhoGuarana.setNome("Goianinho Guaraná 2L");
		goianinhoGuarana.setValor(6.50);
		goianinhoGuarana.setEscolhido(true);
		goianinhoGuarana.setDisponivel(true);
		id=bebidaDao.save(goianinhoGuarana).getId();
		goianinhoGuarana.setId(id);
		listaBebidas.add(goianinhoGuarana);
		
		Cardapio cardapio =new Cardapio();
		cardapio.setAlimento(listaAlimentos);
		cardapio.setBebidas(listaBebidas);
		cardapio.setDescricaoCardapio("Macarronada Gostosa");
		cardapio.setDestaque(destaque);
		cardapio.setTemporariamenteIndisponivel(false);
		cardapio.setTextoDestaque("Macarronada Gostosa");
		cardapio.setData(new Date());
		cardapio.setAtivo(true);
		cardapioDao.save(cardapio);
		
	}
}
