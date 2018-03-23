package com.mymanager.api.controllers;

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

import com.mymanager.api.dtos.CabecalhoDto;
import com.mymanager.api.entities.Cabecalho;
import com.mymanager.api.entities.Integrante;
import com.mymanager.api.enums.TipoEnum;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.CabecalhoService;
import com.mymanager.api.services.IntegranteService;

/**
 * Controller para interação da entidade Cabecalho
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/cabecalho")
@CrossOrigin(origins = "*")
public class CabecalhoController {
	private static final Logger log = LoggerFactory.getLogger(CabecalhoController.class);

	@Autowired
	private CabecalhoService cabecalhoService;
	@Autowired
	private IntegranteService integranteService;

	public CabecalhoController() {
	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * Cadastra um Cabecalho no sistema
	 * 
	 * @param cabecalhoDto
	 * @param result
	 * @return ResponseEntity<Response<CabecalhoDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<CabecalhoDto>> cadastrar(
			// atribui o requestBody para o dto, validando, de acordo com as anotações
			@Valid @RequestBody CabecalhoDto cabecalhoDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Cadastrando cabecalho: {}", cabecalhoDto);
		Response<CabecalhoDto> response = new Response<CabecalhoDto>();

		validarDadosExistentes(cabecalhoDto, result);
		Cabecalho cabecalho = this.converterDtoParaCabecalho(cabecalhoDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do cabecalho: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.cabecalhoService.persistir(cabecalho);

		response.setData(this.converterCabecalhoParaDto(cabecalho));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna uma lista de CabecalhosDto de acordo com o usuário Logado
	 * 
	 * @return ResponseEntity<Response<List<CabecalhoDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<CabecalhoDto>>> buscarPorUsuario() {
		Response<List<CabecalhoDto>> response = new Response<List<CabecalhoDto>>();

		List<Cabecalho> cabecalhos = cabecalhoService.buscarPorUsuario();

		if (cabecalhos.isEmpty()) {
			log.info("Nenhum cabecalho encontrado.");
			response.getErrors().add("Nenhum cabecalho encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<CabecalhoDto> cabecalhosDto = new ArrayList<CabecalhoDto>();

		cabecalhos
			.stream().sorted((c1, c2) -> c1.getId().compareTo(c2.getId()))
				.forEach(cabecalho -> cabecalhosDto.add(this.converterCabecalhoParaDto(cabecalho)));
		
		response.setData(cabecalhosDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de busca do Cabecalho por ID
	 * 
	 * @param id
	 * @return ResponseEntity<Response<CabecalhoDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<CabecalhoDto>> buscarPorId(
			// pega variável da URL
			@PathVariable("id") Long id) {

		log.info("Buscando cabecalho pelo ID: {}", id);
		Response<CabecalhoDto> response = new Response<CabecalhoDto>();

		Optional<Cabecalho> cabecalho = cabecalhoService.buscarPorId(id);

		if (!cabecalho.isPresent()) {
			log.info("Cabecalho não encontrado para o ID: {}", id);
			response.getErrors().add("Cabecalho não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterCabecalhoParaDto(cabecalho.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de atualização do cabecalho
	 * 
	 * @param id
	 * @param cabecalhoDto
	 * @param result
	 * @return ResponseEntity<Response<CabecalhoDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CabecalhoDto>> atualizar(
			// Pega variavel da URL
			@PathVariable("id") Long id,
			// atribui o requestBody para o dto, validando, de acordo com as anotações do
			// DTO
			@Valid @RequestBody CabecalhoDto cabecalhoDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Atualizando cabecalho: {}", cabecalhoDto);
		Response<CabecalhoDto> response = new Response<CabecalhoDto>();

		Optional<Cabecalho> cabecalho = this.cabecalhoService.buscarPorId(id);

		if (!cabecalho.isPresent()) {
			log.error("Erro validando Cabecalho: {}", cabecalho);
			response.getErrors().add("cabecalho não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}

		this.atualizarDadosCabecalho(cabecalho.get(), cabecalhoDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando item: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.cabecalhoService.persistir(cabecalho.get());
		response.setData(this.converterCabecalhoParaDto(cabecalho.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Remove um cabecalho
	 * 
	 * @param id
	 * @param cabecalhoDto
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(
			// Pega variavel da URL
			@PathVariable("id") Long id) {
		log.info("Deletando cabecalho para o ID: {}", id);

		Response<String> response = new Response<String>();
		Optional<Cabecalho> cabecalho = this.cabecalhoService.buscarPorId(id);

		if (!cabecalho.isPresent()) {
			log.info("Erro ao remover devido ao Cabecalho ID: {} ser inválido", id);
			response.getErrors().add("Erro ao remover cabecalho. Cabecalho não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.cabecalhoService.removerItensECabecalho(cabecalho.get());
		return ResponseEntity.ok(response);
	}

	/**************************************************************
	 * METODOS COMPLEMENTARES *
	 **************************************************************/

	/**
	 * Validações especificas
	 * 
	 * @param CabecalhoDto
	 * @param result
	 */
	public void validarDadosExistentes(CabecalhoDto cabecalhoDto, BindingResult result) {
		if (!EnumUtils.isValidEnum(TipoEnum.class, cabecalhoDto.getTipo())) {
			result.addError(new ObjectError("tipo", "Tipo Inválido!"));
		}
	}

	/**
	 * converte um CabecalhoDto para Cabecalho
	 * 
	 * @param CabecalhoDto
	 * @return Cabecalho
	 */
	public Cabecalho converterDtoParaCabecalho(CabecalhoDto cabecalhoDto, BindingResult result) {
		Cabecalho cabecalho = new Cabecalho();

		this.atualizarDadosCabecalho(cabecalho, cabecalhoDto, result);

		return cabecalho;
	}

	/**
	 * Converte um Cabecalho para um CabecalhoDto
	 * 
	 * @param cabecalho
	 * @return CabecalhoDto
	 */
	public CabecalhoDto converterCabecalhoParaDto(Cabecalho cabecalho) {
		CabecalhoDto cabecalhoDto = new CabecalhoDto();
		cabecalhoDto.setId(cabecalho.getId());
		cabecalhoDto.setIntegranteId(cabecalho.getIntegrante().getId());
		cabecalhoDto.setTipo(cabecalho.getTipo().toString());
		return cabecalhoDto;
	}

	/**
	 * Atualiza os dados do cabecalho, baseado no Dto recebido
	 * 
	 * @param cabecalho
	 * @param cabecalhoDto
	 * @param result
	 */
	public void atualizarDadosCabecalho(Cabecalho cabecalho, CabecalhoDto cabecalhoDto, BindingResult result) {
		Optional<Integrante> integrante = this.integranteService.buscarPorId(cabecalhoDto.getIntegranteId());

		if (!integrante.isPresent()) {
			result.addError(new ObjectError("integrante", "integrante inválido."));
		} else {
			cabecalho.setIntegrante(integrante.get());
		}

		if (EnumUtils.isValidEnum(TipoEnum.class, cabecalhoDto.getTipo())) {
			cabecalho.setTipo(TipoEnum.valueOf(cabecalhoDto.getTipo()));
		} else {
			result.addError(new ObjectError("tipo", "tipo inválido."));
		}

		cabecalho.setId(cabecalhoDto.getId());
	}
}
