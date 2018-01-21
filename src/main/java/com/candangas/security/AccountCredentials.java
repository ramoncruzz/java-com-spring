package com.candangas.security;

public class AccountCredentials {
	
	private String userName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
