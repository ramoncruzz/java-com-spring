package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Regiao;

public interface RegiaoDAO extends JpaRepository<Regiao, Long> {

	Regiao findById(Long id);
}
