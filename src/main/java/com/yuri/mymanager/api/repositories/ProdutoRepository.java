package com.yuri.mymanager.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Produto;

@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Produto findByDescricao(String descricao);
}
