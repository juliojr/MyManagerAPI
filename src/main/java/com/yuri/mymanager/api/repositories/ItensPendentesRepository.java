package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.ItensPendentes;

@Transactional(readOnly = true)
public interface ItensPendentesRepository extends JpaRepository<ItensPendentes, Long> {
	List<ItensPendentes> findByUsuarioID(Long usuarioID);
}
