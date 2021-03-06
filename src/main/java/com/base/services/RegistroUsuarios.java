package com.base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.base.DAO.AutorizacaoDAO;
import com.base.DAO.UsuarioDAO;
import com.base.model.Usuario;
import com.base.security.Autorizacao;

@Service("userService")
public class RegistroUsuarios implements UserService {

	@Autowired 
	private UsuarioDAO usuarioDao;
	@Autowired
	private AutorizacaoDAO authDao;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	
	@Override
	public Usuario findByUserName(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Long registerUser(Usuario user) {
		
		Autorizacao busca = authDao.findByNome("USER");
		if(busca!=null)
		{
			user.setAutorizacao(busca);
		}else
		{
			Autorizacao auth = new Autorizacao();
			auth.setNome("USER");
			long id =authDao.save(auth).getId();
			auth.setId(id);
			user.setAutorizacao(auth);
			
		}
		String hash =bCrypt.encode(user.getPassword());
		user.setPassword(hash);
		boolean ativo=user.isAtivo();
		user.setAtivo(ativo);
		return usuarioDao.saveAndFlush(user).getId();
		
	}

	@Override
	public Long registerAdminUser(Usuario user) {
		Autorizacao busca = authDao.findByNome("ADMIN");
		if(busca!=null)
		{
			user.setAutorizacao(busca);
		}else
		{
			Autorizacao auth = new Autorizacao();
			auth.setNome("ADMIN");
			long id =authDao.save(auth).getId();
			auth.setId(id);
			user.setAutorizacao(auth);
			
		}
		String hash =bCrypt.encode(user.getPassword());
		user.setPassword(hash);
		user.setAtivo(true);
		
		
		return usuarioDao.saveAndFlush(user).getId();
	}

}
