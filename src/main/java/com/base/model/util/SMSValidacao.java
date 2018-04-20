package com.base.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.base.helpers.JsonString;

@Entity
public class SMSValidacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nomeAplicativo;
	private String codigoValidacao;
	private String telefone;
	private boolean validado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoValidacao() {
		return codigoValidacao;
	}
	public void setCodigoValidacao(String codigoValidacao) {
		this.codigoValidacao = codigoValidacao;
	}
	public String getTelefone() {
		return telefone;
	}
	public boolean isValidado() {
		return validado;
	}
	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
	public String getNomeAplicativo() {
		return nomeAplicativo;
	}
	public void setNomeAplicativo(String nomeAplicativo) {
		this.nomeAplicativo = nomeAplicativo;
	}
}
