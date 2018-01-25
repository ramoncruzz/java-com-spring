package com.candangas.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.candangas.helpers.JsonString;
@Entity
public class Email {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	private String remetente;
	private String destinatario;
	private String titulo;
	private String mensagem;
	private String dataHoraEnvio;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDataHoraEnvio() {
		return dataHoraEnvio;
	}
	public void setDataHoraEnvio(String dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
}
