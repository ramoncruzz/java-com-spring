package com.candangas.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {

	Usuario findById(Long id);
	Usuario findByUsername(String userName);
	
}
