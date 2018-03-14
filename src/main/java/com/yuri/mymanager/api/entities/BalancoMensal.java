package com.yuri.mymanager.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yuri.mymanager.api.enums.SituacaoEnum;

@Entity
@Table(name = "balanco_mensal")
public class BalancoMensal implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private SituacaoEnum situacao;
	private Long usuarioID;
	private Integer ano;
	private Integer mes;
	private Double Compra;
	private Double Venda;
	private Double Geral;

	public BalancoMensal() {

	}

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "situacao")
	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	@Column(name = "usuario_id")
	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
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

	@Column(name = "compra")
	public Double getCompra() {
		return Compra;
	}

	public void setCompra(Double compra) {
		Compra = compra;
	}

	@Column(name = "venda")
	public Double getVenda() {
		return Venda;
	}

	public void setVenda(Double venda) {
		Venda = venda;
	}

	@Column(name = "geral")
	public Double getGeral() {
		return Geral;
	}

	public void setGeral(Double geral) {
		Geral = geral;
	}

	@Override
	public String toString() {
		return "BalancoMensal [id=" + id + ", situacao=" + situacao + ", usuarioID=" + usuarioID + ", ano=" + ano
				+ ", mes=" + mes + ", Compra=" + Compra + ", Venda=" + Venda + ", Geral=" + Geral + "]";
	}
}
