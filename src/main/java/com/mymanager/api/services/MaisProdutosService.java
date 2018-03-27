package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.MaisProdutos;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface MaisProdutosService {
	/**
	 * retorna uma lista de produtos
	 * @param mes
	 * @param ano
	 * @return List<MaisProdutos>
	 */
	List<MaisProdutos> buscarPorMesEAno(Integer mes,Integer ano);
}
