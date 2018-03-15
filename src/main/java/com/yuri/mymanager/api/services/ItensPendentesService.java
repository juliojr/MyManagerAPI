package com.yuri.mymanager.api.services;

import java.util.List;

import com.yuri.mymanager.api.entities.ItensPendentes;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface ItensPendentesService {
	/**
	 * Retorna uma lista de ItensPendentes
	 * @Param usuarioID
	 * @return List<ItensPendentes>
	 */
	List<ItensPendentes> buscarPorusuarioID(Long usuarioID);
}
