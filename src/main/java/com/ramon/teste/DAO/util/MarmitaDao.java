package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.MarmitaMobileRequest;

public interface MarmitaDao extends JpaRepository<MarmitaMobileRequest, Long>{
	
	MarmitaMobileRequest findById(Long id);

}
