package com.base.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.base.helpers.JsonString;

@Entity
public class Logradouro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idLogradouro;
	private String cep;
	private String tipo;
	private String descricao;
	private String UF;
	private String complemento;
	private String descricaoSemNumero;
	private String descricaoCidade;
	private String codigoCidadeIbge;
	private String descricaoBairro;
	
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDescricaoSemNumero() {
		return descricaoSemNumero;
	}

	public void setDescricaoSemNumero(String descricaoSemNumero) {
		this.descricaoSemNumero = descricaoSemNumero;
	}

	public String getDescricaoCidade() {
		return descricaoCidade;
	}

	public void setDescricaoCidade(String descricaoCidade) {
		this.descricaoCidade = descricaoCidade;
	}

	public String getCodigoCidadeIbge() {
		return codigoCidadeIbge;
	}

	public void setCodigoCidadeIbge(String codigoCidadeIbge) {
		this.codigoCidadeIbge = codigoCidadeIbge;
	}

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
}
