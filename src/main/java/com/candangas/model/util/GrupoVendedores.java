package com.candangas.model.util;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.candangas.model.Usuario;

@Entity
public class GrupoVendedores {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@ManyToMany
	@JoinTable(name="GrupoVendedores_usuarios",joinColumns=@JoinColumn(name="grupo_vendedores_id",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="usuario_id",referencedColumnName="id"))
	private List<Usuario> vendedores;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Usuario> getVendedores() {
		return vendedores;
	}
	public void setVendedores(List<Usuario> vendedores) {
		this.vendedores = vendedores;
	}

}
