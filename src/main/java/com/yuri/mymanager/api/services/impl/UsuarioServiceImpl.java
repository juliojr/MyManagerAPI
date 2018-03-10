package com.yuri.mymanager.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.repositories.UsuarioRepository;
import com.yuri.mymanager.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		log.info("Buscando usuario pelo ID {}", id);
		return Optional.ofNullable(usuarioRepository.findById(id));
	}

	@Override
	public Usuario persistir(Usuario usuario) {
		log.info("Persistindo usuario: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando usuario pelo Email {}", email);
		return Optional.ofNullable(usuarioRepository.findByEmail(email));
	}

	@Override
	public void removeUsuario(Usuario usuario) {
		log.info("Removendo usuario: {}", usuario);
		this.usuarioRepository.delete(usuario.getId());
	}
}
