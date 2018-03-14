package com.yuri.mymanager.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yuri.mymanager.api.enums.TipoEnum;

@Entity
@Table(name = "itens_pendentes")
public class ItensPendentes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long usuarioID;
	private Long integranteID;
	private String cpfCnpj;
	private String nome;
	private Long cabecalhoID;
	private Date cabecalhoDataCriacao;
	private TipoEnum tipo;
	private Long itemID;
	private Date itemDataCriacao;
	private Date dataPagamento;
	private Long produtoID;
	private String descricao;
	private Double quantidade;
	private Double unitario;
	private Double valorTotal;
	
	public ItensPendentes() {
		
	}
	
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "usuario_id")
	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}
	
	@Column(name = "integrante_id")
	public Long getIntegranteID() {
		return integranteID;
	}

	public void setIntegranteID(Long integranteID) {
		this.integranteID = integranteID;
	}
	
	@Column(name = "cpf_cnpj")
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "cabecalho_id")
	public Long getCabecalhoID() {
		return cabecalhoID;
	}

	public void setCabecalhoID(Long cabecalhoID) {
		this.cabecalhoID = cabecalhoID;
	}

	@Column(name = "cabecalho_data_criacao")
	public Date getCabecalhoDataCriacao() {
		return cabecalhoDataCriacao;
	}

	public void setCabecalhoDataCriacao(Date cabecalhoDataCriacao) {
		this.cabecalhoDataCriacao = cabecalhoDataCriacao;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	@Column(name = "item_id")
	public Long getItemID() {
		return itemID;
	}

	public void setItemID(Long itemID) {
		this.itemID = itemID;
	}

	@Column(name = "item_data_criacao")
	public Date getItemDataCriacao() {
		return itemDataCriacao;
	}

	public void setItemDataCriacao(Date itemDataCriacao) {
		this.itemDataCriacao = itemDataCriacao;
	}

	@Column(name = "data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "produto_id")
	public Long getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(Long produtoID) {
		this.produtoID = produtoID;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "quantidade")
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "unitario")
	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

	@Column(name = "valor_total")
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "ItensPendentes [id=" + id + ", usuarioID=" + usuarioID + ", integranteID=" + integranteID + ", cpfCnpj="
				+ cpfCnpj + ", nome=" + nome + ", cabecalhoID=" + cabecalhoID + ", cabecalhoDataCriacao="
				+ cabecalhoDataCriacao + ", tipo=" + tipo + ", itemID=" + itemID + ", itemDataCriacao="
				+ itemDataCriacao + ", dataPagamento=" + dataPagamento + ", produtoID=" + produtoID + ", descricao="
				+ descricao + ", quantidade=" + quantidade + ", unitario=" + unitario + ", valorTotal=" + valorTotal
				+ "]";
	}
	
}
