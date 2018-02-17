package com.candangas.services;

import com.candangas.model.Usuario;

public interface UserService {
	
	public Usuario findByUserName(String username);
	public Long registerUser(Usuario user);
	public Long registerAdminUser(Usuario user);
	

}
