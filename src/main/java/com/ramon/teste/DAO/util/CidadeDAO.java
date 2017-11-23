package com.ramon.teste.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.util.Cidade;

public interface CidadeDAO extends JpaRepository<Cidade, Long> {

}
