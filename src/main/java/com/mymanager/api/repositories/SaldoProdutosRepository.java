package com.mymanager.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.SaldoProdutos;

/**
 * Repositório de acesso aos dados da view
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface SaldoProdutosRepository extends JpaRepository<SaldoProdutos, Long> {
	List<SaldoProdutos> findByUsuarioID(Long usuarioID);
	
	Optional<SaldoProdutos> findByUsuarioIDAndProdutoID(Long usuarioID, Long produtoID);
	
}
