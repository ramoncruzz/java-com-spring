package com.candangas.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.Email;

public interface EmailDAO extends JpaRepository<Email, Long>{

	
}
