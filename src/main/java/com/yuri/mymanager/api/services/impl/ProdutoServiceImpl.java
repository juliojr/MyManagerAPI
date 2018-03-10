package com.yuri.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.repositories.ProdutoRepository;
import com.yuri.mymanager.api.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	private static final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Optional<Produto> buscarPorId(Long id) {
		log.info("Buscando produto por ID: {}", id);
		return Optional.ofNullable(this.produtoRepository.findById(id));
	}

	@Override
	public Produto persistir(Produto produto) {
		log.info("Persistindo produto: {}", produto);
		return this.produtoRepository.save(produto);
	}

	@Override
	public List<Produto> buscarPorUsuario(Usuario usuario) {
		log.info("Buscando produtos por Usuario: {}", usuario);
		return this.produtoRepository.findByUsuario(usuario);
	}

	@Override
	public void removeProduto(Produto produto) {
		log.info("Removendo produto: {}", produto);
		this.produtoRepository.delete(produto.getId());
	}
}
