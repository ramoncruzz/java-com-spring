package com.candangas.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.candangas.helpers.JsonString;

@Entity
public class ServidorConfiguracoes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String tokenServer;
	private String tokenResponsavelRecebimentoPedidos;
	private String codRemetente;
	private String applicationIdAndroid;
	private String applicationIdiOS;
	private String ulrTotalVoiceSMS;
	private String accesTokenTotalVoiceSMS;
	
	
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
	public String getTokenResponsavelRecebimentoPedidos() {
		return tokenResponsavelRecebimentoPedidos;
	}
	public void setTokenResponsavelRecebimentoPedidos(String tokenResponsavelImpressaoPedidos) {
		this.tokenResponsavelRecebimentoPedidos = tokenResponsavelImpressaoPedidos;
	}
	public String getUlrTotalVoiceSMS() {
		return ulrTotalVoiceSMS;
	}
	public void setUlrTotalVoiceSMS(String ulrTotalVoiceSMS) {
		this.ulrTotalVoiceSMS = ulrTotalVoiceSMS;
	}
	public String getAccesTokenTotalVoiceSMS() {
		return accesTokenTotalVoiceSMS;
	}
	public void setAccesTokenTotalVoiceSMS(String accesTokenTotalVoiceSMS) {
		this.accesTokenTotalVoiceSMS = accesTokenTotalVoiceSMS;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
}
