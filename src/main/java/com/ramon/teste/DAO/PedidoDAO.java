package com.ramon.teste.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Pedido;


public interface PedidoDAO extends JpaRepository<Pedido, Long> {

	Pedido findById(Long id);
	List<Pedido> findByUserName(String usuario);
}
