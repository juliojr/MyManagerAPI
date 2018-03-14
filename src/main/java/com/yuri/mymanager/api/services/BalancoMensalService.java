package com.yuri.mymanager.api.services;

import java.util.List;

import com.yuri.mymanager.api.entities.BalancoMensal;

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
