package com.yuri.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.BalancoMensal;
import com.yuri.mymanager.api.repositories.BalancoMensalRepository;
import com.yuri.mymanager.api.services.BalancoMensalService;

@Service
public class BalancoMensalServiceImpl implements BalancoMensalService {
	private Logger log = LoggerFactory.getLogger(BalancoMensalServiceImpl.class);
	
	@Autowired
	private BalancoMensalRepository balancoMensalRepository;
	
	@Override
	public List<BalancoMensal> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes, Integer ano) {
		log.info("Buscando balanços mensais. usuarioID: {} - mes: {} - ano: {}", usuarioID, mes, ano);
		return this.balancoMensalRepository.findByUsuarioIDAndMesAndAno(usuarioID, mes, ano);
	}

}
