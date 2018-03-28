package com.mymanager.api.services;

import java.util.Optional;

import com.mymanager.api.entities.Usuario;
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
	 * @return Usuario
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
