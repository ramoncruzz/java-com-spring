package com.ramon.teste.services;

import com.ramon.teste.model.Usuario;

public interface UserService {
	
	public Usuario findByUserName(String username);
	public Long registerUser(Usuario user);
	

}
