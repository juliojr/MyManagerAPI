package com.mymanager.api.services;
import java.util.List;
import java.util.Optional;

import com.mymanager.api.entities.SaldoProdutos;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface SaldoProdutosService {
	
	/**
	 * Retorna uma lista de saldos
	 * @return List<SaldoProdutos>
	 */
	List<SaldoProdutos> buscarPorUsuarioID();
	
	/**
	 * retonar um saldo
	 * @param produtoID
	 * @return Optional<SaldoProdutos>
	 */
	Optional<SaldoProdutos> buscarPorUsuarioIDEProdutoID(Long produtoID);
}
