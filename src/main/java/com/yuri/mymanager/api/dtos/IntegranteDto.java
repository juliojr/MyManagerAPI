package com.yuri.mymanager.api.dtos;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import com.yuri.mymanager.api.enums.TipoIntegranteEnum;

public class IntegranteDto {
	private Long id;
	private String cpf;
	private String cnpj;	
	private String nome;
	private Integer ddd;
	private Long telefone;
	private String uf;
	private String cidade;
	private String bairro;
	private String rua;
	private Integer numero;
	private String Complemento;
	private TipoIntegranteEnum tipoIntegrante;
	
	public IntegranteDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@CPF(message = "CPF invalido.")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@CNPJ(message = "CNPJ invalido.")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotEmpty(message = "Nome não pode ser vazio.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	@NotEmpty(message = "Estado não pode ser vazio.")
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@NotEmpty(message = "Cidade não pode ser vazia.")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotEmpty(message = "Bairro não pode ser vazio.")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotEmpty(message = "Rua não pode ser vazia.")
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}
	
	
	public TipoIntegranteEnum getTipoIntegrante() {
		return tipoIntegrante;
	}

	public void setTipoIntegrante(TipoIntegranteEnum tipoIntegrante) {
		this.tipoIntegrante = tipoIntegrante;
	}

	@Override
	public String toString() {
		return "CadastroIntegranteDto [id=" + id + ", cpf=" + cpf + ", cnpj=" + cnpj + ", nome=" + nome + ", ddd=" + ddd
				+ ", telefone=" + telefone + ", uf=" + uf + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua
				+ ", numero=" + numero + ", Complemento=" + Complemento + ", tipoIntegrante=" + tipoIntegrante + "]";
	}
	
	
	
	
}
