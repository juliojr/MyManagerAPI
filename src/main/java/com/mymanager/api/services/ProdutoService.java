package com.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.mymanager.api.entities.Produto;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface ProdutoService {
	/**
	 * Retorna o produto
	 * @param id
	 * @return Optional<Produto>
	 */
	Optional<Produto> buscarPorId(Long id);
	
	/**
	 * Cadastra um novo produto
	 * @param produto
	 * @return Produto
	 */
	Produto persistir(Produto produto);
	
	/**
	 * Retorna uma lista de produtos
	 * @return Optional<Produto>
	 */
	List<Produto> buscarPorUsuario();
	
	/**
	 * Remove um Produto
	 * 
	 * @param Produto
	 */
	void removerProduto(Produto produto);

}
