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

import com.mymanager.api.dtos.IntegranteDto;
import com.mymanager.api.entities.Integrante;
import com.mymanager.api.enums.TipoIntegranteEnum;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.IntegranteService;

/**
 * Controller para interação da entity Integrante
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/integrante")
@CrossOrigin(origins = "*")
public class IntegranteController {
	private static final Logger log = LoggerFactory.getLogger(IntegranteController.class);

	@Autowired
	private IntegranteService integranteService;

	public IntegranteController() {
	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * Cadastra um Integrante no sistema
	 * 
	 * @param integranteDto
	 * @param result
	 * @return ResponseEntity<Response<IntegranteDto>>
	 */
	@PostMapping
	public ResponseEntity<Response<IntegranteDto>> cadastrar(
			// atribui o requestBody para o dto, validando, de acordo com as anotações
			@Valid @RequestBody IntegranteDto integranteDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Cadastrando integrante: {}", integranteDto);
		Response<IntegranteDto> response = new Response<IntegranteDto>();

		validarDadosExistentes(integranteDto, result);
		Integrante integrante = this.converterDtoParaIntegrante(integranteDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do integrante: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.integranteService.persistir(integrante);

		response.setData(this.converterIntegranteParaDto(integrante));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna uma lista de IntegrantesDto de acordo com o usuário Logado
	 * 
	 * @return ResponseEntity<Response<List<IntegranteDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<IntegranteDto>>> buscarPorUsuario() {
		Response<List<IntegranteDto>> response = new Response<List<IntegranteDto>>();

		List<Integrante> integrantes = this.integranteService.buscarPorUsuario();

		if (integrantes.isEmpty()) {
			log.info("Nenhum integrante encontrado.");
			response.getErrors().add("Nenhum integrante encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<IntegranteDto> integrantesDto = new ArrayList<IntegranteDto>();
		
		integrantes
			.stream()
				.sorted((i1, i2) -> i1.getId().compareTo(i2.getId()))
				.forEach(integrante -> integrantesDto.add(this.converterIntegranteParaDto(integrante)));

		response.setData(integrantesDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de busca do Integrante por ID
	 * 
	 * @param id
	 * @return ResponseEntity<Response<IntegranteDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<IntegranteDto>> buscarPorId(
			// pega variável da URL
			@PathVariable("id") Long id) {

		log.info("Buscando integrante pelo ID: {}", id);
		Response<IntegranteDto> response = new Response<IntegranteDto>();

		Optional<Integrante> integrante = integranteService.buscarPorId(id);

		if (!integrante.isPresent()) {
			log.info("Integrante não encontrado para o ID: {}", id);
			response.getErrors().add("Integrante não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterIntegranteParaDto(integrante.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Método de atualização do integrante
	 * 
	 * @param id
	 * @param integranteDto
	 * @param result
	 * @return ResponseEntity<Response<IntegranteDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<IntegranteDto>> atualizar(
			// Pega variavel da URL
			@PathVariable("id") Long id,
			// atribui o requestBody para o dto, validando, de acordo com as anotações do
			// DTO
			@Valid @RequestBody IntegranteDto integranteDto,
			// identifica o resultado da validação do DTO
			BindingResult result) {

		log.info("Atualizando integrante: {}", integranteDto);
		Response<IntegranteDto> response = new Response<IntegranteDto>();

		Optional<Integrante> integrante = this.integranteService.buscarPorId(id);

		if (!integrante.isPresent()) {
			log.error("Erro validando Integrante: {}", integrante);
			response.getErrors().add("integrante não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}

		this.atualizarDadosIntegrante(integrante.get(), integranteDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando integrante: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.integranteService.persistir(integrante.get());
		response.setData(this.converterIntegranteParaDto(integrante.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Remove um integrante
	 * 
	 * @param id
	 * @param integranteDto
	 * @param result
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(
			// Pega variavel da URL
			@PathVariable("id") Long id) {
		log.info("Deletando integrante para o ID: {}", id);

		Response<String> response = new Response<String>();
		Optional<Integrante> integrante = this.integranteService.buscarPorId(id);

		if (!integrante.isPresent()) {
			log.info("Erro ao remover devido ao Integrante ID: {} ser inválido", id);
			response.getErrors().add("Erro ao remover integrante. Integrante não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.integranteService.removerIntegrante(integrante.get());
		return ResponseEntity.ok(response);
	}

	/**************************************************************
	 * METODOS COMPLEMENTARES *
	 **************************************************************/

	/**
	 * Validações especificas
	 * 
	 * @param IntegranteDto
	 * @param result
	 */
	public void validarDadosExistentes(IntegranteDto integranteDto, BindingResult result) {
		if (EnumUtils.isValidEnum(TipoIntegranteEnum.class, integranteDto.getTipoIntegrante())) {

			if (TipoIntegranteEnum.valueOf(integranteDto.getTipoIntegrante()).equals(TipoIntegranteEnum.CNPJ)
					&& integranteDto.getCnpj().isEmpty()) {
				result.addError(new ObjectError("cnpj", "Obrigatório informar CNPJ"));
			} else if (TipoIntegranteEnum.valueOf(integranteDto.getTipoIntegrante()).equals(TipoIntegranteEnum.CPF)
					&& integranteDto.getCpf().isEmpty()) {
				result.addError(new ObjectError("cpf", "Obrigatório informar CPF"));
			}
		} else {
			result.addError(new ObjectError("tipoIntegrante", "Tipo Integrante Inválido!"));
		}
	}

	/**
	 * converte um IntegranteDto para Integrante
	 * 
	 * @param IntegranteDto
	 * @return Integrante
	 */
	public Integrante converterDtoParaIntegrante(IntegranteDto integranteDto, BindingResult result) {
		Integrante integrante = new Integrante();

		this.atualizarDadosIntegrante(integrante, integranteDto, result);

		return integrante;
	}

	/**
	 * Converte um Integrante para um IntegranteDto
	 * 
	 * @param integrante
	 * @return IntegranteDto
	 */
	public IntegranteDto converterIntegranteParaDto(Integrante integrante) {
		IntegranteDto IntegranteDto = new IntegranteDto();
		IntegranteDto.setBairro(integrante.getBairro());
		IntegranteDto.setCidade(integrante.getCidade());

		if (integrante.getTipoIntegrante().equals(TipoIntegranteEnum.CNPJ)) {
			IntegranteDto.setCnpj(integrante.getCpfCnpj());
		} else {
			IntegranteDto.setCpf(integrante.getCpfCnpj());
		}
		IntegranteDto.setComplemento(integrante.getComplemento());
		IntegranteDto.setDdd(integrante.getDdd());
		IntegranteDto.setId(integrante.getId());
		IntegranteDto.setNome(integrante.getNome());
		IntegranteDto.setNumero(integrante.getNumero());
		IntegranteDto.setRua(integrante.getRua());
		IntegranteDto.setTelefone(integrante.getTelefone());
		IntegranteDto.setTipoIntegrante(integrante.getTipoIntegrante().toString());
		IntegranteDto.setUf(integrante.getUf());

		return IntegranteDto;
	}

	/**
	 * Atualiza os dados do integrante, baseado no Dto recebido
	 * 
	 * @param integrante
	 * @param integranteDto
	 * @param result
	 */
	public void atualizarDadosIntegrante(Integrante integrante, IntegranteDto integranteDto, BindingResult result) {
		integrante.setBairro(integranteDto.getBairro());
		integrante.setCidade(integranteDto.getCidade());
		integrante.setComplemento(integranteDto.getComplemento());

		if (EnumUtils.isValidEnum(TipoIntegranteEnum.class, integranteDto.getTipoIntegrante())) {

			if (TipoIntegranteEnum.valueOf(integranteDto.getTipoIntegrante()).equals(TipoIntegranteEnum.CPF)) {
				integrante.setCpfCnpj(integranteDto.getCpf());
			} else {
				integrante.setCpfCnpj(integranteDto.getCnpj());
			}

			integrante.setTipoIntegrante(TipoIntegranteEnum.valueOf(integranteDto.getTipoIntegrante()));
		} else {
			result.addError(new ObjectError("tipoIntegrante", "tipoIntegrante inválido."));
		}

		integrante.setDdd(integranteDto.getDdd());
		integrante.setNome(integranteDto.getNome());
		integrante.setNumero(integranteDto.getNumero());
		integrante.setRua(integranteDto.getRua());
		integrante.setTelefone(integranteDto.getTelefone());
		integrante.setUf(integranteDto.getUf());
		integrante.setId(integranteDto.getId());
	}
}
