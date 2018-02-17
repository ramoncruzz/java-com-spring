package com.candangas.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.candangas.helpers.AtributoJson;
import com.candangas.helpers.JsonString;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String CEP;
	private String logradouro;
	private String pontoDeReferencia;
	private String bairro;
	
	@Transient
	private String nomeCidade;
	@Transient
	private Integer codIbgeCidde;
	
	@ManyToOne
	private Cidade cidade;
	@AtributoJson
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@AtributoJson
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	@AtributoJson
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	@AtributoJson
	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}
	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	@AtributoJson
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	@AtributoJson
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	@AtributoJson
	public String getNomeCidade() {
		return nomeCidade;
	}
	
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	@AtributoJson
	public Integer getCodIbgeCidde() {
		return codIbgeCidde;
	}
	public void setCodIbgeCidde(Integer codIbgeCidde) {
		this.codIbgeCidde = codIbgeCidde;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
	
	
}
