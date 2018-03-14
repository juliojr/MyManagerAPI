package com.yuri.mymanager.api.services;

import java.util.List;

import com.yuri.mymanager.api.entities.MaisProdutos;

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
