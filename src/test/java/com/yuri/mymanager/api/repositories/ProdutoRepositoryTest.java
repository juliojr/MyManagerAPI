package com.yuri.mymanager.api.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings("static-access")
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	GenericInfo info = new GenericInfo();

	@Before
	public void setUp() {
		this.usuarioRepository.save(info.usuario);

		this.produtoRepository.save(info.produto);
	}

	@After
	public final void tearDown() {
		this.produtoRepository.deleteAll();
		this.usuarioRepository.deleteAll();
	}
}
