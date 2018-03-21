package com.mymanager.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.mymanager.api.enums.TipoEnum;

import javax.persistence.Id;
/**
 * Mapeamento da view no banco de dados.
 * Tratamento identico ao de tabelas, com @Id como identificador.
 * Usado UUID_Short() no MySql para retornar um identificador unico para o registro.
 * @author Yuri Oliveira
 *
 */
@Entity
@Table(name="mais_produtos")
public class MaisProdutos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long Id;
	private Long usuarioID;
	private Integer mes;
	private Integer ano;
	private TipoEnum tipo;
	private Long produtoID;
	private String descricao;
	private Double quantidade;
	private Double valorTotal;
	
	public MaisProdutos() {
		
	}

	@Id
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	@Column(name="usuario_id")
	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	@Column(name="mes")
	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	@Column(name="ano")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
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

	@Column(name = "valor_total")
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "MaisProdutos [Id=" + Id + ", usuarioID=" + usuarioID + ", mes=" + mes + ", ano=" + ano + ", tipo="
				+ tipo + ", produtoID=" + produtoID + ", descricao=" + descricao + ", quantidade=" + quantidade
				+ ", valorTotal=" + valorTotal + "]";
	}
	
	
	
	
}
