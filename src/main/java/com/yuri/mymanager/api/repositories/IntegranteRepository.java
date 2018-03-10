package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Usuario;

@Transactional(readOnly = true)
public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
	
	Integrante findById(Long id);
	
	List<Integrante> findByUsuario(Usuario usuario);
}
