package com.yuri.mymanager.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yuri.mymanager.api.enums.SituacaoEnum;

@Entity
@Table(name="fechamento_dia")
public class FechamentoDia implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long usuarioID;
	private Date dataPagamento;
	private SituacaoEnum situacao;
	private Double valorTotal;
	private Integer ano;
	private Integer mes;
	private Integer dia;
	
	public FechamentoDia() {
		
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
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_pagamento")
	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Column(name = "valor_total")
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Column(name = "ano")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Column(name = "mes")
	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	@Column(name = "dia")
	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "FechamentoDia [id=" + id + ", usuarioID=" + usuarioID + ", dataPgamento=" + dataPagamento + ", situacao="
				+ situacao + ", valorTotal=" + valorTotal + ", ano=" + ano + ", mes=" + mes + ", dia=" + dia + "]";
	}
	
	
}
