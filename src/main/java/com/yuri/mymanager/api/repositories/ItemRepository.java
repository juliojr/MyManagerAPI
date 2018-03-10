package com.yuri.mymanager.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Item;
import com.yuri.mymanager.api.entities.Produto;

@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByCabecalho(Cabecalho cabecalho);

	List<Item> findByProduto(Produto produto);	
}
