package com.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.Integrante;
import com.mymanager.api.entities.Usuario;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
	
	Integrante findByIdAndUsuario(Long id, Usuario usuario);
	
	List<Integrante> findByUsuario(Usuario usuario);
}
