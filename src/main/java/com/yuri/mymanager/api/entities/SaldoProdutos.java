package com.yuri.mymanager.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapeamento da view no banco de dados.
 * Tratamento identico ao de tabelas, com @Id como identificador.
 * Usado UUID_Short() no MySql para retornar um identificador unico para o registro.
 * @author Yuri Oliveira
 *
 */
@Entity
@Table(name="saldo_produtos")
public class SaldoProdutos implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long usuarioID;
	private Long produtoID;
	private String descricao;
	private String caminhoFoto;
	private Double saldo;
	
	@Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="usuario_id")
	public Long getUsuarioID() {
		return usuarioID;
	}
	
	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}
	
	@Column(name="produto_id")
	public Long getProdutoID() {
		return produtoID;
	}

	public void setProdutoID(Long produtoID) {
		this.produtoID = produtoID;
	}
	
	@Column(name="descricao")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="caminho_foto")
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	
	@Column(name="saldo")
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	@Override
	public String toString() {
		return "SaldoProdutos [id=" + id + ", usuarioID=" + usuarioID + ", produtoID=" + produtoID + ", descricao="
				+ descricao + ", caminhoFoto=" + caminhoFoto + ", saldo=" + saldo + "]";
	}
	
	
}
