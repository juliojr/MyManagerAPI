package com.yuri.mymanager.api.repositories;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.enums.TipoEnum;

@Transactional(readOnly = true)
@NamedQueries({
		@NamedQuery(name = "CabecalhoRepository.findByIntegranteId",
					query = "SELECT cab FROM Cabecalho cab WHERE cab.integrante.id = :integranteId") 
})
public interface CabecalhoRepository extends JpaRepository<Cabecalho, Long> {
	
	List<Cabecalho> findByIntegranteId(@Param("integranteId") Long integranteId);
	
	List<Cabecalho> findByTipo(TipoEnum tipo);
	
	Cabecalho findByDataMovimento(Date dataMovimento);
		
}
