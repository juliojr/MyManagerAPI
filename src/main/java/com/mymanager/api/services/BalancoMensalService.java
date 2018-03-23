package com.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.mymanager.api.entities.BalancoMensal;
import com.mymanager.api.enums.SituacaoEnum;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface BalancoMensalService {
	/**
	 * retorna uma lista de BalancoMensal
	 * @param ano
	 * @param situacao
	 * @return List<BalancoMensal>
	 */
	List<BalancoMensal> buscarPorAnoESituacao(Integer ano, SituacaoEnum situacao);
	
	/**
	 * Retorna um BalancoMensal
	 * @param mes
	 * @param ano
	 * @return
	 */
	Optional<BalancoMensal> buscarPorMesEAnoESituacao(Integer mes, Integer ano, SituacaoEnum situacao);
}
