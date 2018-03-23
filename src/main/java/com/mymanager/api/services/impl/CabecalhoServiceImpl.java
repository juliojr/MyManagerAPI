package com.mymanager.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Integrante;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.enums.TipoEnum;
import com.mymanager.api.repositories.CabecalhoRepository;
import com.mymanager.api.security.services.UsuarioLogadoServiceImpl;
import com.mymanager.api.services.CabecalhoService;
import com.mymanager.api.services.ItemService;
/**
 * implementação da interface especifica de acesso ao repositorio
 * @author Yuri Oliveira
 *
 */
@Service
public class CabecalhoServiceImpl extends UsuarioLogadoServiceImpl implements CabecalhoService {
	private static final Logger log = LoggerFactory.getLogger(CabecalhoServiceImpl.class);
	
	@Autowired
	private CabecalhoRepository cabecalhoRepository;
	
	@Autowired
	private ItemService itemService;
	
	
	@Override
	public Cabecalho persistir(Cabecalho cabecalho) {
		Usuario usuarioAutenticado = this.getusuarioAutenticado().get();
		cabecalho.setUsuario(usuarioAutenticado);
		log.info("Persistindo cabecalho: {}", cabecalho);
		return this.cabecalhoRepository.save(cabecalho);
	}
	
	@Override
	public Optional<Cabecalho> buscarPorId(Long id) {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando cabecalho por ID: {} e Usuario {}", id, usuario); 
		return Optional.ofNullable(this.cabecalhoRepository.findByIdAndUsuario(id, usuario));
	}

	@Override
	public List<Cabecalho> buscarPorIntegrante(Integrante integrante) {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando cabecalhos por Integrante: {} e Usuario: {}", integrante, usuario); 
		return this.cabecalhoRepository.findByIntegranteAndUsuario(integrante, usuario);
	}

	@Override
	public List<Cabecalho> buscarPorTipo(TipoEnum tipo) {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando cabecalhos por Tipo: {} e Usuario: {}", tipo, usuario); 
		return this.cabecalhoRepository.findByTipoAndUsuario(tipo, usuario);
	}

	@Override
	public List<Cabecalho> buscarPorUsuario() {
		Usuario usuario = this.getusuarioAutenticado().get();
		log.info("Buscando cabecalhos por Usuario: {}", usuario);
		return this.cabecalhoRepository.findByUsuario(usuario);
	}

	@Override
	public void removerCabecalho(Cabecalho cabecalho) {
		log.info("Removendo Cabecalho: {}", cabecalho);
		this.cabecalhoRepository.delete(cabecalho.getId());
	}

	@Override
	public void removerItensECabecalho(Cabecalho cabecalho) {
		log.info("Removendo Itens e Cabecalho: {}", cabecalho);
		//tenta remover primeiro os itens
		this.itemService.removeItensPorCabecalho(cabecalho);
		
		//depois remove o cabecalho
		this.cabecalhoRepository.delete(cabecalho.getId());
	}
}
