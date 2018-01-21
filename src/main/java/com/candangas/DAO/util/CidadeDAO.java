package com.candangas.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.Cidade;

public interface CidadeDAO extends JpaRepository<Cidade, Long>{
	
	Cidade findByCodigoIbge(Integer codigoIbge);
	

}
