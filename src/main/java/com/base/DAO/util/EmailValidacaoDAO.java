package com.base.DAO.util;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.EmailValidacao;


public interface EmailValidacaoDAO extends JpaRepository<EmailValidacao, Long> {

	List<EmailValidacao> findByEmail(String email);
	
}
