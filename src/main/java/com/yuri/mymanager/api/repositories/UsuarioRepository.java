package com.yuri.mymanager.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	
	Usuario findByEmail(String email);
	
	Usuario findByNome(String nome);
}
