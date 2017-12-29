package com.ramon.teste.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FirebaseNotifications {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idFirebaseNotifications;
	private String tokenUsuario;
	private String tituloMensagem;
	private String mensagem;
	private String dataEnvioMensagem;
	
	public String getTokenUsuario() {
		return tokenUsuario;
	}
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}
	public String getTituloMensagem() {
		return tituloMensagem;
	}
	public void setTituloMensagem(String tituloMensagem) {
		this.tituloMensagem = tituloMensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Integer getIdFirebaseNotifications() {
		return idFirebaseNotifications;
	}
	public void setIdFirebaseNotifications(Integer idFirebaseNotifications) {
		this.idFirebaseNotifications = idFirebaseNotifications;
	}
	public String getDataEnvioMensagem() {
		return dataEnvioMensagem;
	}
	public void setDataEnvioMensagem(String dataEnvioMensagem) {
		this.dataEnvioMensagem = dataEnvioMensagem;
	}
	
	
	
	
}
