package com.yuri.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Item;
import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.repositories.ItemRepository;
import com.yuri.mymanager.api.services.ItemService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item persistir(Item item) {
		log.info("Persistindo item: {}", item);
		return this.itemRepository.save(item);
	}
	
	@Override
	public List<Item> buscarPorCabecalho(Cabecalho cabecalho) {
		log.info("Buscando itens por Cabecalho: {}", cabecalho); 
		return this.itemRepository.findByCabecalho(cabecalho);
	}

	@Override
	public List<Item> buscarPorProduto(Produto produto) {
		log.info("Buscando itens por Produto: {}", produto); 
		return this.itemRepository.findByProduto(produto);
	}

	@Override
	public void removeItem(Item item) {
		log.info("Removendo Item: {}", item); 
		this.itemRepository.delete(item.getId());		
	}
	
	@Override
	public void removeItensPorCabecalho(Cabecalho cabecalho) {
		log.info("Removendo itens pelo cabecaçho: {}", cabecalho); 
		List<Item > lista = this.itemRepository.findByCabecalho(cabecalho);
		
		lista.forEach(item -> removeItem(item));		
	}
	
}
