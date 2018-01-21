package com.candangas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private String codigoReferencia;
	private String genero;
	private String indicadoPara;
	private String categoria;
	private String material;
	private String tecnologia;
	private String descricaoTecnologia;
	private String composicao;
	private String garantiaFabricante;
	private String origem;
	private String linkImagemGrande;
	private String linkImagemMedia;
	private String linkImagemPequena;
	private double preco;
	private double precoPrmocional;
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getIndicadoPara() {
		return indicadoPara;
	}
	public void setIndicadoPara(String indicadoPara) {
		this.indicadoPara = indicadoPara;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	public String getDescricaoTecnologia() {
		return descricaoTecnologia;
	}
	public void setDescricaoTecnologia(String descricaoTecnologia) {
		this.descricaoTecnologia = descricaoTecnologia;
	}
	public String getComposicao() {
		return composicao;
	}
	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
	public String getGarantiaFabricante() {
		return garantiaFabricante;
	}
	public void setGarantiaFabricante(String garantiaFabricante) {
		this.garantiaFabricante = garantiaFabricante;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public double getPrecoPrmocional() {
		return precoPrmocional;
	}
	public void setPrecoPrmocional(double precoPrmocional) {
		this.precoPrmocional = precoPrmocional;
	}
	public String getLinkImagemGrande() {
		return linkImagemGrande;
	}
	public void setLinkImagemGrande(String linkImagemGrande) {
		this.linkImagemGrande = linkImagemGrande;
	}
	public String getLinkImagemMedia() {
		return linkImagemMedia;
	}
	public void setLinkImagemMedia(String linkImagemMedia) {
		this.linkImagemMedia = linkImagemMedia;
	}
	public String getLinkImagemPequena() {
		return linkImagemPequena;
	}
	public void setLinkImagemPequena(String linkImagemPequena) {
		this.linkImagemPequena = linkImagemPequena;
	}
	
	
}
