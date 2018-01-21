package com.candangas.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.Produto;

public interface ProdutoDAO  extends JpaRepository<Produto, Long>{

}
