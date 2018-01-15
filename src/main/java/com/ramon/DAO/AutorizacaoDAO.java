package com.ramon.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.security.Autorizacao;

public interface AutorizacaoDAO extends JpaRepository<Autorizacao, Long> {

	Autorizacao findById(Long id);
	Autorizacao findByNome(String nome);
}