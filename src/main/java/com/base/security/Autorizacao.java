package com.base.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.base.helpers.AtributoJson;
import com.base.helpers.JsonString;


@Entity
public class Autorizacao implements GrantedAuthority{
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String nome;
	
	@AtributoJson
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@AtributoJson
	public String getNome()
	{
		return nome;
	}
	
	@Override
	public String getAuthority() {
		return nome;
	}

	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}

}
