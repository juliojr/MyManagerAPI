package com.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Integrante;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.enums.TipoEnum;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface CabecalhoRepository extends JpaRepository<Cabecalho, Long> {
	
	Cabecalho findByIdAndUsuario(Long id, Usuario usuario);
	
	List<Cabecalho> findByIntegranteAndUsuario(Integrante integrante, Usuario usuario);

	List<Cabecalho> findByTipoAndUsuario(TipoEnum tipo, Usuario usuario);

	List<Cabecalho> findByUsuario(Usuario usuario);

}
