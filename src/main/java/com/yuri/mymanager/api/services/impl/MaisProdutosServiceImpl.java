package com.yuri.mymanager.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.MaisProdutos;
import com.yuri.mymanager.api.repositories.MaisProdutosRepository;
import com.yuri.mymanager.api.services.MaisProdutosService;

@Service
public class MaisProdutosServiceImpl implements MaisProdutosService {
	private Logger log = LoggerFactory.getLogger(MaisProdutosServiceImpl.class);
	
	@Autowired
	private MaisProdutosRepository maisProdutosRepository;
	
	@Override
	public List<MaisProdutos> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes, Integer ano) {
		log.info("Buscando produtos pelo usuarioID: {} - Mês: {} - Ano: {} ", usuarioID, mes, ano);
		return this.maisProdutosRepository.findByUsuarioIDAndMesAndAno(usuarioID, mes, ano);
	}

}
