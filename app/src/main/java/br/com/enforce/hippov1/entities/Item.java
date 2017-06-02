package br.com.enforce.hippov1.entities;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {

	private Long idProduto;
	private String nomeProduto;
	private Integer qtdProduto;
	private BigDecimal precoVendaItem;

	public Item() {}

	public Item(Long idProduto, Integer qtdProduto, BigDecimal precoVendaItem) {
		this.idProduto = idProduto;
		this.qtdProduto = qtdProduto;
		this.precoVendaItem = precoVendaItem;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(Integer qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public BigDecimal getPrecoVendaItem() {
		return precoVendaItem;
	}

	public void setPrecoVendaItem(BigDecimal precoVendaItem) {
		this.precoVendaItem = precoVendaItem;
	}
}