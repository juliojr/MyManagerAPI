package com.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Item;
import com.mymanager.api.entities.Produto;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByCabecalho(Cabecalho cabecalho);

	List<Item> findByProduto(Produto produto);	
}
