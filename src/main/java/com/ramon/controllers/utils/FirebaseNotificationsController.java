package com.ramon.controllers.utils;

import java.io.IOException;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
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

import com.ramon.DAO.UsuarioDAO;
import com.ramon.DAO.util.FirebaseNotificationsDAO;
import com.ramon.DAO.util.ServidorConfiguracoesDAO;
import com.ramon.helpers.StringData;
import com.ramon.model.Usuario;
import com.ramon.model.util.FirebaseNotifications;
import com.ramon.model.util.ServidorConfiguracoes;
import com.ramon.services.HttpRequests;

@RestController
@RequestMapping("/v0/firebaseNotifications")
public class FirebaseNotificationsController {

	@Autowired
	private FirebaseNotificationsDAO firebaseDao;
	@Autowired
	private ServidorConfiguracoesDAO servidorDao;
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@GetMapping
	public List<FirebaseNotifications> getFirebaseObject() {
		try {
			return firebaseDao.findAll();
		} catch (Exception e) {
			return null;
		}

	}
	
	@PostMapping
	public HttpStatus enviaNotificacaoFireBase(@RequestBody FirebaseNotifications mensagem) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			firebaseDao.save(mensagem);
			return HttpStatus.OK;

		} 
			catch (ClientProtocolException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (JSONException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}
	
	@PostMapping("/{username}")
	public HttpStatus enviaNotificacaoParaUsuario(@RequestBody FirebaseNotifications mensagem, @PathVariable String username)
	{
		try
		{
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			Usuario usuario = usuarioDao.findByUsername(username);
			String tokenUsuario = usuario.getTokenPushNotification();
			mensagem.setTokenUsuario(tokenUsuario);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			
			return HttpStatus.OK;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	@DeleteMapping
	public HttpStatus deletaFirebaseConfiguracoes(FirebaseNotifications mensagem) {
		try {
			firebaseDao.delete((long)mensagem.getIdFirebaseNotifications());
			return HttpStatus.OK;

		} catch (Exception e) {

			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

	@PutMapping
	public HttpStatus atualizaFirebaseConfiguracoes(FirebaseNotifications mensagem) {
		try {
		return HttpStatus.INTERNAL_SERVER_ERROR;

		} catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}
	
	public HttpStatus enviaNotificacaoFireBase(FirebaseNotifications mensagem,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem());
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			firebaseDao.save(mensagem);
			return HttpStatus.OK;

		} 
			catch (ClientProtocolException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (JSONException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}
	
	public HttpStatus enviaNotificacaoFireBase(FirebaseNotifications mensagem,ServidorConfiguracoesDAO servidorDao,FirebaseNotificationsDAO firebaseDao,String chaveParamentro,String conteudoParametro) {
		try {
			
			HttpRequests r = new HttpRequests();
			ServidorConfiguracoes srv = servidorDao.findById(1L);
			r.notificaUsuario(srv.getTokenServer(), mensagem.getTokenUsuario(), mensagem.getTituloMensagem(), mensagem.getMensagem(),chaveParamentro,conteudoParametro);
			mensagem.setDataEnvioMensagem(StringData.getStringData());
			firebaseDao.save(mensagem);
			return HttpStatus.OK;

		} 
			catch (ClientProtocolException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (JSONException e) {
			e.printStackTrace();
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}

	}

}