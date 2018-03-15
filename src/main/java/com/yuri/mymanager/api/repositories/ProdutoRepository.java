package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.entities.Usuario;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findById(Long id);
	
	List<Produto> findByUsuario(Usuario usuario);
}
