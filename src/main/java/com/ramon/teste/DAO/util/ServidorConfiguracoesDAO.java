package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.ServidorConfiguracoes;

public interface ServidorConfiguracoesDAO extends JpaRepository<ServidorConfiguracoes, Long> {

	ServidorConfiguracoes findById(Long id);
}
