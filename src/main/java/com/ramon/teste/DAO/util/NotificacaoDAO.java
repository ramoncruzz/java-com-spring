package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.Notificacoes;

public interface NotificacaoDAO extends JpaRepository<Notificacoes, Long>{
	
	Notificacoes findById(Long id);

}
