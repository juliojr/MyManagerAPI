package com.yuri.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.MaisIntegrantes;
import com.yuri.mymanager.api.repositories.MaisIntegrantesRepository;
import com.yuri.mymanager.api.services.MaisIntegrantesService;

@Service
public class MaisIntegrantesServiceImpl implements MaisIntegrantesService {
	private Logger log = LoggerFactory.getLogger(MaisIntegrantesServiceImpl.class);
	
	@Autowired
	private MaisIntegrantesRepository maisIntegranteRepository;
	
	@Override
	public List<MaisIntegrantes> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes, Integer ano) {
		log.info("Buscando integrantes pelo usuarioID: {} - Mês: {} - Ano: {} ", usuarioID, mes, ano);
		return this.maisIntegranteRepository.findByUsuarioIDAndMesAndAno(usuarioID, mes, ano);
	}

}
