package com.candangas.model.util;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StatusPedido {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	private String codigoPedido;
	private String nomeVendedor;
	private String userNameVendedor;
	//Key: DataHora  // Value: mensagem Status
	private HashMap<String,String> statusComData;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	public String getUserNameVendedor() {
		return userNameVendedor;
	}
	public void setUserNameVendedor(String userNameVendedor) {
		this.userNameVendedor = userNameVendedor;
	}
	public HashMap<String, String> getStatusComData() {
		return statusComData;
	}
	public void setStatusComData(HashMap<String, String> statusComData) {
		this.statusComData = statusComData;
	}
	

}
