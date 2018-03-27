package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.entities.SaldoProdutos;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.SaldoProdutosService;

/**
 * Controller para interação da entidade SaldoProdutos
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/saldo-produtos")
@CrossOrigin(origins = "*")
public class SaldoProdutosController {
	private static final Logger log = LoggerFactory.getLogger(SaldoProdutosController.class);

	@Autowired
	private SaldoProdutosService saldoProdutosService;

	public SaldoProdutosController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna uma lista de SaldoProdutos
	 * 
	 * @return ResponseEntity<Response<List<SaldoProdutos>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<SaldoProdutos>>> buscarPorUsuario() {

		log.info("Buscando SaldoProdutos");
		Response<List<SaldoProdutos>> response = new Response<List<SaldoProdutos>>();

		List<SaldoProdutos> lista = new ArrayList<SaldoProdutos>();

		this.saldoProdutosService.buscarPorUsuarioID().stream()
				.sorted((s1, s2) -> s1.getProdutoID().compareTo(s2.getProdutoID()))
				.forEach(saldoProdutos -> lista.add(saldoProdutos));

		response.setData(lista);
		return ResponseEntity.ok(response);
	}

	/**
	 * retorna um SaldoProdutos
	 * 
	 * @return ResponseEntity<Response<SaldoProdutos>>
	 */
	@GetMapping(value = "/{produtoID}")
	public ResponseEntity<Response<SaldoProdutos>> buscarPorUsuarioEProdutoID(
			// pega variável da URL
			@PathVariable("produtoID") Long produtoID) {

		log.info("Buscando SaldoProdutos por produtoID: {}", produtoID);
		Response<SaldoProdutos> response = new Response<SaldoProdutos>();

		Optional<SaldoProdutos> saldoProdutos = this.saldoProdutosService.buscarPorUsuarioIDEProdutoID(produtoID);

		if (!saldoProdutos.isPresent()) {
			log.error("Produto não encontrado!");
			response.getErrors().add("Produto não encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(saldoProdutos.get());
		return ResponseEntity.ok(response);
	}

}
