package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.MaisIntegrantes;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface MaisIntegrantesService {
	/**
	 * retorna uma lista de integrantes
	 * @param mes
	 * @param ano
	 * @return List<MaisIntegrantes>
	 */
	List<MaisIntegrantes> buscarPorMesEAno(Integer mes,Integer ano);	
}
