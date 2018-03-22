package com.mymanager.api.dtos;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO responsável pela integração do front com o back, e por validações
 * simples. Representa a entidade Produto
 * 
 * @author Yuri Oliveira
 *
 */
public class ProdutoDto {
	private Long id;
	private String descricao;
	private String caminhoFoto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message = "Descrição não pode ser vazio.")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	@Override
	public String toString() {
		return "ProdutoDto [id=" + id + ", descricao=" + descricao + ", caminhoFoto=" + caminhoFoto + "]";
	}

}
