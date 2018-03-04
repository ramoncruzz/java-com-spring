package com.base.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.ServidorConfiguracoes;

public interface ServidorConfiguracoesDAO extends JpaRepository<ServidorConfiguracoes, Long> {

	ServidorConfiguracoes findById(Long id);
	ServidorConfiguracoes findFirstByOrderByIdDesc();
}
