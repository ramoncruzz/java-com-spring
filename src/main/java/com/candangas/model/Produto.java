package com.candangas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.candangas.helpers.AtributoJson;
import com.candangas.helpers.JsonString;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	@Column(unique=true)
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
	private String linkSiteVendas;
	private double preco;
	private double precoPormocional;
	private boolean ativo;
	
	@AtributoJson
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@AtributoJson
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@AtributoJson
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@AtributoJson
	public String getCodigoReferencia() {
		return codigoReferencia;
	}
	public void setCodigoReferencia(String codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}
	@AtributoJson
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	@AtributoJson
	public String getIndicadoPara() {
		return indicadoPara;
	}
	public void setIndicadoPara(String indicadoPara) {
		this.indicadoPara = indicadoPara;
	}
	@AtributoJson
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@AtributoJson
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	@AtributoJson
	public String getTecnologia() {
		return tecnologia;
	}
	public void setTecnologia(String tecnologia) {
		this.tecnologia = tecnologia;
	}
	@AtributoJson
	public String getDescricaoTecnologia() {
		return descricaoTecnologia;
	}
	public void setDescricaoTecnologia(String descricaoTecnologia) {
		this.descricaoTecnologia = descricaoTecnologia;
	}
	@AtributoJson
	public String getComposicao() {
		return composicao;
	}
	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}
	@AtributoJson
	public String getGarantiaFabricante() {
		return garantiaFabricante;
	}
	public void setGarantiaFabricante(String garantiaFabricante) {
		this.garantiaFabricante = garantiaFabricante;
	}
	@AtributoJson
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	@AtributoJson
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@AtributoJson
	public double getPrecoPromocional() {
		return precoPormocional;
	}
	public void setPrecoPromocional(double precoPormocional) {
		this.precoPormocional = precoPormocional;
	}
	@AtributoJson
	public String getLinkImagemGrande() {
		return linkImagemGrande;
	}
	public void setLinkImagemGrande(String linkImagemGrande) {
		this.linkImagemGrande = linkImagemGrande;
	}
	@AtributoJson
	public String getLinkImagemMedia() {
		return linkImagemMedia;
	}
	public void setLinkImagemMedia(String linkImagemMedia) {
		this.linkImagemMedia = linkImagemMedia;
	}
	@AtributoJson
	public String getLinkImagemPequena() {
		return linkImagemPequena;
	}
	public void setLinkImagemPequena(String linkImagemPequena) {
		this.linkImagemPequena = linkImagemPequena;
	}
	
	@AtributoJson
	public String getLinkSiteVendas() {
		return linkSiteVendas;
	}
	public void setLinkSiteVendas(String linkSiteVendas) {
		this.linkSiteVendas = linkSiteVendas;
	}
	@AtributoJson
	public double getPrecoPormocional() {
		return precoPormocional;
	}
	public void setPrecoPormocional(double precoPormocional) {
		this.precoPormocional = precoPormocional;
	}
	
	@AtributoJson
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
	
}
