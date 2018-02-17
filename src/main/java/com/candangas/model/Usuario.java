package com.candangas.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.candangas.helpers.AtributoJson;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.Endereco;
import com.candangas.security.Autorizacao;

@Entity
public class Usuario implements UserDetails {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	private String sobreNome;
	private String telefoneFixo;
	private String telefoneCelular;
	private String cpf;
	private String tipo;
	
	@ManyToOne
	private Autorizacao authorities;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String linkfotoUsuario;
	
	@ManyToOne
	private Endereco endereco;
	
	private boolean ativo;
	
	@Transient
	private String codigoValidacao;
	
	@Transient
	private Long idEndereco;

	@Column(nullable=true)
	private String tokenPushNotification;

	@AtributoJson
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.authorities = autorizacao;
	}

	@AtributoJson
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@AtributoJson
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}
	
	@AtributoJson
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@AtributoJson
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(authorities);
		return roles;
	}
	@AtributoJson
	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		
		return ativo;
	}
	
	public String getCodigoValidacao() {
		return codigoValidacao;
	}

	public void setCodigoValidacao(String codigoValidacao) {
		this.codigoValidacao = codigoValidacao;
	}
	@AtributoJson
	public String getTokenPushNotification() {
		return tokenPushNotification;
	}

	public void setTokenPushNotification(String tokenPushNotification) {
		this.tokenPushNotification = tokenPushNotification;

	}
	@AtributoJson
	public String getLinkfotoUsuario() {
		return linkfotoUsuario;
	}

	public void setLinkfotoUsuario(String linkfotoUsuario) {
		this.linkfotoUsuario = linkfotoUsuario;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	@AtributoJson
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	@AtributoJson
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setAuthorities(Autorizacao authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}
	@AtributoJson
	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}
}
