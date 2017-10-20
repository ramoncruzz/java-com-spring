package com.ramon.teste.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.teste.model.Bebida;

public interface BebidaDAO extends JpaRepository<Bebida, Long> {

}
