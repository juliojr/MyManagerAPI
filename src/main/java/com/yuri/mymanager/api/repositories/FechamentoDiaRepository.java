package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.FechamentoDia;

@Transactional(readOnly = true)
public interface FechamentoDiaRepository extends JpaRepository<FechamentoDia, Long> {
	
	/* pode ter N metodos combinando usuarioID, mes, ano, situacao. Esperar e implementar só o que vai usar*/
	List<FechamentoDia> findByUsuarioIDAndMesAndAno(Long usuarioID, Integer mes, Integer ano);
	
}
