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

import com.yuri.mymanager.api.entities.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository usuarioRepository;
	private static final String email = "teste@teste";
	private static final String nome = "root";
	private static final String senha = "root";
	
	@Before
	public void setUp() {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuarioRepository.save(usuario);
	}
	
	@After
	public void tearDown() {
		usuarioRepository.deleteAll();
	}
	
	@Test
	public void testFindByEmail() {
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		assertEquals(usuario.getEmail(), email);		
	}
	
	@Test
	public void testFindByNome() {
		Usuario usuario = this.usuarioRepository.findByNome(nome);
		assertEquals(usuario.getNome(), nome);		
	}
	
}
