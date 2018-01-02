package com.ramon.teste.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusPedido {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	private Long idPedido;
	private String numeroPedido;
	private String datahoraEnvioPeloUsuario;
	private String dataHoraRecebimentoNoRestaurante;
	private String dataHoraSaidaParaEntrega;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getDataHoraRecebimentoNoRestaurante() {
		return dataHoraRecebimentoNoRestaurante;
	}
	public void setDataHoraRecebimentoNoRestaurante(String dataHoraRecebimentoNoRestaurante) {
		this.dataHoraRecebimentoNoRestaurante = dataHoraRecebimentoNoRestaurante;
	}
	public String getDataHoraSaidaParaEntrega() {
		return dataHoraSaidaParaEntrega;
	}
	public void setDataHoraSaidaParaEntrega(String dataHoraSaidaParaEntrega) {
		this.dataHoraSaidaParaEntrega = dataHoraSaidaParaEntrega;
	}
	public String getDatahoraEnvioPeloUsuario() {
		return datahoraEnvioPeloUsuario;
	}
	public void setDatahoraEnvioPeloUsuario(String datahoraEnvioPeloUsuario) {
		this.datahoraEnvioPeloUsuario = datahoraEnvioPeloUsuario;
	}
	
	
}
