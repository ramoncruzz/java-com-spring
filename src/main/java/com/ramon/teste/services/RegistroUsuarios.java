package com.ramon.teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.ramon.teste.DAO.UsuarioDAO;
import com.ramon.teste.model.Usuario;

@Service("userService")
public class RegistroUsuarios implements UserService {

	@Autowired 
	private UsuarioDAO usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	
	@Override
	public Usuario findByUserName(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Long registerUser(Usuario user) {
		
		user.setPassword(bCrypt.encode(user.getPassword()));
		user.setAtivo(true);
		Usuario u = usuarioDao.findByUsername(user.getUsername());
		if(u!=null)
			return 0L;
		else
			return usuarioDao.saveAndFlush(user).getId();
		
	}

}
