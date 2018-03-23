package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.BalancoMensal;
import com.mymanager.api.enums.SituacaoEnum;
import com.mymanager.api.repositories.BalancoMensalRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.BalancoMensalService;

/**
 * implementação da interface especifica de acesso ao repositorio
 * 
 * @author Yuri Oliveira
 *
 */
@Service
public class BalancoMensalServiceImpl extends UsuarioLogadoServiceImpl implements BalancoMensalService {
	private Logger log = LoggerFactory.getLogger(BalancoMensalServiceImpl.class);

	@Autowired
	private BalancoMensalRepository balancoMensalRepository;

	@Override
	public List<BalancoMensal> buscarPorAnoESituacao(Integer ano, SituacaoEnum situacao) {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando balanços mensais. usuarioID: {} - ano: {} - situacao: {}", usuarioID, ano, situacao);
		return this.balancoMensalRepository.findByUsuarioIDAndAnoAndSituacao(usuarioID, ano, situacao);
	}

	@Override
	public Optional<BalancoMensal> buscarPorMesEAnoESituacao(Integer mes, Integer ano, SituacaoEnum situacao) {
		Long usuarioID = this.getusuarioAutenticado().get().getId();
		log.info("Buscando balanços mensais. usuarioID: {} - ano: {} - situacão: {}", usuarioID, ano, situacao);
		return this.balancoMensalRepository.findByUsuarioIDAndMesAndAnoAndSituacao(usuarioID, mes, ano, situacao);
	}

}
