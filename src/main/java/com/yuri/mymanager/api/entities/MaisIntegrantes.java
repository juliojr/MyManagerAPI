package com.yuri.mymanager.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yuri.mymanager.api.enums.TipoEnum;

@Entity
@Table(name="mais_integrantes")
public class MaisIntegrantes implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long usuarioID;
	private Integer mes;
	private Integer ano;
	private TipoEnum tipo;
	private Long integranteID;
	private String cpfCnpj;
	private String nome;
	private Double quantidade;
	private Double valorTotal;
	
	public MaisIntegrantes() {
		
	}

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
		return "MaisIntegrantes [id=" + id + ", usuarioID=" + usuarioID + ", mes=" + mes + ", ano=" + ano + ", tipo="
				+ tipo + ", integranteID=" + integranteID + ", cpfCnpj=" + cpfCnpj + ", nome=" + nome + ", quantidade="
				+ quantidade + ", valorTotal=" + valorTotal + "]";
	}
	
	
	
}
