package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.entities.ItensPendentes;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.ItensPendentesService;

/**
 * Controller para interação da entidade ItensPendentes
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/itens-pendentes")
@CrossOrigin(origins = "*")
public class ItensPendentesController {
	private static final Logger log = LoggerFactory.getLogger(ItensPendentesController.class);

	@Autowired
	private ItensPendentesService itensPendentesService;

	public ItensPendentesController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna uma lista de ItensPendentes
	 * 
	 * @return ResponseEntity<Response<List<ItensPendentes>>>
	 */
	@GetMapping
	public ResponseEntity<Response<List<ItensPendentes>>> buscarPorUsuario() {

		log.info("Buscando ItensPendentes");
		Response<List<ItensPendentes>> response = new Response<List<ItensPendentes>>();

		List<ItensPendentes> lista = new ArrayList<ItensPendentes>();

		this.itensPendentesService.buscarPorUsuarioID().stream()
				.sorted((i1, i2) -> i1.getDataPagamento().compareTo(i2.getDataPagamento()))
				.forEach(itensPendentes -> lista.add(itensPendentes));

		response.setData(lista);
		return ResponseEntity.ok(response);
	}
}
