package com.mymanager.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.dtos.UsuarioDto;
import com.mymanager.api.entities.Usuario;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.UsuarioService;
import com.mymanager.api.utils.PasswordUtils;

/**
 * Controller para interação da entidade Usuario
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	public UsuarioController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * Cadastra um usuario no sistema
	 * 
	 * @param UsuarioDto
	 * @param result
	 * @return ResponseEntity<Response<UsuarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Response<UsuarioDto>> cadastrar(
			// atribui o requestBody para o dto, validando, de acordo com as anotações do
			// DTO
			@Valid @RequestBody UsuarioDto usuarioDto,
			// identifica o resultado da validação do DTO
			BindingResult result)
			// pode lançar exceção na parte de geração de senha (BCrypt)
			throws NoSuchAlgorithmException {

		log.info("Cadastrando usuário: {}", usuarioDto);
		Response<UsuarioDto> response = new Response<UsuarioDto>();

		validarDadosExistentes(usuarioDto, result);

		Usuario usuario = this.converterDtoParaUsuario(usuarioDto);

		if (result.hasErrors()) {
			log.error("Ero validando dados de cadastro de usuario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.usuarioService.persistir(usuario);

		response.setData(this.converterUsuarioParaDto(usuario));
		return ResponseEntity.ok(response);
	}

	/**************************************************************
	 * METODOS COMPLEMENTARES *
	 **************************************************************/

	/**
	 * Verifica se o email já existe na base de dados
	 * 
	 * @param cadastrarUsuarioDto
	 * @param result
	 */
	private void validarDadosExistentes(UsuarioDto UsuarioDto, BindingResult result) {
		// verifica se o email já foi cadastrado
		this.usuarioService.buscarPorEmail(UsuarioDto.getEmail())
				.ifPresent(u -> result.addError(new ObjectError("email", "Email já cadastrado")));
	}

	/**
	 * Converte UsuarioDto para Usuario
	 * 
	 * @param UsuarioDto
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Usuario converterDtoParaUsuario(UsuarioDto UsuarioDto) throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(UsuarioDto.getNome());
		usuario.setEmail(UsuarioDto.getEmail());
		usuario.setSenha(PasswordUtils.gerarBCrypt(UsuarioDto.getSenha()));

		return usuario;
	}

	/**
	 * Converte Usuario para UsuarioDto
	 * 
	 * @param usuario
	 * @return
	 */
	public UsuarioDto converterUsuarioParaDto(Usuario usuario) {
		UsuarioDto UsuarioDto = new UsuarioDto();
		UsuarioDto.setId(usuario.getId());
		UsuarioDto.setNome(usuario.getNome());
		UsuarioDto.setEmail(usuario.getEmail());

		return UsuarioDto;
	}
}
