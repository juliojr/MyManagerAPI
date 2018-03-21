package com.mymanager.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.Usuario;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
	
	Usuario findById(Long id);
	
	Usuario findByEmail(String email);

}
