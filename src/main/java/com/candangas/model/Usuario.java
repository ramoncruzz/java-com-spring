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

import com.candangas.model.util.Endereco;
import com.candangas.security.Autorizacao;

@Entity
public class Usuario implements UserDetails {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Autorizacao authorities;
	
	@Column(nullable=false)
	private String nome;
	private String sobreNome;
	private String telefoneFixo;
	private String telefoneCelular;
	private String cpf;
	private String tipo;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String linkImagemGrande;
	private String linkImagemMedia;
	private String linkImagemPequena;
	
	@ManyToOne
	private Endereco endereco;
	
	private boolean ativo;
	
	@Transient
	private String codigoValidacao;

	@Column(nullable=true)
	private String tokenPushNotification;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAutorizacao(Autorizacao autorizacao) {
		this.authorities = autorizacao;
	}

	public String getNomeo() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setUserName(String userName) {
		this.username = userName;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(authorities);
		return roles;
	}

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
		
		return true;
	}

	public String getCodigoValidacao() {
		return codigoValidacao;
	}

	public void setCodigoValidacao(String codigoValidacao) {
		this.codigoValidacao = codigoValidacao;
	}
	
	public String getTokenPushNotification() {
		return tokenPushNotification;
	}

	public void setTokenPushNotification(String tokenPushNotification) {
		this.tokenPushNotification = tokenPushNotification;

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

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
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


}
