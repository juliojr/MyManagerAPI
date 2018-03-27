package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.SaldoProdutos;
import com.mymanager.api.repositories.SaldoProdutosRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.SaldoProdutosService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class SaldoProdutosServiceImpl extends UsuarioLogadoServiceImpl implements SaldoProdutosService {
	private Logger log = LoggerFactory.getLogger(SaldoProdutosServiceImpl.class);
	
	@Autowired
	private SaldoProdutosRepository saldoProdutosRepository;
	
	@Override
	public List<SaldoProdutos> buscarPorUsuarioID() {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando saldos. usuarioID: {}", usuarioID);
		return this.saldoProdutosRepository.findByUsuarioID(usuarioID);
	}

	@Override
	public Optional<SaldoProdutos> buscarPorUsuarioIDEProdutoID(Long produtoID) {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando ssaldos. usuarioID: {} - produtoID: {}", usuarioID, produtoID);
		return this.saldoProdutosRepository.findByUsuarioIDAndProdutoID(usuarioID, produtoID);
	}

}
