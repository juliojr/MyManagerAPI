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

import com.mymanager.api.entities.FechamentoDia;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.FechamentoDiaService;

/**
 * Controller para interação da entity FechamentoDia
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/fechamento-dia")
@CrossOrigin(origins = "*")
public class FechamentoDiaController {
	private static final Logger log = LoggerFactory.getLogger(FechamentoDiaController.class);

	@Autowired
	private FechamentoDiaService fechamentoDiaService;

	public FechamentoDiaController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna uma lista de FechamentoDia
	 * 
	 * @param ano
	 * @param mes
	 * @return ResponseEntity<Response<List<FechamentoDia>>>
	 */
	@GetMapping(value = "/{ano}/{mes}")
	public ResponseEntity<Response<List<FechamentoDia>>> buscarPorMesEAno(
			// pega variáveis da URL
			@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {

		log.info("Buscando FechamentoDia");
		Response<List<FechamentoDia>> response = new Response<List<FechamentoDia>>();

		List<FechamentoDia> lista = new ArrayList<FechamentoDia>();

		this.fechamentoDiaService.buscarPorMesEAno(mes, ano)
			.stream()
				.sorted((b1, b2) -> b1.getDia().compareTo(b2.getDia()))
				.forEach(fechamentoDia -> lista.add(fechamentoDia));

		response.setData(lista);
		return ResponseEntity.ok(response);
	}
}