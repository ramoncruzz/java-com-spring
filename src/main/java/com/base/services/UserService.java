package com.base.services;

import com.base.model.Usuario;

public interface UserService {
	
	public Usuario findByUserName(String username);
	public Long registerUser(Usuario user);
	public Long registerAdminUser(Usuario user);
	

}
