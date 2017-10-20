package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Endereco;

public interface EnderecoDAO extends JpaRepository<Endereco, Long> {

	Endereco findById(Long id);
}
