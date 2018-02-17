package com.candangas.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.Cidade;

public interface CidadeDAO extends JpaRepository<Cidade, Long>{
	
	List<Cidade> findByCodigoIbge(Integer codigoIbge);
	Cidade findByIdCidade(Integer idCidade);

}
