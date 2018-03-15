package com.yuri.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.entities.Usuario;
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
	 * @return
	 */
	Produto persistir(Produto produto);
	
	/**
	 * Retorna uma lista de produtos
	 * @param usuario
	 * @return Optional<Produto>
	 */
	List<Produto> buscarPorUsuario(Usuario usuario);
	
	/**
	 * Remove um Produto
	 * 
	 * @param Produto
	 */
	void removeProduto(Produto produto);

}
