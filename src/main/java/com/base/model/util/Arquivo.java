package com.base.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.base.helpers.AtributoJson;
import com.base.helpers.JsonString;


@Entity
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String diretorio;
	private String nome;
	private String tipo;
	private String link;
	
	@AtributoJson
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@AtributoJson
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	@AtributoJson
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@AtributoJson
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@AtributoJson
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@AtributoJson
	public String getFullPathToSave()
	{
		if(this.diretorio!=null)
			return diretorio+"/"+nome+"."+tipo;
		else 
			return nome+"."+tipo;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}

}
