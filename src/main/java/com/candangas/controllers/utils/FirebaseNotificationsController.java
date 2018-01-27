package com.candangas.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.UsuarioDAO;
import com.candangas.DAO.util.FirebaseNotificationsDAO;
import com.candangas.DAO.util.ServidorConfiguracoesDAO;
import com.candangas.helpers.JsonString;
import com.candangas.helpers.StringData;
import com.candangas.model.Usuario;
import com.candangas.model.util.FirebaseNotifications;
import com.candangas.model.util.ServidorConfiguracoes;
import com.candangas.services.HttpRequests;

@RestController
@RequestMapping("/v0/firebaseNotifications")
public class FirebaseNotificationsController {

	@Autowired
	private FirebaseNotificationsDAO firebaseDao;
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping(produces="application/json")
	public String getFirebaseObject() {
		try {
			return JsonString.geraJsonArray(firebaseDao.findAll());
		} catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}

	}
	
	@PostMapping(produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String enviaNotificacaoFireBase(@RequestBody FirebaseNotifications mensagem) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			FirebaseNotifications f= firebaseDao.save(mensagem);
			return JsonString.geraJsonCreatedUpdated(f.getIdFirebaseNotifications());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}

	}
	
	@PostMapping(value="/{username}",produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public String enviaNotificacaoParaUsuario(@RequestBody FirebaseNotifications mensagem, @PathVariable String username)
	{
		try
		{
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			Usuario usuario = usuarioDao.findByUsername(username);
			String tokenUsuario = usuario.getTokenPushNotification();
			mensagem.setTokenUsuario(tokenUsuario);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			FirebaseNotifications f= firebaseDao.save(mensagem);
			return JsonString.geraJsonCreatedUpdated(f.getIdFirebaseNotifications());
		}catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	@DeleteMapping(produces="application/json")
	public String deletaFirebaseConfiguracoes(FirebaseNotifications mensagem) {
		try {
			firebaseDao.delete((long)mensagem.getIdFirebaseNotifications());
			return JsonString.geraJsonOK();

		} catch (Exception e) {
			return JsonString.jsonErroMensagem( e.getMessage());
		}
	}
	
	public String enviaNotificacaoFireBase(FirebaseNotifications mensagem,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			FirebaseNotifications f =firebaseDao.save(mensagem);
			
			return JsonString.geraJsonCreatedUpdated(f.getIdFirebaseNotifications());

		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}
	
	public String enviaNotificacaoFireBase(FirebaseNotifications mensagem,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao,String chaveParamentro,String conteudoParametro) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem(),chaveParamentro,conteudoParametro);
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			FirebaseNotifications f =firebaseDao.save(mensagem);
			return JsonString.geraJsonCreatedUpdated(f.getIdFirebaseNotifications());

		}catch (Exception e) {
			return JsonString.jsonErroMensagem(e.getMessage());
		}

	}

}
