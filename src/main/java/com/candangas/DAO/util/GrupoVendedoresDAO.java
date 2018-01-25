package com.candangas.DAO.util;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candangas.model.util.GrupoVendedores;

public interface GrupoVendedoresDAO  extends JpaRepository<GrupoVendedores, Long>{

	GrupoVendedores findByNome(String nome);
}
