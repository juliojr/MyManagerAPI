package com.yuri.mymanager.api.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.enums.TipoEnum;
import com.yuri.mymanager.api.repositories.CabecalhoRepository;
import com.yuri.mymanager.api.services.CabecalhoService;

@Service
public class CabecalhoServiceImpl implements CabecalhoService {
	private static final Logger log = LoggerFactory.getLogger(CabecalhoServiceImpl.class);
	
	@Autowired
	private CabecalhoRepository cabecalhoRepository;
	
	@Autowired
	private ItemServiceImpl itemService;
	
	
	@Override
	public Cabecalho persistir(Cabecalho cabecalho) {
		log.info("Persistindo cabecalho: {}", cabecalho);
		return this.cabecalhoRepository.save(cabecalho);
	}
	
	@Override
	public Optional<Cabecalho> buscarPorId(Long id) {
		log.info("Buscando cabecalho por ID: {}", id); 
		return Optional.ofNullable(this.cabecalhoRepository.findById(id));
	}

	@Override
	public List<Cabecalho> buscarPorIntegrante(Integrante integrante) {
		log.info("Buscando cabecalhos por Integrante: {}", integrante); 
		return this.cabecalhoRepository.findByIntegrante(integrante);
	}

	@Override
	public List<Cabecalho> buscarPorTipoEUsuario(TipoEnum tipo, Usuario usuario) {
		log.info("Buscando cabecalhos por Tipo: {} e Usuario: {}", tipo, usuario); 
		return this.cabecalhoRepository.findByTipoAndUsuario(tipo, usuario);
	}

	@Override
	public List<Cabecalho> buscarPorDataMovimentoEUsuario(Date dataMovimento, Usuario usuario) {
		log.info("Buscando cabecalhos por Data: {} e Usuario: {}", dataMovimento, usuario); 
		return this.cabecalhoRepository.findByDataMovimentoAndUsuario(dataMovimento, usuario);
	}

	@Override
	public List<Cabecalho> buscarPorUsuario(Usuario usuario) {
		log.info("Buscando cabecalhos por Usuario: {}", usuario);
		return this.cabecalhoRepository.findByUsuario(usuario);
	}

	@Override
	public void removeCabecalho(Cabecalho cabecalho) {
		log.info("Removendo Cabecalho: {}", cabecalho);
		this.cabecalhoRepository.delete(cabecalho.getId());
	}

	@Override
	public void removeItensECabecalho(Cabecalho cabecalho) {
		log.info("Removendo Itens e Cabecalho: {}", cabecalho);
		//tenta remover primeiro os itens
		this.itemService.removeItensPorCabecalho(cabecalho);
		
		//depois remove o cabecalho
		this.cabecalhoRepository.delete(cabecalho.getId());
	}
}
