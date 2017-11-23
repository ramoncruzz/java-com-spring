package com.ramon.teste.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Endereco;

public interface EnderecoDAO extends JpaRepository<Endereco, Long> {

	Endereco findById(Long id);
	
}
