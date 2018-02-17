package com.candangas.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.Endereco;

public interface EnderecoDAO  extends JpaRepository<Endereco, Long>{

	Endereco findById(Long id);
}
