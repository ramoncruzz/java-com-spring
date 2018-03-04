package com.base.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.Endereco;

public interface EnderecoDAO  extends JpaRepository<Endereco, Long>{

	Endereco findById(Long id);
}
