package com.base.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.security.Autorizacao;

public interface AutorizacaoDAO extends JpaRepository<Autorizacao, Long> {

	Autorizacao findById(Long id);
	Autorizacao findByNome(String nome);
}
