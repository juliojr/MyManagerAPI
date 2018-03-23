package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Item;
import com.mymanager.api.entities.Produto;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.repositories.ItemRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.ItemService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class ItemServiceImpl extends UsuarioLogadoServiceImpl implements ItemService {
	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Optional<Item> buscarPorId(Long id){
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("buscando item pelo id: {} e Usuario: {}", id, usuarioAutenticado);
		return this.itemRepository.findByIdAndUsuario(id, usuarioAutenticado);
	}
	
	@Override
	public Item persistir(Item item) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		item.setUsuario(usuarioAutenticado);
		log.info("Persistindo item: {}", item);
		return this.itemRepository.save(item);
	}
	
	@Override
	public List<Item> buscarPorCabecalho(Cabecalho cabecalho) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("Buscando itens por Cabecalho: {} e Usuario: {}", cabecalho, usuarioAutenticado); 
		return this.itemRepository.findByCabecalhoAndUsuario(cabecalho, usuarioAutenticado);
	}

	@Override
	public List<Item> buscarPorProduto(Produto produto) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("Buscando itens por Produto: {} e Usuario: {}", produto, usuarioAutenticado); 
		return this.itemRepository.findByProdutoAndUsuario(produto, usuarioAutenticado);
	}

	@Override
	public void removeItem(Item item) {
		log.info("Removendo Item: {}", item); 
		this.itemRepository.delete(item.getId());		
	}
	
	@Override
	public void removeItensPorCabecalho(Cabecalho cabecalho) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("Removendo itens pelo cabecaçho: {}", cabecalho); 
		List<Item > lista = this.itemRepository.findByCabecalhoAndUsuario(cabecalho, usuarioAutenticado);
		
		lista.forEach(item -> removeItem(item));		
	}
	
}
