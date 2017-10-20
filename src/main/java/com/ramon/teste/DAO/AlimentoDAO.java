package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.teste.model.Alimento;

public interface AlimentoDAO extends JpaRepository<Alimento, Long> {

}
