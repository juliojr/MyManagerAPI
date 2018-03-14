package com.yuri.mymanager.api.services;

import java.util.List;

import com.yuri.mymanager.api.entities.MaisIntegrantes;

public interface MaisIntegrantesService {
	/**
	 * retorna uma lista de integrantes
	 * @param usuarioID
	 * @param mes
	 * @param ano
	 * @return List<MaisIntegrantes>
	 */
	List<MaisIntegrantes> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes,Integer ano);	
}