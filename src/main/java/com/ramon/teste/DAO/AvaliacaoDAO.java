package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.Avaliacao;

public interface AvaliacaoDAO extends JpaRepository<Avaliacao, Long> {

	Avaliacao findById(Long id);
}
