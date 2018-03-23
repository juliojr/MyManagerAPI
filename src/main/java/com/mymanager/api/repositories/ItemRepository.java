package com.mymanager.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Item;
import com.mymanager.api.entities.Produto;
import com.mymanager.api.entities.Usuario;
/**
 * Repositório de acesso aos dados da tabela
 * @author Yuri Oliveira
 *
 */
@Transactional(readOnly = true)
public interface ItemRepository extends JpaRepository<Item, Long> {
	Optional<Item> findByIdAndUsuario(Long id, Usuario usuario);
	
	List<Item> findByCabecalhoAndUsuario(Cabecalho cabecalho, Usuario usuario);

	List<Item> findByProdutoAndUsuario(Produto produto, Usuario usuario);	
}
