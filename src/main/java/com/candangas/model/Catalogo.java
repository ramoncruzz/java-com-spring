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
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public boolean isExibir() {
		return exibir;
	}
	public void setExibir(boolean exibir) {
		this.exibir = exibir;
	}
	public int getDiaInicio() {
		return diaInicio;
	}
	public void setDiaInicio(int diaInicio) {
		this.diaInicio = diaInicio;
	}
	public int getMesInicio() {
		return mesInicio;
	}
	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}
	public int getAnoInicio() {
		return anoInicio;
	}
	public void setAnoInicio(int anoInicio) {
		this.anoInicio = anoInicio;
	}
	public int getDiaFim() {
		return diaFim;
	}
	public void setDiaFim(int diaFim) {
		this.diaFim = diaFim;
	}
	public int getMesFim() {
		return mesFim;
	}
	public void setMesFim(int mesFim) {
		this.mesFim = mesFim;
	}
	public int getAnoFim() {
		return anoFim;
	}
	public void setAnoFim(int anoFim) {
		this.anoFim = anoFim;
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
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	
}
