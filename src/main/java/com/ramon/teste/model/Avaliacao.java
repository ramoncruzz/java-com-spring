package com.ramon.teste.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long notaPedido;
	
	private int notaEntregador;
	
	private String comentario;
	
	@ManyToOne
	private Pedido pedido;
	@ManyToOne
	private Entregador entregador;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNotaPedido() {
		return notaPedido;
	}
	public void setNotaPedido(Long notaPedido) {
		this.notaPedido = notaPedido;
	}
	public int getNotaEntregador() {
		return notaEntregador;
	}
	public void setNotaEntregador(int notaEntregador) {
		this.notaEntregador = notaEntregador;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Entregador getEntregador() {
		return entregador;
	}
	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}
	
	
	}
