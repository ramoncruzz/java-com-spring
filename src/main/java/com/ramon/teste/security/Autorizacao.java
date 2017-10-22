package com.ramon.teste.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;


@Entity
public class Autorizacao implements GrantedAuthority{
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return nome;
	}

	
	

}
