package com.candangas.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.SMSValidacao;

public interface SMSValidacaoDAO extends JpaRepository<SMSValidacao, Long>{
	
	List<SMSValidacao> findByTelefone(String telefone);

}
