package com.mymanager.api.dtos;

public class CabecalhoDto {
	private Long id;
	private Long integranteId;
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIntegranteId() {
		return integranteId;
	}

	public void setIntegranteId(Long integranteId) {
		this.integranteId = integranteId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "CabecalhoDto [id=" + id + ", integranteId=" + integranteId + ", tipo=" + tipo + "]";
	}

}
