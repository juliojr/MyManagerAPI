package com.yuri.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.SaldoProdutos;
import com.yuri.mymanager.api.repositories.SaldoProdutosRepository;
import com.yuri.mymanager.api.services.SaldoProdutosService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class SaldoProdutosServiceImpl implements SaldoProdutosService {
	private Logger log = LoggerFactory.getLogger(SaldoProdutosServiceImpl.class);
	
	@Autowired
	private SaldoProdutosRepository saldoProdutosRepository;
	
	@Override
	public List<SaldoProdutos> buscarPorUsuarioID(Long usuarioID) {
		log.info("Buscando ssaldos. usuarioID: {}", usuarioID);
		return this.saldoProdutosRepository.findByUsuarioID(usuarioID);
	}

	@Override
	public Optional<SaldoProdutos> buscarPorUsuarioIDEProdutoID(Long usuarioID, Long produtoID) {
		log.info("Buscando ssaldos. usuarioID: {} - produtoID: {}", usuarioID, produtoID);
		return this.saldoProdutosRepository.findByUsuarioIDAndProdutoID(usuarioID, produtoID);
	}

}
