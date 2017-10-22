package com.ramon.teste.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Cardapio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date data; 
	private boolean ativo;
	private boolean temporariamenteIndisponivel;
	private String descricaoCardapio;
	private String textoDestaque;
	
	@ManyToOne
	private Alimento destaque;
	
	@OneToMany
	private List<Bebida> bebidas;
	
	@OneToMany
	private List<Alimento> alimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isTemporariamenteIndisponivel() {
		return temporariamenteIndisponivel;
	}

	public void setTemporariamenteIndisponivel(boolean temporariamenteIndisponivel) {
		this.temporariamenteIndisponivel = temporariamenteIndisponivel;
	}

	public String getDescricaoCardapio() {
		return descricaoCardapio;
	}

	public void setDescricaoCardapio(String descricaoCardapio) {
		this.descricaoCardapio = descricaoCardapio;
	}

	public String getTextoDestaque() {
		return textoDestaque;
	}

	public void setTextoDestaque(String textoDestaque) {
		this.textoDestaque = textoDestaque;
	}

	public Alimento getDestaque() {
		return destaque;
	}

	public void setDestaque(Alimento destaque) {
		this.destaque = destaque;
	}

	public List<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public List<Alimento> getAlimento() {
		return alimento;
	}

	public void setAlimento(List<Alimento> alimento) {
		this.alimento = alimento;
	}

	

	
	
}