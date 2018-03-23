package com.mymanager.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.BalancoMensal;
import com.mymanager.api.enums.SituacaoEnum;
/**
 * Repositório de acesso aos dados da view
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface BalancoMensalRepository extends JpaRepository<BalancoMensal, Long> {
	
	/* pode ter N metodos combinando usuarioID, mes, ano, situacao. Esperar e implementar só o que vai usar*/
	List<BalancoMensal> findByUsuarioIDAndAnoAndSituacao(Long usuarioID, Integer ano, SituacaoEnum situacao);
	
	Optional<BalancoMensal> findByUsuarioIDAndMesAndAnoAndSituacao(Long usuarioID, Integer mes, Integer ano, SituacaoEnum situacao);
	
}
