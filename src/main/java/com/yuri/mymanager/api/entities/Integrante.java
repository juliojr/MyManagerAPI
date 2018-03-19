package com.yuri.mymanager.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.yuri.mymanager.api.enums.TipoIntegranteEnum;
/**
 * Representação do model da tabela no banco de dados.
 * @author Yuri Oliveira
 *
 */
@Entity
@Table(name = "integrante")
public class Integrante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String cpfCnpj;
	private String nome;
	private Integer ddd;
	private Long telefone;
	private String uf;
	private String cidade;
	private String bairro;
	private String rua;
	private Integer numero;
	private String Complemento;
	private Usuario usuario;
	private Date dataCriacao;
	private TipoIntegranteEnum tipoIntegrante;
	private List<Cabecalho> cabecalhos;

	public Integrante() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "cpf_cnpj", nullable = false)
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ddd", nullable = false)
	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	@Column(name = "telefone", nullable = false)
	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	@Column(name = "uf", nullable = false)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "cidade", nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "bairro", nullable = false)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "rua", nullable = false)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@Column(name = "numero", nullable = false)
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "complemento", nullable = false)
	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	@Column(name = "tipo_integrante")
	@Enumerated(EnumType.STRING)
	public TipoIntegranteEnum getTipoIntegrante() {
		return tipoIntegrante;
	}

	public void setTipoIntegrante(TipoIntegranteEnum tipoIntegrante) {
		this.tipoIntegrante = tipoIntegrante;
	}

	@OneToMany(mappedBy = "integrante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Cabecalho> getCabecalhos() {
		return cabecalhos;
	}

	public void setCabecalhos(List<Cabecalho> cabecalhos) {
		this.cabecalhos = cabecalhos;
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
	}

	@Override
	public String toString() {
		return "Integrante [id=" + id + ", cpfCnpj=" + cpfCnpj + ", nome=" + nome + ", ddd=" + ddd + ", telefone="
				+ telefone + ", uf=" + uf + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero="
				+ numero + ", Complemento=" + Complemento + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao
				+ "]";
	}
	
	
	
}
