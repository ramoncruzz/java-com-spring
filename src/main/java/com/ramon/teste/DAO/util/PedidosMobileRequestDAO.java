package com.ramon.teste.DAO.util;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.PedidosMobileRequest;

public interface PedidosMobileRequestDAO extends JpaRepository<PedidosMobileRequest, Long> {
	
	PedidosMobileRequest findById(Long id);
	
}
