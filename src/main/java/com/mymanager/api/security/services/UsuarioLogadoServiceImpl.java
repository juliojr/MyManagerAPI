package com.mymanager.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Usuario;

/**
 * Classe pai para controle do Usuario que está autenticado pelo JWT
 * 
 * @author Yuri Oliveira
 *
 */
@Service
public class UsuarioLogadoServiceImpl {
	@Autowired
	private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	/**
	 * busca o Usuario da sessão
	 * 
	 * @return Usuario
	 */
	public Optional<Usuario> getusuarioAutenticado() {
		return Optional.ofNullable(this.jwtUserDetailsServiceImpl.getUsuarioLogado());
	}
}
