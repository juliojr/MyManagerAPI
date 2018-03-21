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
	 * @param usuarioID
	 * @param mes
	 * @param ano
	 * @return List<MaisProdutos>
	 */
	List<MaisProdutos> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes,Integer ano);
}
