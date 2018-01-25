package com.candangas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.candangas.helpers.AtributoJson;
import com.candangas.helpers.JsonString;

@Entity
public class Catalogo {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nome;
	private String descricao;
	@Column(unique=true)
	private String codigoReferencia;
	private boolean ativo;
	private boolean exibir;
	private int diaInicio;
	private int mesInicio;
	private int anoInicio;
	private int diaFim;
	private int mesFim;
	private int anoFim;
	private String linkImagemGrande;
	private String linkImagemMedia;
	private String linkImagemPequena;
	
	@ManyToMany
	@JoinTable(name="catalogo_produto",joinColumns=@JoinColumn(name="catalogo_id",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="produto_id",referencedColumnName="id"))
	private List<Produto> listaProdutos;
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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	@AtributoJson
	public boolean isExibir() {
		return exibir;
	}
	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}
	@AtributoJson
	public int getDiaInicio() {
		return diaInicio;
	}
	public void setDiaInicio(int diaInicio) {
		this.diaInicio = diaInicio;
	}
	@AtributoJson
	public int getMesInicio() {
		return mesInicio;
	}
	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}
	@AtributoJson
	public int getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}
	@AtributoJson
	public int getDiaFim() {
		return diaFim;
	}
	public void setDiaFim(int diaFim) {
		this.diaFim = diaFim;
	}
	@AtributoJson
	public int getMesFim() {
		return mesFim;
	}
	public void setMesFim(int mesFim) {
		this.mesFim = mesFim;
	}
	@AtributoJson
	public int getAnoFim() {
		return anoFim;
	}
	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
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
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
}
