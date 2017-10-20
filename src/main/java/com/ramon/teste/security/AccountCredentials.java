package com.ramon.teste.security;

public class AccountCredentials {
	
	private String username;
	private String password;
	private String tokenFacebook;
	private String tokenGoogle;
	
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
