package com.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.MaisIntegrantes;
import com.mymanager.api.repositories.MaisIntegrantesRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.MaisIntegrantesService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class MaisIntegrantesServiceImpl extends UsuarioLogadoServiceImpl implements MaisIntegrantesService {
	private Logger log = LoggerFactory.getLogger(MaisIntegrantesServiceImpl.class);
	
	@Autowired
	private MaisIntegrantesRepository maisIntegranteRepository;
	
	@Override
	public List<MaisIntegrantes> buscarPorMesEAno(Integer mes, Integer ano) {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando integrantes pelo usuarioID: {} - Mês: {} - Ano: {} ", usuarioID, mes, ano);
		return this.maisIntegranteRepository.findByUsuarioIDAndMesAndAno(usuarioID, mes, ano);
	}

}
