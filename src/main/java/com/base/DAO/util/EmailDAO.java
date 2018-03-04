package com.base.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.Email;

public interface EmailDAO extends JpaRepository<Email, Long>{

	
}
