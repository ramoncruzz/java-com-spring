package com.ramon.teste.model.util;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class MarmitaMobileRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ElementCollection @Column(length=50)
	private List<String> alimentosEscolhidos;
	
	public List<String> getAlimentosEscolhidos() {
		return alimentosEscolhidos;
	}
	public void setAlimentosEscolhidos(List<String> alimentosEscolhidos) {
		this.alimentosEscolhidos = alimentosEscolhidos;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
