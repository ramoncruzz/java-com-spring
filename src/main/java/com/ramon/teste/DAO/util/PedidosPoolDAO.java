package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.PedidosPool;

public interface PedidosPoolDAO  extends JpaRepository<PedidosPool, Long>{
	
	PedidosPool findById(Long id);
	
}
