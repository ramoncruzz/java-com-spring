package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.Marmita;

public interface MarmitaDao extends JpaRepository<Marmita, Long>{
	
	Marmita findById(Long id);

}
