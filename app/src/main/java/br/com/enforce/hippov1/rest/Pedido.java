package br.com.enforce.hippov1.rest;

import java.io.Serializable;
import java.util.List;

import br.com.enforce.hippov1.entities.Item;

public class Pedido implements Serializable{

	private Long idPedido;
	private Long idCliente;
	private int idStatus;
//	private Date dataPedido;
	private int idTipoPagto;
	private Long idEndereco;
	private int idAplicacao;
	private List<Item> itens;

	public Pedido() {
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

//	public Date getDataPedido() {
//		return dataPedido;
//	}
//
//	public void setDataPedido(Date dataPedido) {
//		this.dataPedido = dataPedido;
//	}

	public int getIdTipoPagto() {
		return idTipoPagto;
	}

	public void setIdTipoPagto(int idTipoPagto) {
		this.idTipoPagto = idTipoPagto;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public int getIdAplicacao() {
		return idAplicacao;
	}

	public void setIdAplicacao(int idAplicacao) {
		this.idAplicacao = idAplicacao;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
}