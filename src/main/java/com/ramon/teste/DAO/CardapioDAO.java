package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.Cardapio;

public interface CardapioDAO extends JpaRepository<Cardapio, Long> {

	Cardapio findById(Long id);
}
