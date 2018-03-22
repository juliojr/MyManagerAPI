package com.mymanager.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Usuario;
import com.mymanager.api.security.JwtUserFactory;
import com.mymanager.api.services.UsuarioService;

/**
 * implementação da interface especifica do JwtUser para o Spring Security
 * 
 * @author Yuri Oliveira
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	private Usuario usuarioLogado = new Usuario();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(username);

		if (usuario.isPresent()) {
			this.setUsuarioLogado(usuario.get());
			return JwtUserFactory.create(usuario.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
