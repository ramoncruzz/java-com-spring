package com.base.DAO.util;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.model.util.Arquivo;

public interface ArquivoDAO extends JpaRepository<Arquivo, Long> {

	Arquivo findByNome(String nome);
	List<Arquivo> findByDiretorio(String diretorio);
}
