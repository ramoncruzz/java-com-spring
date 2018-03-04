package com.base.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

	Usuario findById(Long id);
	Usuario findByUsername(String userName);
	
}
