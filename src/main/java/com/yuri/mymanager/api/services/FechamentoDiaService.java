package com.yuri.mymanager.api.services;

import java.util.List;

import com.yuri.mymanager.api.entities.FechamentoDia;

public interface FechamentoDiaService {
	/**
	 * retorna uma lista de fechamentos dia
	 * @param usuarioID
	 * @param mes
	 * @param ano
	 * @return List<FfechamentoDia>
	 */
	List<FechamentoDia> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes,Integer ano);
}
