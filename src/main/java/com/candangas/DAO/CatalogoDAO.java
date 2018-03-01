package com.candangas.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Catalogo;

public interface CatalogoDAO extends JpaRepository<Catalogo, Long> {

	Catalogo findByCodigoReferencia(String codigoReferencia);
	Catalogo findById(Long id);
	List<Catalogo> findByNome(String nome);
	
	
}
