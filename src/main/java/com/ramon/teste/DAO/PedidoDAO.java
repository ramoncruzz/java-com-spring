package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Pedido;
import com.ramon.teste.model.Usuario;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {

	Pedido findById(Long id);
	Pedido findByUsuario(Usuario usuario);
}
