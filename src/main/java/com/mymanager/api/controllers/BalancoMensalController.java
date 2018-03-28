package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.entities.BalancoMensal;
import com.mymanager.api.enums.SituacaoEnum;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.BalancoMensalService;

/**
 * Controller para interação da entity BalancoMensal
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/balanco-mensal")
@CrossOrigin(origins = "*")
public class BalancoMensalController {
	private static final Logger log = LoggerFactory.getLogger(BalancoMensalController.class);

	@Autowired
	private BalancoMensalService balancoMensalService;

	public BalancoMensalController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna um BalancoMensal
	 * 
	 * @param ano
	 * @param mes
	 * @param situacao
	 * @return ResponseEntity<Response<BalancoMensal>>
	 */
	@GetMapping(value = "/{ano}/{mes}/{situacao}")
	public ResponseEntity<Response<BalancoMensal>> buscarPorAnoEMes(
			// pega variáveis da URL
			@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes,
			@PathVariable("situacao") String situacao) {

		log.info("Buscando BalancoMensal pelo ano {} e mes {} e situacao {}", ano, mes, situacao);
		Response<BalancoMensal> response = new Response<BalancoMensal>();

		if (ano == null) {
			log.info("Ano não informado");
			response.getErrors().add("Ano não informado.");
			return ResponseEntity.badRequest().body(response);
		}

		if (mes == null) {
			log.info("Mês não informado");
			response.getErrors().add("Mês não informado.");
			return ResponseEntity.badRequest().body(response);
		}

		if (!EnumUtils.isValidEnum(SituacaoEnum.class, situacao)) {
			log.info("Situação inválida.");
			response.getErrors().add("Situacao Inválida");
			return ResponseEntity.badRequest().body(response);
		}

		Optional<BalancoMensal> balancoMensal = this.balancoMensalService.buscarPorMesEAnoESituacao(mes, ano,
				SituacaoEnum.valueOf(situacao));

		if (!balancoMensal.isPresent()) {
			log.info("Balanco Não encontrado!");
			response.getErrors().add("Balanço não encontrado.");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(balancoMensal.get());
		return ResponseEntity.ok(response);
	}

	/**
	 * retorna uma lista de BalancoMensal
	 * 
	 * @param ano
	 * @param situacao
	 * @return ResponseEntity<Response<List<BalancoMensal>>>
	 */
	@GetMapping(value = "/{ano}/{situacao}")
	public ResponseEntity<Response<List<BalancoMensal>>> buscarPorAno(
			// pega as variaveis da url
			@PathVariable("ano") Integer ano, @PathVariable("situacao") String situacao) {

		log.info("Buscando Balancos pelo ano {}", ano);
		Response<List<BalancoMensal>> response = new Response<List<BalancoMensal>>();

		if (ano == null) {
			log.info("Ano não informado.");
			response.getErrors().add("Ano não informado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<BalancoMensal> lista = new ArrayList<BalancoMensal>();

		this.balancoMensalService.buscarPorAnoESituacao(ano, SituacaoEnum.valueOf(situacao))
			.stream()
				.sorted((p1, p2) -> p1.getMes().compareTo(p2.getMes()))
				.forEach(balancoMensal -> lista.add(balancoMensal));

		response.setData(lista);
		return ResponseEntity.ok(response);
	}
}
