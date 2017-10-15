package com.ramon.teste.security;

public class AccountCredentials {
	
	private String username;
	private String password;
	private String role;
	private String tokenFacebook;
	
	public String getTokenFacebook() {
		return tokenFacebook;
	}

	public void setTokenFacebook(String tokenFacebook) {
		this.tokenFacebook = tokenFacebook;
	}

	public String getTokenGoogle() {
		return tokenGoogle;
	}

	public void setTokenGoogle(String tokenGoogle) {
		this.tokenGoogle = tokenGoogle;
	}

	private String tokenGoogle;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
