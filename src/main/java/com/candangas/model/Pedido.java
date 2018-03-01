package com.candangas.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.candangas.helpers.AtributoJson;
import com.candangas.helpers.JsonString;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nomeVendedor;
	private String usernameVendedor;
	private String dataHora;
	private String tipoVenda;
	private String nomeCliente;
	private String emailCliente;
	private String telefoneCliente;
	private String codigoPedido;
	private boolean finalizado;
	private boolean cancelado;
	private double valorTotal;
	
	@ManyToMany
	@JoinTable(name="pedido_produtos", joinColumns=@JoinColumn(name="pedido_id",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="produto_id",referencedColumnName="id"))
	private List<Produto>produtos;
	
	@AtributoJson
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@AtributoJson
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	@AtributoJson
	public String getUsernameVendedor() {
		return usernameVendedor;
	}
	public void setUsernameVendedor(String usernameVendedor) {
		this.usernameVendedor = usernameVendedor;
	}
	@AtributoJson
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	@AtributoJson
	public String getTipoVenda() {
		return tipoVenda;
	}
	
	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	@AtributoJson
	public String getEmailCliente() {
		return emailCliente;
	}
	
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	@AtributoJson
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	@AtributoJson
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@AtributoJson
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	@AtributoJson
	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	@AtributoJson
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	@Override
	public String toString() {
		return JsonString.geraJsonString(this);
	}

	@AtributoJson
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@AtributoJson
	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
}
