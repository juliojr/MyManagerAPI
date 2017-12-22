package com.yuri.mymanager.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.mymanager.api.entities.Produto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	private static final String desc = "produto teste";
	
	@Before
	public void setUp() {
		
		Produto produto = new Produto();
		produto.setDescricao(desc);
		this.produtoRepository.save(produto);
	}
	
	@After
	public final void tearDown() {
		this.produtoRepository.deleteAll();
	}
	
	@Test
	public void testProdutoRepository() {
		Produto p = this.produtoRepository.findByDescricao(desc);
		
		assertEquals(desc, p.getDescricao());
	}
}
