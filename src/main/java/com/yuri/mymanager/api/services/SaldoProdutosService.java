package com.yuri.mymanager.api.services;
import java.util.List;
import java.util.Optional;

import com.yuri.mymanager.api.entities.SaldoProdutos;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface SaldoProdutosService {
	
	/**
	 * Retorna uma lista de saldos
	 * @param usuarioID
	 * @return List<SaldoProdutos>
	 */
	List<SaldoProdutos> buscarPorUsuarioID(Long usuarioID);
	
	/**
	 * retonar um saldo
	 * @param usuarioID
	 * @param produtoID
	 * @return Optional<SaldoProdutos>
	 */
	Optional<SaldoProdutos> buscarPorUsuarioIDEProdutoID(Long usuarioID, Long produtoID);
}
