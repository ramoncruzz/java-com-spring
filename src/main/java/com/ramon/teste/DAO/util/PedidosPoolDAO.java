package com.ramon.teste.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.PedidosPool;

public interface PedidosPoolDAO  extends JpaRepository<PedidosPool, Long>{
	
	PedidosPool findById(Long id);
	List<PedidosPool> findByEnviadoParaRestauranteFalse();
	
}
