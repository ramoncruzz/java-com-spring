package com.ramon.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ServidorConfiguracoes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String tokenServer;
	private String tokenResponsavelImpressaoPedidos;
	private String codRemetente;
	private String applicationIdAndroid;
	private String applicationIdiOS;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTokenServer() {
		return tokenServer;
	}
	public void setTokenServer(String tokenServer) {
		this.tokenServer = tokenServer;
	}
	public String getCodRemetente() {
		return codRemetente;
	}
	public void setCodRemetente(String codRemetente) {
		this.codRemetente = codRemetente;
	}
	public String getApplicationIdAndroid() {
		return applicationIdAndroid;
	}
	public void setApplicationIdAndroid(String applicationIdAndroid) {
		this.applicationIdAndroid = applicationIdAndroid;
	}
	public String getApplicationIdiOS() {
		return applicationIdiOS;
	}
	public void setApplicationIdiOS(String applicationIdiOS) {
		this.applicationIdiOS = applicationIdiOS;
	}
	public String getTokenResponsavelImpressaoPedidos() {
		return tokenResponsavelImpressaoPedidos;
	}
	public void setTokenResponsavelImpressaoPedidos(String tokenResponsavelImpressaoPedidos) {
		this.tokenResponsavelImpressaoPedidos = tokenResponsavelImpressaoPedidos;
	}
	
}
