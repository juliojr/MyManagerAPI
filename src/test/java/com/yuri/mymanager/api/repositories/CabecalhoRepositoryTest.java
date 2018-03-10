package com.yuri.mymanager.api.repositories;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.mymanager.api.entities.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@SuppressWarnings("static-access")
public class CabecalhoRepositoryTest {
	@Autowired
	private CabecalhoRepository cabecalhoRepository;
	
	@Autowired
	private IntegranteRepository integranteRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private GenericInfo info = new GenericInfo();
	private Usuario u;
	
	@Before
	public void SetUp() {
		u = this.usuarioRepository.save(info.usuario);
		info.integrante.setUsuario(u);
	}
	
	@After
	public void tearDown() {
		this.cabecalhoRepository.deleteAll();
		this.integranteRepository.deleteAll();
		this.usuarioRepository.deleteAll();
	}	
}
