package com.ramon.teste.security;

import org.springframework.security.core.GrantedAuthority;

public class Roles implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String autority;

	public Roles() {
	}

	public Roles(String autority) {
		this.autority = autority;
	}

	@Override
	public String getAuthority() {
		return this.autority;
	}
}
