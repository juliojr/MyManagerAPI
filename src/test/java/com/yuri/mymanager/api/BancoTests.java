package com.yuri.mymanager.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.mymanager.api.entities.BalancoMensal;
import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.FechamentoDia;
import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Item;
import com.yuri.mymanager.api.entities.ItensPendentes;
import com.yuri.mymanager.api.entities.MaisIntegrantes;
import com.yuri.mymanager.api.entities.MaisProdutos;
import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.entities.SaldoProdutos;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.enums.SituacaoEnum;
import com.yuri.mymanager.api.enums.TipoEnum;
import com.yuri.mymanager.api.repositories.MaisProdutosRepository;
import com.yuri.mymanager.api.services.impl.BalancoMensalServiceImpl;
import com.yuri.mymanager.api.services.impl.CabecalhoServiceImpl;
import com.yuri.mymanager.api.services.impl.FechamentoDiaServiceImpl;
import com.yuri.mymanager.api.services.impl.IntegranteServiceImpl;
import com.yuri.mymanager.api.services.impl.ItemServiceImpl;
import com.yuri.mymanager.api.services.impl.ItensPendentesServiceImpl;
import com.yuri.mymanager.api.services.impl.MaisIntegrantesServiceImpl;
import com.yuri.mymanager.api.services.impl.MaisProdutosServiceImpl;
import com.yuri.mymanager.api.services.impl.ProdutoServiceImpl;
import com.yuri.mymanager.api.services.impl.SaldoProdutosServiceImpl;
import com.yuri.mymanager.api.services.impl.UsuarioServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BancoTests {
	@Autowired
	private UsuarioServiceImpl usuarioService;
	@Autowired
	private ProdutoServiceImpl produtoService;
	@Autowired
	private IntegranteServiceImpl integranteService;
	@Autowired
	private CabecalhoServiceImpl cabecalhoService;
	@Autowired
	private ItemServiceImpl itemService;
	@Autowired
	private ItensPendentesServiceImpl itensPendentesServiceImpl;
	@Autowired
	private BalancoMensalServiceImpl balancoMensalServiceImpl;
	@Autowired
	private MaisProdutosServiceImpl maisProdutosServiceImpl;
	@Autowired
	private MaisIntegrantesServiceImpl maisIntegrantesServiceImpl;
	@Autowired
	private FechamentoDiaServiceImpl fechamentoDiaServiceImpl;
	@Autowired
	private SaldoProdutosServiceImpl saldoProdutosServiceImpl;
	

	@Test
	public void iniciar() {
		
		List<ItensPendentes> lista = itensPendentesServiceImpl.buscarPorusuarioID(2L);
		
		lista.forEach(l -> System.out.println(l.toString()));
		
		List<BalancoMensal> listaBalanco = balancoMensalServiceImpl.buscarPorUsuarioIDEMesEAno(2L,  3,  2018);
		
		listaBalanco.forEach(l -> System.out.println(l.toString()));
		
		List<MaisProdutos> listaProdutos = maisProdutosServiceImpl.buscarPorUsuarioIDEMesEAno(2L,  3,  2018);
		
		listaProdutos.forEach(l -> System.out.println(l.toString()));
		
		List<MaisIntegrantes> listaIntegrantes = maisIntegrantesServiceImpl.buscarPorUsuarioIDEMesEAno(2L,  3,  2018);
		
		listaIntegrantes.forEach(l -> System.out.println(l.toString()));
		
		List<FechamentoDia> listaFechamentosDia = fechamentoDiaServiceImpl.buscarPorUsuarioIDEMesEAno(2L,  3,  2018);
		
		listaFechamentosDia.forEach(l -> System.out.println(l.toString()));
		
		List<SaldoProdutos> listaSaldoProdutos = saldoProdutosServiceImpl.buscarPorUsuarioID(2L);
		
		listaSaldoProdutos.forEach(l -> System.out.println(l.toString()));
		
		
		/*
		// Testes de inserção no banco - OK

		// testa se o usuário já existe, se não existir cria um novo
		Usuario usuario = usuarioService.buscarPorEmail("yuriodp@gmail.com").orElse(new Usuario());
		usuario.setNome("Yuri Oliveira de Paula");
		usuario.setEmail("yuriodp@gmail.com");
		usuario.setSenha("abc123@");

		usuario = usuarioService.persistir(usuario);

		// tenta buscar o integrante, se não tiver, cria 
		Integrante integrante = new Integrante();
		if (!integranteService.buscarPorUsuario(usuario).isEmpty()){
			integrante = integranteService.buscarPorUsuario(usuario).get(0);
		}
		integrante.setBairro("Luiza 2");
		integrante.setCidade("Franca");
		integrante.setComplemento("apto 11");
		integrante.setCpfCnpj("39063491875");
		integrante.setDdd(16);
		integrante.setNome("Yuri Oliveira de Paula");
		integrante.setNumero(2965);
		integrante.setRua("Rubens Carbone");
		integrante.setTelefone(Long.parseLong("992039581"));
		integrante.setUf("SP");
		integrante.setUsuario(usuario);

		integrante = integranteService.persistir(integrante);

		// tenta buscar um cabecalho, se não tiver, cria 
		Cabecalho cabecalho = new Cabecalho();
		//if (!cabecalhoService.buscarPorIntegrante(integrante).isEmpty()) {
	    //	cabecalho = cabecalhoService.buscarPorIntegrante(integrante).get(0);
		//}
		cabecalho.setDataMovimento(new Date());
		cabecalho.setIntegrante(integrante);
		cabecalho.setTipo(TipoEnum.VENDA);
		cabecalho.setUsuario(usuario);

		cabecalho = cabecalhoService.persistir(cabecalho);

		// cria um produto
		//Produto produto = new Produto();
		//produto.setDescricao("Produto 1");
		//produto.setUsuario(usuario);

		Produto produto = produtoService.buscarPorId(2L).orElse(new Produto());

		// cria um item
		Item item = new Item();
		item.setCabecalho(cabecalho);
		item.setDataPagamento(new Date());
		item.setSituacao(SituacaoEnum.ABERTO);
		item.setProduto(produto);
		item.setQuantidade((double) 50);
		item.setUnitario((double) 20);
		item.setUsuario(usuario);

		item = itemService.persistir(item);

		// outro produto
		//produto = new Produto();
		//produto.setDescricao("Produto 2");
		//produto.setUsuario(usuario);

		//produto = produtoService.persistir(produto);
		produto = produtoService.buscarPorId(3L).orElse(new Produto());

		// outro item
		item = new Item();
		item.setCabecalho(cabecalho);
		item.setDataPagamento(new Date());
		item.setSituacao(SituacaoEnum.PAGO);
		item.setProduto(produto);
		item.setQuantidade((double) 30);
		item.setUnitario((double) 25);
		item.setUsuario(usuario);

		item = itemService.persistir(item);

		// outro produto
		//produto = new Produto();
		//produto.setDescricao("Produto 3");
		//produto.setUsuario(usuario);

		//produto = produtoService.persistir(produto);
		produto = produtoService.buscarPorId(4L).orElse(new Produto());

		// outro item
		item = new Item();
		item.setCabecalho(cabecalho);
		item.setDataPagamento(new Date());
		item.setSituacao(SituacaoEnum.PAGO);
		item.setProduto(produto);
		item.setQuantidade((double) 10);
		item.setUnitario((double) 80);
		item.setUsuario(usuario);

		item = itemService.persistir(item);

		// testes de busca no banco

		// precisa entender melhor o uso do optional (evita null pointer exception)
		// precisa sempre testar se o objeto está presente (muito trabalho)

		// Optional<Usuario> u = usuarioService.buscarPorEmail("yuriodp@gmail.com.br");

		// validando o optional para preencher o objeto
		// Usuario usu = u.orElse(new Usuario());
		// System.out.println(usu);

		// validando o optional pra preencher o pbjeto
		// Usuario us = (u.isPresent() ? u.get() : new Usuario());
		// System.out.println(us.toString());

		// passado por lambda function
		// u.ifPresent(us -> System.out.println(us));

		// passando por validação
		/*
		 * if (u.isPresent()) { System.out.println(u.get());
		 * 
		 * List<Cabecalho> listaCabecalho = cabecalhoService.buscarPorUsuario(u.get());
		 * listaCabecalho.forEach(c -> System.out.println(c.toString()));
		 * 
		 * listaCabecalho = cabecalhoService.buscarPorTipoEUsuario(TipoEnum.COMPRA,
		 * u.get()); listaCabecalho.forEach(c -> System.out.println(c.toString())); }
		 */

		// pelo que entendi do optional, essa é a melhor forma de atribuição, no else
		// cria um novo objeto vazio
		// Usuario u = usuarioService.buscarPorEmail("yuriodp@gmail.com.br").orElse(new
		// Usuario());
		// System.out.println(u);

		System.out.println("************************************************");
		System.out.println("            Excluindo registros                 ");
		System.out.println("************************************************");

		// itemService.removeItensPorCabecalho(cabecalho);
		//cabecalhoService.removeItensECabecalho(cabecalho);
		//integranteService.removeIntegrante(integrante);
		//produtoService.removeProduto(produto);
		//usuarioService.removeUsuario(usuario);

		System.out.println("************************************************");
		System.out.println("           Registros Excluidos                  ");
		System.out.println("************************************************");

	}

}
