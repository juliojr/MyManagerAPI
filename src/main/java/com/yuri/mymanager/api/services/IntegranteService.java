package com.yuri.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Usuario;
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
	List<Integrante> buscarPorUsuario(Usuario usuario);
	
	/**
	 * Remove um Integrante
	 * 
	 * @param Integrante
	 */
	void removeIntegrante(Integrante integrante);
	
}
