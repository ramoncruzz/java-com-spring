package com.ramon.teste.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Regiao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double taxaEntrega;
	private boolean regiaoCoberta;
	
	@ElementCollection @Column(length=11)
	private List<String>cep;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<String> getCep() {
		return cep;
	}
	public void setCep(List<String> cep) {
		this.cep = cep;
	}
	public Double getTaxaEntrega() {
		return taxaEntrega;
	}
	public void setTaxaEntrega(Double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}
	public boolean isRegiaoCoberta() {
		return regiaoCoberta;
	}
	public void setRegiaoCoberta(boolean regiaoCoberta) {
		this.regiaoCoberta = regiaoCoberta;
	}
	

}
