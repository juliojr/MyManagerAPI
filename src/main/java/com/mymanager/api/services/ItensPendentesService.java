package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.ItensPendentes;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface ItensPendentesService {
	/**
	 * Retorna uma lista de ItensPendentes
	 * @return List<ItensPendentes>
	 */
	List<ItensPendentes> buscarPorUsuarioID();
}
