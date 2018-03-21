package com.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.mymanager.api.entities.Integrante;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface IntegranteService {
	/**
	 * Retorna o integrante
	 * @param id
	 * @return Optional<Integrante>
	 */
	Optional<Integrante> buscarPorId(Long id);
	
	/**
	 * Cadastra um novo integrante
	 * @param integrante
	 * @return
	 */
	Integrante persistir(Integrante integrante);
	
	/**
	 * Retorna uma lista de integrantes
	 * @param usuario
	 * @return Optional<Integrante>
	 */
	List<Integrante> buscarPorUsuario();
	
	/**
	 * Remove um Integrante
	 * 
	 * @param Integrante
	 */
	void removerIntegrante(Integrante integrante);
	
}
