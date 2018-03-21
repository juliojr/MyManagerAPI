package com.mymanager.api.services;

import java.util.List;

import com.mymanager.api.entities.BalancoMensal;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface BalancoMensalService {
	/**
	 * retorna uma lista de balanços
	 * @param usuarioID
	 * @param mes
	 * @param ano
	 * @return List<BalancoMensal>
	 */
	List<BalancoMensal> buscarPorUsuarioIDEMesEAno(Long usuarioID, Integer mes,Integer ano);
}
