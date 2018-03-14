package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.mymanager.api.entities.MaisIntegrantes;

public interface MaisIntegrantesRepository extends JpaRepository<MaisIntegrantes, Long> {
	/* pode ter N metodos combinando usuarioID, mes, ano. Esperar e implementar só o que vai usar*/
	List<MaisIntegrantes> findByUsuarioIDAndMesAndAno(Long usuarioID, Integer mes, Integer ano);
}
