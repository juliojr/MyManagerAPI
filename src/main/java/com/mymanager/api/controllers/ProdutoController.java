package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.dtos.ProdutoDto;
import com.mymanager.api.entities.Produto;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.ProdutoService;

/**
 * Controller para interação da entidade Produto
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
	private static final Logger log = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoService produtoService;

	public ProdutoController() {
	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * Cadastra um Produto no sistema
	 * 
	 * @param ProdutoDto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<ProdutoDto>> cadastrar(
			// atribui o requestBody para o dto, validando, de acordo com as anotações
			@Valid @RequestBody ProdutoDto produtoDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Cadastrando produto: {}", produtoDto);
		Response<ProdutoDto> response = new Response<ProdutoDto>();

		validarDadosExistentes(produtoDto, result);
		Produto produto = this.converterDtoParaProduto(produtoDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do produto: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.produtoService.persistir(produto);

		response.setData(this.converterProdutoParaDto(produto));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna uma lista de ProdutoDto de acordo com o usuário Logado
	 * 
	 * @return ResponseEntity<Response<List<ProdutoDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ProdutoDto>>> buscarPorUsuario() {
		Response<List<ProdutoDto>> response = new Response<List<ProdutoDto>>();

		List<Produto> produtos = produtoService.buscarPorUsuario();

		if (produtos.isEmpty()) {
			log.info("Nenhum produto encontrado.");
			response.getErrors().add("Nenhum produto encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<ProdutoDto> produtosDto = new ArrayList<ProdutoDto>();

		produtos.forEach(produto -> produtosDto.add(this.converterProdutoParaDto(produto)));

		response.setData(produtosDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de busca do Produto por ID
	 * 
	 * @param id
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ProdutoDto>> buscarPorId(
			// pega variável da URL
			@PathVariable("id") Long id) {

		log.info("Buscando produto pelo ID: {}", id);
		Response<ProdutoDto> response = new Response<ProdutoDto>();

		Optional<Produto> produto = produtoService.buscarPorId(id);

		if (!produto.isPresent()) {
			log.info("Produto não encontrado para o ID: {}", id);
			response.getErrors().add("Produto não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterProdutoParaDto(produto.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de atualização do produto
	 * 
	 * @param id
	 * @param produtoDto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ProdutoDto>> atualizar(
			// Pega variavel da URL
			@PathVariable("id") Long id,
			// atribui o requestBody para o dto, validando, de acordo com as anotações do
			// DTO
			@Valid @RequestBody ProdutoDto produtoDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Atualizando produto: {}", produtoDto);
		Response<ProdutoDto> response = new Response<ProdutoDto>();

		Optional<Produto> produto = this.produtoService.buscarPorId(id);

		if (!produto.isPresent()) {
			log.error("Erro validando Produto: {}", produto);
			response.getErrors().add("produto não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}

		this.atualizarDadosProduto(produto.get(), produtoDto, result);

		this.produtoService.persistir(produto.get());
		response.setData(this.converterProdutoParaDto(produto.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Remove um produto
	 * 
	 * @param id
	 * @param produtoDto
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(
			// Pega variavel da URL
			@PathVariable("id") Long id) {
		log.info("Deletando produto para o ID: {}", id);

		Response<String> response = new Response<String>();
		Optional<Produto> produto = this.produtoService.buscarPorId(id);

		if (!produto.isPresent()) {
			log.info("Erro ao remover devido ao Produto ID: {} ser inválido", id);
			response.getErrors().add("Erro ao remover produto. Produto não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.produtoService.removerProduto(produto.get());
		return ResponseEntity.ok(response);
	}

	/**************************************************************
	 * METODOS COMPLEMENTARES *
	 **************************************************************/

	/**
	 * Validações especificas
	 * 
	 * @param ProdutoDto
	 * @param result
	 */
	public void validarDadosExistentes(ProdutoDto produtoDto, BindingResult result) {
		// sem validações especificas
	}

	/**
	 * converte um ProdutoDto para Produto
	 * 
	 * @param ProdutoDto
	 * @return Produto
	 */
	public Produto converterDtoParaProduto(ProdutoDto produtoDto, BindingResult result) {
		Produto produto = new Produto();
		produto.setCaminhoFoto(produtoDto.getCaminhoFoto());
		produto.setDescricao(produtoDto.getDescricao());

		return produto;
	}

	/**
	 * Converte um Produto para um ProdutoDto
	 * 
	 * @param produto
	 * @return ProdutoDto
	 */
	public ProdutoDto converterProdutoParaDto(Produto produto) {
		ProdutoDto produtoDto = new ProdutoDto();
		produtoDto.setCaminhoFoto(produto.getCaminhoFoto());
		produtoDto.setDescricao(produto.getDescricao());
		produtoDto.setId(produto.getId());

		return produtoDto;
	}

	/**
	 * Atualiza os dados do produto, baseado no Dto recebido
	 * 
	 * @param produto
	 * @param produtoDto
	 * @param result
	 */
	public void atualizarDadosProduto(Produto produto, ProdutoDto produtoDto, BindingResult result) {
		produto.setCaminhoFoto(produtoDto.getCaminhoFoto());
		produto.setDescricao(produtoDto.getDescricao());
	}
}
