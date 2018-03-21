package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Item;
import com.mymanager.api.entities.Produto;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface ItemService {
	/**
	 * Cadastra um novo Item
	 * @param Item
	 * @return Item
	 */
	Item persistir(Item item);
	
	/**
	 * Retorna uma lista de Itens
	 * 
	 * @param cabecalho
	 * @return List<Item>
	 */
	List<Item> buscarPorCabecalho(Cabecalho cabecalho);

	/**
	 * Retorna uma lista de itens
	 * 
	 * @param produto
	 * @return List<Itens>
	 */
	List<Item> buscarPorProduto(Produto produto);
	
	/**
	 * Remove um Item
	 * 
	 * @Param Item
	 */
	void removeItem(Item item);
	
	/**
	 *Rremove itens pelo Cabecalho
	 * 
	 * @param Cabecalho
	 */
	void removeItensPorCabecalho(Cabecalho cabecalho);
}
