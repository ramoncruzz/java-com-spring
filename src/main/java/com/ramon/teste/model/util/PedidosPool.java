package com.ramon.teste.model.util;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ramon.teste.model.Pedido;

@Entity
public class PedidosPool {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private boolean enviadoParaRestaurante;
	private String dataHora;
	private String dataHoraRecebimentoRestaurante;
	
	@ManyToOne
	private Pedido pedido;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isEnviadoParaRestaurante() {
		return enviadoParaRestaurante;
	}
	public void setEnviadoParaRestaurante(boolean enviadoParaRestaurante) {
		this.enviadoParaRestaurante = enviadoParaRestaurante;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getDataHoraRecebimentoRestaurante() {
		return dataHoraRecebimentoRestaurante;
	}
	public void setDataHoraRecebimentoRestaurante(String dataHoraRecebimentoRestaurante) {
		this.dataHoraRecebimentoRestaurante = dataHoraRecebimentoRestaurante;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
