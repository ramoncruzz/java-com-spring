package com.ramon.teste.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.util.StatusPedido;


public interface StatusPedidoDAO  extends JpaRepository<StatusPedido, Long>{

	StatusPedido findById(Long id);
	List<StatusPedido> findByUsername(String username);
	StatusPedido findByUsernameAndIdPedido(String username,Long idPedido);
	StatusPedido findByNumeroPedido(String numeroPedido);
	
}
