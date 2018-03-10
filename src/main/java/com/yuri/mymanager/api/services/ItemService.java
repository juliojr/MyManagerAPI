package com.yuri.mymanager.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Item;
import com.yuri.mymanager.api.entities.Produto;

@Service
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
