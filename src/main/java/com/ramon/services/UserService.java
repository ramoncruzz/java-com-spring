package com.ramon.services;

import com.ramon.model.Usuario;

public interface UserService {
	
	public Usuario findByUserName(String username);
	public Long registerUser(Usuario user);
	

}
