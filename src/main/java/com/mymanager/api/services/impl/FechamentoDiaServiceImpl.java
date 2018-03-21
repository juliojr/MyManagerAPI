package com.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.FechamentoDia;
import com.mymanager.api.repositories.FechamentoDiaRepository;
import com.mymanager.api.services.FechamentoDiaService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class FechamentoDiaServiceImpl implements FechamentoDiaService {
	
	private Logger log = LoggerFactory.getLogger(FechamentoDiaServiceImpl.class);
	
	@Autowired
	private FechamentoDiaRepository fechamentoDiaRepository;
	
	@Override
	public List<FechamentoDia> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes, Integer ano) {
		log.info("Buscando fechamentos dia. usuarioID: {} - mes: {} - ano: {}", usuarioID, mes, ano);
		return this.fechamentoDiaRepository.findByUsuarioIDAndMesAndAno(usuarioID, mes, ano);
	}

}