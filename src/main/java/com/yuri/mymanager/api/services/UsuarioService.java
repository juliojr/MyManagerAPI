package com.yuri.mymanager.api.services;

import java.util.Optional;

import com.yuri.mymanager.api.entities.Usuario;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface UsuarioService {
	/**
	 * Retorna o usuário
	 * @param id
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorId(Long id);
	
	/**
	 * Cadastra um novo usuário
	 * @param usuario
	 * @return
	 */
	Usuario persistir(Usuario usuario);
	
	/**
	 * Retorna o usuário
	 * @param email
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorEmail(String email);
	
	/**
	 * Remove um Usuario
	 * 
	 * @param usuario
	 */
	void removeUsuario(Usuario usuario);
}
