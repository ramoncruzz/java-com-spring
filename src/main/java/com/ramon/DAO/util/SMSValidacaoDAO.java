package com.ramon.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.model.util.SMSValidacao;

public interface SMSValidacaoDAO extends JpaRepository<SMSValidacao, Long>{
	
	List<SMSValidacao> findByTelefone(String telefone);

}
