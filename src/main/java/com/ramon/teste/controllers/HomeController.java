package com.ramon.teste.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.*;
import com.ramon.teste.DAO.util.MarmitaDao;
import com.ramon.teste.DAO.util.PedidosMobileRequestDAO;
import com.ramon.teste.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.teste.helpers.StringData;
import com.ramon.teste.model.*;
import com.ramon.teste.model.util.PedidosMobileRequest;
import com.ramon.teste.model.util.ServidorConfiguracoes;
import com.ramon.teste.security.Autorizacao;
import com.ramon.teste.services.HttpRequests;


@RestController
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AlimentoDAO alimentoDao;
	@Autowired
	private BebidaDAO bebidaDao;
	@Autowired
	private CardapioDAO cardapioDao;
	
	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private AutorizacaoDAO autorizacaoDao;
	@Autowired
	private PedidosMobileRequestDAO pedidosMobileDAO;
	@Autowired
	private MarmitaDao marmitaDAO;
	
	@Autowired
	private RegiaoDAO regiaoDao;
	
	@Autowired
	private EnderecoDAO enderecoDao;
	
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	
	@GetMapping
	@ResponseBody
	public String index()
	{
		
		return "Olá";
	}
	
	@GetMapping("/notificacao")
	public void request()
	{
		HttpRequests r = new HttpRequests();
		try {
			//String tokenServidor="AAAApejE2J8:APA91bHHpo_ILaY9fs2dWG2JBAQKDyAkg2Qu-Cd0xh9SH_BGRHBwrpbYnfpLWuqEZQuaMB0X3s1w-ys9nqmqG5btT7wqvUTQ2-iBkUAEE3rAp-aBSs4w4r7VPcpUbZxs077ZhafuXNxX";
			String tokenUsuario="fiZnAZtFBys:APA91bGxwT-AAAE8_zurVn85vYXC2nsmnkBQVY3nfnmiUrMITv1y37AfOt6y7p-l6QgvElovUsID0MFOOwsjp3QZ-0ku6PytXGlToHQnyKC3O0Tt1H-k4CDi6790pTHj7CF6-D-9oqlg";
			String tituloMensagem="OpressorasDetect";
			String mensagem="Luana Opressora está por perto!";
			
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), tokenUsuario, tituloMensagem, mensagem);
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	}
	
	@GetMapping("/carregar")
	public void carregar()
	{
		ServidorConfiguracoes srv = new ServidorConfiguracoes();
		srv.setCodRemetente("153101293432");
		srv.setTokenServer("AAAAI6WMV3g:APA91bFe5AyDOh9KO5CyviIcoRZXYWApuC9wCKYg8_1PiVfadlm6f8Bf9akeyc58_moy9v6RekJPAw6KgK1gVu6rZUxsA2uhYKIA225ra7Ov_mCKgeUD-qDgBtUA7_X5DUTJcf3FncLE");
		servidorDao.save(srv);
		
		long id=0L;
		ArrayList<Alimento> listaAlimentos = new ArrayList<Alimento>();
		ArrayList<Bebida> listaBebidas = new ArrayList<Bebida>();
		
		ArrayList<String> listaCep = new ArrayList<>();
		
		listaCep.add("cep00");
		
		Regiao regiao = new Regiao();
		regiao.setCep(listaCep);
		regiao.setNome("Região tribulosa");
		regiao.setTaxaEntrega(2.5);
		regiaoDao.save(regiao);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Logradouro");
		endereco.setRegiao(regiao);
		enderecoDao.save(endereco);
		
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
		arroz.setModaDaCasa(true);
		listaAlimentos.add(arroz);
		
		Alimento arrozColorido = new Alimento(); 
		arrozColorido.setDescricao("Arroz Colorido");
		arrozColorido.setDisponivel(true);
		arrozColorido.setCategoria("Acompanhamento");
		arrozColorido.setNome("Arroz Colorido");
		id=alimentoDao.save(arrozColorido).getId();
		arrozColorido.setId(id);
		arrozColorido.setModaDaCasa(false);
		listaAlimentos.add(arrozColorido);
		
		Alimento feijaoPreto = new Alimento(); 
		feijaoPreto.setDescricao("Feijão Preto");
		feijaoPreto.setDisponivel(true);
		feijaoPreto.setCategoria("Acompanhamento");
		feijaoPreto.setNome("Feijão Preto");
		id=alimentoDao.save(feijaoPreto).getId();
		feijaoPreto.setId(id);
		feijaoPreto.setModaDaCasa(true);
		listaAlimentos.add(feijaoPreto);
		
		Alimento feijaoTropeiro = new Alimento(); 
		feijaoTropeiro.setDescricao("Feijão Preto");
		feijaoTropeiro.setDisponivel(true);
		feijaoTropeiro.setCategoria("Acompanhamento");
		feijaoTropeiro.setNome("Feijão Preto");
		id=alimentoDao.save(feijaoTropeiro).getId();
		feijaoTropeiro.setId(id);
		feijaoTropeiro.setModaDaCasa(false);
		listaAlimentos.add(feijaoTropeiro);
		
		Alimento aboboraCozida = new Alimento(); 
		aboboraCozida.setDescricao("Abobora Cozida");
		aboboraCozida.setDisponivel(true);
		aboboraCozida.setCategoria("Acompanhamento");
		aboboraCozida.setNome("Abobora Cozida");
		id=alimentoDao.save(aboboraCozida).getId();
		aboboraCozida.setId(id);
		aboboraCozida.setModaDaCasa(true);
		listaAlimentos.add(aboboraCozida);
		
		Alimento pureBatata = new Alimento(); 
		pureBatata.setDescricao("Purê de Batatas");
		pureBatata.setDisponivel(true);
		pureBatata.setCategoria("Acompanhamento");
		pureBatata.setNome("Purê de Batatas");
		id=alimentoDao.save(pureBatata).getId();
		pureBatata.setId(id);
		pureBatata.setModaDaCasa(true);
		listaAlimentos.add(pureBatata);
		
		Alimento carneBovina = new Alimento(); 
		carneBovina.setDescricao("Carne Bovina");
		carneBovina.setDisponivel(true);
		carneBovina.setCategoria("Churrasco");
		carneBovina.setNome("Carne Bovina");
		id=alimentoDao.save(carneBovina).getId();
		carneBovina.setId(id);
		carneBovina.setModaDaCasa(true);
		listaAlimentos.add(carneBovina);
		
		Alimento costelaBovina = new Alimento(); 
		costelaBovina.setDescricao("Costela Bovina");
		costelaBovina.setDisponivel(true);
		costelaBovina.setCategoria("Churrasco");
		costelaBovina.setNome("Costela Bovina");
		id=alimentoDao.save(costelaBovina).getId();
		costelaBovina.setId(id);
		costelaBovina.setModaDaCasa(false);
		listaAlimentos.add(costelaBovina);
		
		Alimento costelaSuina = new Alimento(); 
		costelaSuina.setDescricao("Costela Suina");
		costelaSuina.setDisponivel(true);
		costelaSuina.setCategoria("Churrasco");
		costelaSuina.setNome("Costela Suina");
		id=alimentoDao.save(costelaSuina).getId();
		costelaSuina.setId(id);
		costelaSuina.setModaDaCasa(false);
		listaAlimentos.add(costelaSuina);
		
		Alimento linguicaSuina = new Alimento(); 
		linguicaSuina.setDescricao("Linguiça Suina");
		linguicaSuina.setDisponivel(true);
		linguicaSuina.setCategoria("Churrasco");
		linguicaSuina.setNome("Linguiça Suina");
		id=alimentoDao.save(linguicaSuina).getId();
		linguicaSuina.setId(id);
		linguicaSuina.setModaDaCasa(true);
		listaAlimentos.add(linguicaSuina);
		
		Alimento saladaTomate = new Alimento(); 
		saladaTomate.setDescricao("Salada de Tomate");
		saladaTomate.setDisponivel(true);
		saladaTomate.setCategoria("Saladas");
		saladaTomate.setNome("Salada de Tomate");
		id=alimentoDao.save(saladaTomate).getId();
		saladaTomate.setId(id);
		saladaTomate.setModaDaCasa(true);
		listaAlimentos.add(saladaTomate);
		
		Alimento saladaAlface = new Alimento(); 
		saladaAlface.setDescricao("Salada de Alface");
		saladaAlface.setDisponivel(true);
		saladaAlface.setCategoria("Saladas");
		saladaAlface.setNome("Salada de Alface");
		id=alimentoDao.save(saladaAlface).getId();
		saladaAlface.setId(id);
		saladaAlface.setModaDaCasa(true);
		listaAlimentos.add(saladaAlface);
		
		Alimento salpicao = new Alimento(); 
		salpicao.setDescricao("Salpicão");
		salpicao.setDisponivel(true);
		salpicao.setCategoria("Saladas");
		salpicao.setNome("Salpicão");
		id=alimentoDao.save(salpicao).getId();
		salpicao.setId(id);
		salpicao.setModaDaCasa(false);
		listaAlimentos.add(salpicao);
		
		//Bebidas 
		Bebida sucoLaranja = new Bebida();
		sucoLaranja.setMarca("500ml");
		sucoLaranja.setNome("Suco de Laranja");
		sucoLaranja.setValor(4.50);
		sucoLaranja.setEscolhido(true);
		sucoLaranja.setDisponivel(true);
		id=bebidaDao.save(sucoLaranja).getId();
		sucoLaranja.setId(id);
		listaBebidas.add(sucoLaranja);
		
		Bebida sucoLimao = new Bebida();
		sucoLimao.setMarca("500ml");
		sucoLimao.setNome("Suco de Limão");
		sucoLimao.setValor(4.50);
		sucoLimao.setEscolhido(true);
		sucoLimao.setDisponivel(true);
		id=bebidaDao.save(sucoLimao).getId();
		sucoLimao.setId(id);
		listaBebidas.add(sucoLimao);
		
		Bebida sucoManga = new Bebida();
		sucoManga.setMarca("500ml");
		sucoManga.setNome("Suco de Manga");
		sucoManga.setValor(4.50);
		sucoManga.setEscolhido(true);
		sucoManga.setDisponivel(true);
		id=bebidaDao.save(sucoManga).getId();
		sucoManga.setId(id);
		listaBebidas.add(sucoManga);

		Bebida sucoAbacaxi = new Bebida();
		sucoAbacaxi.setMarca("500ml");
		sucoAbacaxi.setNome("Suco de Abacaxi");
		sucoAbacaxi.setValor(4.50);
		sucoAbacaxi.setEscolhido(true);
		sucoAbacaxi.setDisponivel(true);
		id=bebidaDao.save(sucoAbacaxi).getId();
		sucoAbacaxi.setId(id);
		listaBebidas.add(sucoAbacaxi);
		
		Bebida cocacola = new Bebida();
		cocacola.setMarca("500ml");
		cocacola.setNome("Coca Cola");
		cocacola.setValor(5.50);
		cocacola.setEscolhido(true);
		cocacola.setDisponivel(true);
		id=bebidaDao.save(cocacola).getId();
		cocacola.setId(id);
		listaBebidas.add(cocacola);
		
		Bebida dolly = new Bebida();
		dolly.setMarca("500ml");
		dolly.setNome("Dolly Guaraná");
		dolly.setValor(5.50);
		dolly.setEscolhido(true);
		dolly.setDisponivel(true);
		id=bebidaDao.save(dolly).getId();
		dolly.setId(id);
		listaBebidas.add(dolly);
		
		Bebida agua = new Bebida();
		agua.setMarca("500ml");
		agua.setNome("Água com Gás");
		agua.setValor(2.50);
		agua.setEscolhido(true);
		agua.setDisponivel(true);
		id=bebidaDao.save(agua).getId();
		agua.setId(id);
		listaBebidas.add(agua);
		
		Bebida skol = new Bebida();
		skol.setMarca("500ml");
		skol.setNome("Skoll");
		skol.setValor(3.50);
		skol.setEscolhido(true);
		skol.setDisponivel(true);
		id=bebidaDao.save(skol).getId();
		skol.setId(id);
		listaBebidas.add(skol);
		
		Bebida goianinhoGuarana = new Bebida();
		goianinhoGuarana.setMarca("2L");
		goianinhoGuarana.setNome("Goianinho Guaraná");
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
		cardapio.setData(StringData.getStringData());
		cardapio.setAtivo(true);
		cardapioDao.save(cardapio);
		
		//Carregar PedidoFAke
		ArrayList<String> listaFake = new ArrayList<>();
		listaFake.add("Objeto 01");
		listaFake.add("Objeto 02");
		listaFake.add("Objeto 03");
		
		Endereco end = new Endereco();
		end.setLogradouro("Logradouro");
		end.setPontoDeReferencia("Ponto de referencia");
		
		Autorizacao auth = new Autorizacao();
		auth.setNome("USER");
		id =autorizacaoDao.save(auth).getId();
		auth.setId(id);
		
		Usuario usuarioFake = new Usuario();
		usuarioFake.setAtivo(true);
		usuarioFake.setCpf("11111111");
		usuarioFake.setNomeCompleto("Usuario Fulano");
		usuarioFake.setUserName("usuario@usuario.com");
		usuarioFake.setAutorizacao(auth);
		id=usuarioDao.save(usuarioFake).getId();
		usuarioFake.setId(id);
		
		
		Marmita marmita = new Marmita();
		marmita.setAlimentosEscolhidos(listaFake);
		id=marmitaDAO.save(marmita).getId();
		marmita.setId(id);
		
		ArrayList<Marmita> listaMarmita = new ArrayList<>();
		listaMarmita.add(marmita);
		
		PedidosMobileRequest pedidosMobile = new PedidosMobileRequest();
		pedidosMobile.setBebidas(listaFake);
		pedidosMobile.setDataHora("11-22-33");
		pedidosMobile.setEndereco("SQ 11 QQ");
		pedidosMobile.setMarmitas(listaMarmita);
		pedidosMobile.setNomeCompleto("Fulano de tal");
		pedidosMobile.setPontoReferencia("Rua Magica");
		pedidosMobile.setPrecoFinal(1.0);
		pedidosMobile.setRegiaoNome("Bairro");
		pedidosMobile.setTaxaConveniencia(1);
		pedidosMobile.setTaxaEntrega(3);
		id = pedidosMobileDAO.save(pedidosMobile).getId();
		pedidosMobile.setId(id);
		
		
		
	}
}
