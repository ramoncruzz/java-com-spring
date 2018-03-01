package com.candangas.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {

	Pedido findById(Long id);
	Pedido findByCodigoPedido(String codigoPedido);
	List<Pedido> findByUsernameVendedor(String usernameVendedor);
	List<Pedido> findByFinalizadoTrue();
	List<Pedido> findByFinalizadoFalse();
	List<Pedido> findByFinalizadoTrueAndUsernameVendedor(String usernameVendedor);
	List<Pedido> findByFinalizadoFalseAndUsernameVendedor(String usernameVendedor);
	long count();
}
