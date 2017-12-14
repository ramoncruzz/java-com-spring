package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.SMSValidacao;

public interface SMSValidacaoDAO extends JpaRepository<SMSValidacao, Long>{
	
	SMSValidacao findByTelefone(String telefone);

}
