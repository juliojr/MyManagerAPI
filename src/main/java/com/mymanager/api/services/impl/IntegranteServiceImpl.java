package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Integrante;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.repositories.IntegranteRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.IntegranteService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class IntegranteServiceImpl extends UsuarioLogadoServiceImpl  implements IntegranteService {
	private static final Logger log = LoggerFactory.getLogger(IntegranteServiceImpl.class);
	
	@Autowired
	private IntegranteRepository integranteRepository;
	
	@Override
	public Optional<Integrante> buscarPorId(Long id) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("Buscando integrante por ID: {} e usuario: {}", id, usuarioAutenticado); 
		return Optional.ofNullable(this.integranteRepository.findByIdAndUsuario(id, usuarioAutenticado));
	}

	@Override
	public Integrante persistir(Integrante integrante) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		integrante.setUsuario(usuarioAutenticado);
		log.info("Persistindo integrante: {}", integrante);
		return this.integranteRepository.save(integrante);
	}

	@Override
	public List<Integrante> buscarPorUsuario() {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		log.info("Buscando integrantes por usuario: {}", usuarioAutenticado);
		return this.integranteRepository.findByUsuario(usuarioAutenticado);
	}

	@Override
	public void removerIntegrante(Integrante integrante) {
		log.info("Removendo Integrante: {}", integrante);
		Long id = integrante.getId();
		this.integranteRepository.delete(id);
	}

}
