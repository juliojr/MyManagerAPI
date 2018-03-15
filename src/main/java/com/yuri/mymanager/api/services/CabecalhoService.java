package com.yuri.mymanager.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.enums.TipoEnum;
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
	 * @param usuario
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorTipoEUsuario(TipoEnum tipo, Usuario usuario);

	/**
	 * Retorna uma lista de cabecalhos
	 * @param dataMovimento
	 * @param usuario
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorDataMovimentoEUsuario(Date dataMovimento, Usuario usuario);
	
	/**
	 * Retorna uma lista de usuários
	 * @param usuario
	 * @return List<Cabecalho>
	 */
	List<Cabecalho> buscarPorUsuario(Usuario usuario);
	
	/**
	 * Remove um Cabecalho
	 * 
	 * @param Cabecalho
	 */
	void removeCabecalho(Cabecalho cabecalho);
	
	/**
	 * Remove os itens e depois o Cabecalho
	 * 
	 * @param Cabecalho
	 */
	void removeItensECabecalho(Cabecalho cabecalho);
	
	
}
