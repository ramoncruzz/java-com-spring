package com.ramon.teste.model.util;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ramon.teste.model.Marmita;

@Entity
public class PedidosMobileRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long numeroPedido;
	private String dataHora;
	private double precoFinal;
	private double taxaEntrega;
	private double taxaConveniencia;
	private String userName;
	private String nomeCompleto;
	private String endereco;
	private String regiaoNome;
	private String pontoReferencia;
	@OneToMany
	private List<Marmita> marmitas;
	@ElementCollection @Column(length=50)
	private List<String>bebidas;
	
	public Long getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public double getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}
	public double getTaxaEntrega() {
		return taxaEntrega;
	}
	public void setTaxaEntrega(double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}
	public double getTaxaConveniencia() {
		return taxaConveniencia;
	}
	public void setTaxaConveniencia(double taxaConveniencia) {
		this.taxaConveniencia = taxaConveniencia;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getRegiaoNome() {
		return regiaoNome;
	}
	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Marmita> getMarmitas() {
		return marmitas;
	}
	public void setMarmitas(List<Marmita> marmitas) {
		this.marmitas = marmitas;
	}
	public List<String> getBebidas() {
		return bebidas;
	}
	public void setBebidas(List<String> bebidas) {
		this.bebidas = bebidas;
	}
	 
	 
}
