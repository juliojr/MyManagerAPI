package com.mymanager.api.dtos;

import org.hibernate.validator.constraints.NotEmpty;

public class ItemDto {
	private Long id;
	private Long cabecalhoId;
	private Long produtoId;
	private Double quantidade;
	private Double unitario;
	private String dataPagamento;
	private String situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCabecalhoId() {
		return cabecalhoId;
	}

	public void setCabecalhoId(Long cabecalhoId) {
		this.cabecalhoId = cabecalhoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

	@NotEmpty(message = "Data Pagamento não pode ser vazio.")
	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@NotEmpty(message = "Situacao não pode ser vazio.")
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "ItemDto [id=" + id + ", cabecalhoId=" + cabecalhoId + ", produtoId=" + produtoId + ", quantidade="
				+ quantidade + ", unitario=" + unitario + ", dataPagamento=" + dataPagamento + ", situacao=" + situacao
				+ "]";
	}

}
