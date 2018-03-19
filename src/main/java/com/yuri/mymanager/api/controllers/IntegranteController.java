package com.yuri.mymanager.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuri.mymanager.api.dtos.IntegranteDto;
import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.enums.TipoIntegranteEnum;
import com.yuri.mymanager.api.response.Response;
import com.yuri.mymanager.api.services.IntegranteService;
import com.yuri.mymanager.api.services.UsuarioService;

//marca a classe como endPoint
@RestController
//marca o mapeamento padrão
@RequestMapping("/api/integrante")
//habilita ser acessado por dominios diferentes
@CrossOrigin(origins = "*")
public class IntegranteController {
private static final Logger log = LoggerFactory.getLogger(IntegranteController.class);
	
	@Autowired
	private IntegranteService integranteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public IntegranteController() {
		
	}
	
	/**
	 * Cadastra um Integrante no sistema
	 * @param integranteDto
	 * @param result
	 * @return ResponseEntity<Response<IntegranteDto>>
	 */
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Response<IntegranteDto>> cadastrar(
			//atribui o requestBody para o dto, validando, de acordo com as anotações do DTO
			@Valid @RequestBody IntegranteDto integranteDto,
			//identifica o resultado da validação do DTO
			BindingResult result) {
		
		log.info("Cadastrando integrante: {}", integranteDto);
		Response<IntegranteDto> response = new Response<IntegranteDto>();
		
		validarDadosExistentes(integranteDto, result);
		Integrante integrante = this.converterDtoParaIntegrante(integranteDto);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro do integrante: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.integranteService.persistir(integrante);
		
		response.setData(this.converterIntegranteParaDto(integrante));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Validações especificas
	 * @param IntegranteDto
	 * @param result
	 */
	public void validarDadosExistentes(IntegranteDto IntegranteDto, BindingResult result) {
		if (IntegranteDto.getTipoIntegrante().equals(TipoIntegranteEnum.CNPJ) && IntegranteDto.getCnpj().isEmpty()) {
			result.addError(new ObjectError("cnpj", "Obrigatório informar CNPJ"));
		}
		else if (IntegranteDto.getTipoIntegrante().equals(TipoIntegranteEnum.CPF) && IntegranteDto.getCpf().isEmpty()) {
			result.addError(new ObjectError("cpf", "Obrigatório informar CPF"));
		}
	}
	
	/**
	 * converte um IntegranteDto para Integrante
	 * @param IntegranteDto
	 * @return Integrante
	 */
	public Integrante converterDtoParaIntegrante(IntegranteDto IntegranteDto) {
		Integrante integrante = new Integrante();
		integrante.setBairro(IntegranteDto.getBairro());
		integrante.setCidade(IntegranteDto.getCidade());
		integrante.setComplemento(IntegranteDto.getComplemento());
		if (IntegranteDto.getTipoIntegrante().equals(TipoIntegranteEnum.CPF)) {
			integrante.setCpfCnpj(IntegranteDto.getCpf());
		}else {
			integrante.setCpfCnpj(IntegranteDto.getCnpj());
		}
		integrante.setDdd(IntegranteDto.getDdd());
		integrante.setNome(IntegranteDto.getNome());
		integrante.setNumero(IntegranteDto.getNumero());
		integrante.setRua(IntegranteDto.getRua());
		integrante.setTelefone(IntegranteDto.getTelefone());
		integrante.setTipoIntegrante(IntegranteDto.getTipoIntegrante());
		integrante.setUf(IntegranteDto.getUf());
		
		//#usuarioLogado
		integrante.setUsuario(this.usuarioService.buscarPorId(2L).get());
		
		return integrante;
	}
	
	/**
	 * Converte um Integrante para um IntegranteDto
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
		IntegranteDto.setTipoIntegrante(integrante.getTipoIntegrante());
		IntegranteDto.setUf(integrante.getUf());
		
		return IntegranteDto;
	}
	
	/**
	 * Método de busca do Integrante por ID
	 * @param id
	 * @return ResponseEntity<Response<CadastroUsuarioDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<IntegranteDto>> buscarPorId(
			//pega variável da URL
			@PathVariable("id") Long id){
		
		log.info("Buscando integrante pelo ID: {}", id);
		Response<IntegranteDto> response = new Response<IntegranteDto>();
		
		Optional<Integrante> integrante = integranteService.buscarPorId(id);
		
		if(!integrante.isPresent()) {
			log.info("Integrante não encontrado para o ID: {}", id);
			response.getErrors().add("Integrante não encontrado para o ID: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterIntegranteParaDto(integrante.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 *  Método de atualização do integrante
	 * @param id
	 * @param integranteDto
	 * @param result
	 * @return ResponseEntity<Response<IntegranteDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<IntegranteDto>> atualizar(
			//Pega variavel da URL
			@PathVariable("id") Long id,
			//atribui o requestBody para o dto, validando, de acordo com as anotações do DTO
			@Valid @RequestBody IntegranteDto integranteDto,
			//identifica o resultado da validação do DTO
			BindingResult result) {
		
		log.info("Atualizando integrante: {}", integranteDto);
		Response<IntegranteDto> response = new Response<IntegranteDto>();
		
		Optional<Integrante> integrante = this.integranteService.buscarPorId(id);
		if(!integrante.isPresent()) {
			result.addError(new ObjectError("integrante", "integrante não encontrado."));
		}
		
		this.atualizarDadosIntegrante(integrante.get(), integranteDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando Integrante: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.integranteService.persistir(integrante.get());
		response.setData(this.converterIntegranteParaDto(integrante.get()));
		return ResponseEntity.ok(response);
	}
	
	public void atualizarDadosIntegrante(Integrante integrante, IntegranteDto integranteDto, BindingResult result) {
		integrante.setBairro(integranteDto.getBairro());
		integrante.setCidade(integranteDto.getCidade());
		integrante.setComplemento(integranteDto.getComplemento());
		if (integranteDto.getTipoIntegrante().equals(TipoIntegranteEnum.CPF)) {
			integrante.setCpfCnpj(integranteDto.getCpf());
		}else {
			integrante.setCpfCnpj(integranteDto.getCnpj());
		}
		integrante.setDdd(integranteDto.getDdd());
		integrante.setNome(integranteDto.getNome());
		integrante.setNumero(integranteDto.getNumero());
		integrante.setRua(integranteDto.getRua());
		integrante.setTelefone(integranteDto.getTelefone());
		integrante.setTipoIntegrante(integranteDto.getTipoIntegrante());
		integrante.setUf(integranteDto.getUf());
	}
}
