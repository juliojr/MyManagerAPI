package com.yuri.mymanager.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Integrante;

public interface IntegranteRepository extends JpaRepository<Integrante, Long> {
	
	@Transactional(readOnly = true)
	Integrante findByCpfCnpj(String cpfCnpj);
}
