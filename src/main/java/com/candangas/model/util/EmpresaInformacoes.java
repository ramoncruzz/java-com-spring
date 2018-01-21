package com.candangas.model.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class EmpresaInformacoes {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	private String nomeFantasia;
	private String nomeEmpresarial;
	private String cnpj;
	private String enderecoSite;
	private String enderecoFacebook;
	private String telefoneContato;
	private String email;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}
	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEnderecoSite() {
		return enderecoSite;
	}
	public void setEnderecoSite(String enderecoSite) {
		this.enderecoSite = enderecoSite;
	}
	public String getEnderecoFacebook() {
		return enderecoFacebook;
	}
	public void setEnderecoFacebook(String enderecoFacebook) {
		this.enderecoFacebook = enderecoFacebook;
	}
	public String getTelefoneContato() {
		return telefoneContato;
	}
	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
