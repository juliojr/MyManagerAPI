package com.yuri.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.repositories.IntegranteRepository;
import com.yuri.mymanager.api.services.IntegranteService;

@Service
public class IntegranteServiceImpl implements IntegranteService {
	private static final Logger log = LoggerFactory.getLogger(IntegranteServiceImpl.class);
	
	@Autowired
	private IntegranteRepository integranteRepository;
	
	@Override
	public Optional<Integrante> buscarPorId(Long id) {
		log.info("Buscando integrante por ID: {}", id); 
		return Optional.ofNullable(this.integranteRepository.findById(id));
	}

	@Override
	public Integrante persistir(Integrante integrante) {
		log.info("Persistindo integrante: {}", integrante);
		return this.integranteRepository.save(integrante);
	}

	@Override
	public List<Integrante> buscarPorUsuario(Usuario usuario) {
		log.info("Buscando integrantes por usuario: {}", usuario);
		return this.integranteRepository.findByUsuario(usuario);
	}

	@Override
	public void removeIntegrante(Integrante integrante) {
		log.info("Removendo Integrante: {}", integrante);
		this.integranteRepository.delete(integrante.getId());
	}

}
