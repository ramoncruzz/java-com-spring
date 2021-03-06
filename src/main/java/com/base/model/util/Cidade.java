package com.base.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.base.helpers.AtributoJson;
import com.base.helpers.JsonString;

@Entity
public class Cidade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCidade;
	private String descricao;
	private String uf;
	private Integer codigoIbge;
	private String ddd;
	
	@AtributoJson
	public Integer getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}
	@AtributoJson
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@AtributoJson
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@AtributoJson
	public Integer getCodigoIbge() {
		return codigoIbge;
	}
	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	@AtributoJson
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
}
