package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Produto;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.repositories.ProdutoRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.ProdutoService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class ProdutoServiceImpl extends UsuarioLogadoServiceImpl implements ProdutoService{
	private static final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Optional<Produto> buscarPorId(Long id) {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando produto por ID: {} e usuario: {}", id, usuario);
		return Optional.ofNullable(this.produtoRepository.findById(id));
	}

	@Override
	public Produto persistir(Produto produto) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		produto.setUsuario(usuarioAutenticado);
		log.info("Persistindo produto: {}", produto);
		return this.produtoRepository.save(produto);
	}

	@Override
	public List<Produto> buscarPorUsuario() {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando produtos por Usuario: {}", usuario);
		return this.produtoRepository.findByUsuario(usuario);
	}

	@Override
	public void removerProduto(Produto produto) {
		log.info("Removendo produto: {}", produto);
		this.produtoRepository.delete(produto.getId());
	}
}
