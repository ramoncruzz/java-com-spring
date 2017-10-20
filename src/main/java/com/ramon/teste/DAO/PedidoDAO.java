package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {

}
