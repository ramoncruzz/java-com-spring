package com.ramon.teste.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ramon.teste.security.Autorizacao;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Autorizacao autorizacao;
	
	@Column(nullable=false)
	private String nomeCompleto;
	
	private Integer cpf;
	
	@ElementCollection @Column(length=15)
	private List<String> telefone;
	
	@OneToMany
	private List<Endereco> endereco;
	
	private String userName;
	
	private String password;
	private boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public List<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<String> telefone) {
		this.telefone = telefone;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
