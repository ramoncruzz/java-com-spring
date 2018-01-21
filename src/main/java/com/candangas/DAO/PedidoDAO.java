package com.candangas.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {

}
