package com.yuri.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.ItensPendentes;
import com.yuri.mymanager.api.repositories.ItensPendentesRepository;
import com.yuri.mymanager.api.services.ItensPendentesService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class ItensPendentesServiceImpl implements ItensPendentesService {
	private static final Logger log = LoggerFactory.getLogger(ItensPendentesServiceImpl.class);
			
	@Autowired
	private ItensPendentesRepository itensPendentesRepository;

	@Override
	public List<ItensPendentes> buscarPorusuarioID(Long usuarioID) {
		log.info("Buscando itensPendentes pelo Usuario ID: {}", usuarioID);
		return this.itensPendentesRepository.findByUsuarioID(usuarioID);
	}
	
}
