package com.ramon.teste.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
 
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String numeroPedido;
	
	private Date dataHora;
	
	@Column(nullable=false)
	private Double precoFinal;
	
	@Column(nullable=false)
	private Double taxaEntrega;
	
	@Column(nullable=false)
	private Double taxaConveniencia;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Avaliacao avaliacao;
	
	@ElementCollection @Column(length=50)
	private List<String> alimentosEscolhidos;
	
	@ElementCollection @Column(length=50)
	private List<String> bebidasEscolhidas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Double getPrecoFinal() {
		return precoFinal;
	}

	public void setPrecoFinal(Double precoFinal) {
		this.precoFinal = precoFinal;
	}

	public Double getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(Double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	public Double getTaxaConveniencia() {
		return taxaConveniencia;
	}

	public void setTaxaConveniencia(Double taxaConveniencia) {
		this.taxaConveniencia = taxaConveniencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<String> getAlimentosEscolhidos() {
		return alimentosEscolhidos;
	}

	public void setAlimentosEscolhidos(List<String> alimentosEscolhidos) {
		this.alimentosEscolhidos = alimentosEscolhidos;
	}

	public List<String> getBebidasEscolhidas() {
		return bebidasEscolhidas;
	}

	public void setBebidasEscolhidas(List<String> bebidasEscolhidas) {
		this.bebidasEscolhidas = bebidasEscolhidas;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
	


}
