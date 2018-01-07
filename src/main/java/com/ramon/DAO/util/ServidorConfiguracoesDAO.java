package com.ramon.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.model.util.ServidorConfiguracoes;

public interface ServidorConfiguracoesDAO extends JpaRepository<ServidorConfiguracoes, Long> {

	ServidorConfiguracoes findById(Long id);
}
