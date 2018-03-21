package com.mymanager.api.repositories;

import java.util.Date;
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
	
	Cabecalho findById(Long id);
	
	List<Cabecalho> findByIntegrante(Integrante integrante);

	List<Cabecalho> findByTipoAndUsuario(TipoEnum tipo, Usuario usuario);

	List<Cabecalho> findByDataMovimentoAndUsuario(Date dataMovimento, Usuario usuario);

	List<Cabecalho> findByUsuario(Usuario usuario);

}
