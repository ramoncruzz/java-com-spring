package com.candangas.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Catalogo;

public interface CatalogoDAO extends JpaRepository<Catalogo, Long> {

}
