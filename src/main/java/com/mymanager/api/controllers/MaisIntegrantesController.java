package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.entities.MaisIntegrantes;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.MaisIntegrantesService;

/**
 * Controller para interação da entidade MaisIntegrantes
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/mais-integrantes")
@CrossOrigin(origins = "*")
public class MaisIntegrantesController {
	private static final Logger log = LoggerFactory.getLogger(MaisIntegrantesController.class);

	@Autowired
	private MaisIntegrantesService maisIntegrantesService;

	public MaisIntegrantesController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna uma lista de maisIntegrantes
	 * 
	 * @param ano
	 * @param mes
	 * @return
	 */
	@GetMapping(value = "/{ano}/{mes}")
	public ResponseEntity<Response<List<MaisIntegrantes>>> buscarPorMesEAno(
			// pega a variavel da url
			@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {

		log.info("Buscando Balancos por mes {} e  ano {}", mes, ano);
		Response<List<MaisIntegrantes>> response = new Response<List<MaisIntegrantes>>();

		if (ano == null) {
			log.info("Ano não informado.");
			response.getErrors().add("Ano não informado.");
			return ResponseEntity.badRequest().body(response);
		}

		if (mes == null) {
			log.info("Mês não informado.");
			response.getErrors().add("Mes não informado.");
			return ResponseEntity.badRequest().body(response);
		}

		List<MaisIntegrantes> lista = new ArrayList<MaisIntegrantes>();

		this.maisIntegrantesService.buscarPorMesEAno(mes, ano).stream()
				.sorted((p1, p2) -> p1.getMes().compareTo(p2.getMes()))
				.forEach(maisIntegrantes -> lista.add(maisIntegrantes));

		response.setData(lista);
		return ResponseEntity.ok(response);
	}
}
