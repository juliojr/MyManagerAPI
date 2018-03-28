package com.mymanager.api.controllers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymanager.api.entities.MaisProdutos;
import com.mymanager.api.response.Response;
import com.mymanager.api.services.MaisProdutosService;

/**
 * Controller para interação da entidade MaisProdutos
 * 
 * @RestControler -> marca a classe como endPpoint
 * @RequestMapping -> marca o mapeamento padrão do controller
 * @CrossOrigin -> habilita ser acessado por dominios diferentes
 * @author Yuri Oliveira
 */
@RestController
@RequestMapping("/api/mais-produtos")
@CrossOrigin(origins = "*")
public class MaisProdutosController {
	private static final Logger log = LoggerFactory.getLogger(MaisProdutosController.class);
	
	@Value("${retornos.limit}")
	private int limit;

	@Autowired
	private MaisProdutosService maisProdutosService;

	public MaisProdutosController() {

	}

	/**************************************************************
	 * METODOS DAS REQUISIÇÕES HTTP AO CONTROLER *
	 **************************************************************/

	/**
	 * retorna uma lista de maisProdutos
	 * 
	 * @param ano
	 * @param mes
	 * @return
	 */
	@GetMapping(value = "/{ano}/{mes}")
	public ResponseEntity<Response<List<MaisProdutos>>> buscarPorMesEAno(
			// pega a variavel da url
			@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {

		log.info("Buscando Balancos por mes {} e  ano {}", mes, ano);
		Response<List<MaisProdutos>> response = new Response<List<MaisProdutos>>();

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

		List<MaisProdutos> lista = new ArrayList<MaisProdutos>();

		this.maisProdutosService.buscarPorMesEAno(mes, ano).stream()
				.sorted((p1, p2) -> p2.getValorTotal().compareTo(p1.getValorTotal()))
				.forEach(maisProdutos -> { 
					if(lista.size() < limit) { 
						lista.add(maisProdutos);
					}
				});

		response.setData(lista);
		return ResponseEntity.ok(response);
	}
}
