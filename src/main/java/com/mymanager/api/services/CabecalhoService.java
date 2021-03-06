package com.mymanager.api.services;

import java.util.List;
import java.util.Optional;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Integrante;
import com.mymanager.api.enums.TipoEnum;
/**
 * Interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
public interface CabecalhoService {
	/**
	 * Cadastra um novo Cabecalho
	 * @param Integrante
	 * @return Cabecalho
	 */
	Cabecalho persistir(Cabecalho cabecalho);
	
	/**
	 * Retorna o cabecalho
	 * @param id
	 * @return Optional<Cabecalho>
	 */
	Optional<Cabecalho> buscarPorId(Long id);
	
	/**
	 * Retorna uma lista de cabecalhos
	 * @param integrante
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorIntegrante(Integrante integrante);
	
	/**
	 * Retorna uma lista de cabecalhos
	 * @param tipo
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorTipo(TipoEnum tipo);
	
	/**
	 * Retorna uma lista de usuários
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorUsuario();
	
	/**
	 * Remove um Cabecalho
	 * 
	 * @param Cabecalho
	 */
	void removerCabecalho(Cabecalho cabecalho);
	
	/**
	 * Remove os itens e depois o Cabecalho
	 * 
	 * @param Cabecalho
	 */
	void removerItensECabecalho(Cabecalho cabecalho);
	
	
}
