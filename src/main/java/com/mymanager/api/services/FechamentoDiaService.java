package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.FechamentoDia;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface FechamentoDiaService {
	/**
	 * retorna uma lista de fechamentos dia
	 * @param usuarioID
	 * @param mes
	 * @param ano
	 * @return List<FfechamentoDia>
	 */
	List<FechamentoDia> buscarPorMesEAno(Integer mes,Integer ano);
}
