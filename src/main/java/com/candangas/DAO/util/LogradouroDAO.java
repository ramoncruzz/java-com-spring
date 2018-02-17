package com.candangas.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.Logradouro;

public interface LogradouroDAO extends JpaRepository<Logradouro, Long> {
	List<Logradouro> findByDescricaoBairro(String bairro);
	Logradouro findByCep(String cep);
}
