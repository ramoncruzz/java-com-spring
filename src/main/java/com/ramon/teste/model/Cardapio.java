package com.ramon.teste.model;

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
	private String data; 
	private boolean ativo;
	private boolean temporariamenteIndisponivel;
	private String descricaoCardapio;
	private String textoDestaque;
	private String mensagem;
	private int horaInicioEntregas;
	private int minutoInicioEntregas;
	
	
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
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

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getHoraInicioEntregas() {
		return horaInicioEntregas;
	}

	public void setHoraInicioEntregas(int horaInicioEntregas) {
		this.horaInicioEntregas = horaInicioEntregas;
	}

	public int getMinutoInicioEntregas() {
		return minutoInicioEntregas;
	}

	public void setMinutoInicioEntregas(int minutoInicioEntregas) {
		this.minutoInicioEntregas = minutoInicioEntregas;
	}

	
	
	
}
