package com.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.ItensPendentes;
import com.mymanager.api.repositories.ItensPendentesRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.ItensPendentesService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class ItensPendentesServiceImpl extends UsuarioLogadoServiceImpl implements ItensPendentesService {
	private static final Logger log = LoggerFactory.getLogger(ItensPendentesServiceImpl.class);
			
	@Autowired
	private ItensPendentesRepository itensPendentesRepository;

	@Override
	public List<ItensPendentes> buscarPorUsuarioID() {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando itensPendentes pelo Usuario ID: {}", usuarioID);
		return this.itensPendentesRepository.findByUsuarioID(usuarioID);
	}
	
}
