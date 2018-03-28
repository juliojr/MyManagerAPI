package com.mymanager.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.dtos.ItemDto;
import com.mymanager.api.entities.Item;
import com.mymanager.api.entities.Produto;
import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.enums.SituacaoEnum;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.ItemService;
import com.mymanager.api.services.ProdutoService;
import com.mymanager.api.services.CabecalhoService;

/**
 * Controller para interação da entidade Item
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/item")
@CrossOrigin(origins = "*")
public class ItemController {
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private ItemService itemService;
	@Autowired
	private CabecalhoService cabecalhoService;
	@Autowired
	private ProdutoService produtoService;

	public ItemController() {
	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * Cadastra um Item no sistema
	 * 
	 * @param itemDto
	 * @param result
	 * @return ResponseEntity<Response<ItemDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<ItemDto>> cadastrar(
			// atribui o requestBody para o dto, validando, de acordo com as anotações
			@Valid @RequestBody ItemDto itemDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Cadastrando item: {}", itemDto);
		Response<ItemDto> response = new Response<ItemDto>();

		validarDadosExistentes(itemDto, result);
		Item item = this.converterDtoParaItem(itemDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do item: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.itemService.persistir(item);

		response.setData(this.converterItemParaDto(item));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna uma lista de ItemsDto de acordo com o Id do cabecalho e o usuario
	 * Logado
	 * 
	 * @return ResponseEntity<Response<List<ItemDto>>>
	 */
	@GetMapping(value = "/cab/{id}")
	public ResponseEntity<Response<List<ItemDto>>> buscarPorCabecalho(
			// pega variável da URL
			@PathVariable("id") Long id,
			// identifica o resultado da validação do DTO
			BindingResult result) {
		Response<List<ItemDto>> response = new Response<List<ItemDto>>();

		Optional<Cabecalho> cabecalho = this.cabecalhoService.buscarPorId(id);

		List<Item> items = new ArrayList<Item>();

		if (!cabecalho.isPresent()) {
			log.error("Erro validando dados de cabecalho do item: {}", result.getAllErrors());
			response.getErrors().add("Cabecalho não encontrado.");
			return ResponseEntity.badRequest().body(response);
		} else {
			items = itemService.buscarPorCabecalho(cabecalho.get());
		}

		if (items.isEmpty()) {
			log.info("Nenhum item encontrado.");
			response.getErrors().add("Nenhum item encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<ItemDto> itensDto = new ArrayList<ItemDto>();

		items.stream().sorted((i1, i2) -> i1.getDataPagamento().compareTo(i2.getDataPagamento()))
				.forEach(item -> itensDto.add(this.converterItemParaDto(item)));

		response.setData(itensDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de busca do Item por ID
	 * 
	 * @param id
	 * @return ResponseEntity<Response<ItemDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<ItemDto>> buscarPorId(
			// pega variável da URL
			@PathVariable("id") Long id) {

		log.info("Buscando item pelo ID: {}", id);
		Response<ItemDto> response = new Response<ItemDto>();

		Optional<Item> item = itemService.buscarPorId(id);

		if (!item.isPresent()) {
			log.info("Item não encontrado para o ID: {}", id);
			response.getErrors().add("Item não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterItemParaDto(item.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de atualização do item
	 * 
	 * @param id
	 * @param itemDto
	 * @param result
	 * @return ResponseEntity<Response<ItemDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ItemDto>> atualizar(
			// Pega variavel da URL
			@PathVariable("id") Long id,
			// atribui o requestBody para o dto, validando, de acordo com as anotações do
			// DTO
			@Valid @RequestBody ItemDto itemDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Atualizando item: {}", itemDto);
		Response<ItemDto> response = new Response<ItemDto>();

		Optional<Item> item = this.itemService.buscarPorId(id);

		if (!item.isPresent()) {
			log.error("Erro validando Item: {}", item);
			response.getErrors().add("item não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}

		this.atualizarDadosItem(item.get(), itemDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando Item: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.itemService.persistir(item.get());
		response.setData(this.converterItemParaDto(item.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Remove um item
	 * 
	 * @param id
	 * @param itemDto
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(
			// Pega variavel da URL
			@PathVariable("id") Long id) {
		log.info("Deletando item para o ID: {}", id);

		Response<String> response = new Response<String>();
		Optional<Item> item = this.itemService.buscarPorId(id);

		if (!item.isPresent()) {
			log.info("Erro ao remover devido ao Item ID: {} ser inválido", id);
			response.getErrors().add("Erro ao remover item. Item não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.itemService.removeItem(item.get());
		return ResponseEntity.ok(response);
	}

	/**************************************************************
	 * METODOS COMPLEMENTARES *
	 **************************************************************/

	/**
	 * Validações especificas
	 * 
	 * @param ItemDto
	 * @param result
	 */
	public void validarDadosExistentes(ItemDto itemDto, BindingResult result) {
		if (!EnumUtils.isValidEnum(SituacaoEnum.class, itemDto.getSituacao())) {
			result.addError(new ObjectError("situacao", "situacao Inválida!"));
		}
	}

	/**
	 * converte um ItemDto para Item
	 * 
	 * @param ItemDto
	 * @return Item
	 */
	public Item converterDtoParaItem(ItemDto itemDto, BindingResult result) {
		Item item = new Item();

		this.atualizarDadosItem(item, itemDto, result);

		return item;
	}

	/**
	 * Converte um Item para um ItemDto
	 * 
	 * @param item
	 * @return ItemDto
	 */
	public ItemDto converterItemParaDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setCabecalhoId(item.getCabecalho().getId());
		itemDto.setSituacao(item.getSituacao().toString());
		itemDto.setProdutoId(item.getProduto().getId());
		itemDto.setQuantidade(item.getQuantidade());
		itemDto.setUnitario(item.getUnitario());
		itemDto.setDataPagamento(dateFormat.format(item.getDataPagamento()));

		return itemDto;
	}

	/**
	 * Atualiza os dados do item, baseado no Dto recebido
	 * 
	 * @param item
	 * @param itemDto
	 * @param result
	 */
	public void atualizarDadosItem(Item item, ItemDto itemDto, BindingResult result) {
		Optional<Cabecalho> cabecalho = this.cabecalhoService.buscarPorId(itemDto.getCabecalhoId());

		if (!cabecalho.isPresent()) {
			result.addError(new ObjectError("cabecalho", "cabecalho inválido."));
		} else {
			item.setCabecalho(cabecalho.get());
		}

		Optional<Produto> produto = this.produtoService.buscarPorId(itemDto.getProdutoId());

		if (!produto.isPresent()) {
			result.addError(new ObjectError("produto", "Produto inválido."));
		} else {
			item.setProduto(produto.get());
		}

		if (EnumUtils.isValidEnum(SituacaoEnum.class, itemDto.getSituacao())) {
			item.setSituacao(SituacaoEnum.valueOf(itemDto.getSituacao()));
		} else {
			result.addError(new ObjectError("situacao", "situacao inválida."));
		}

		item.setId(itemDto.getId());
		item.setQuantidade(itemDto.getQuantidade());
		item.setUnitario(itemDto.getUnitario());

		try {
			item.setDataPagamento(this.dateFormat.parse(itemDto.getDataPagamento()));
		} catch (ParseException e) {
			e.printStackTrace();
			result.addError(new ObjectError("dataPagamento", "data pagamento inválida."));
		}
	}
}
