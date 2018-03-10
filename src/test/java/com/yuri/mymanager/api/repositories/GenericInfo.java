package com.yuri.mymanager.api.repositories;

import java.util.Date;

import com.yuri.mymanager.api.entities.Cabecalho;
import com.yuri.mymanager.api.entities.Integrante;
import com.yuri.mymanager.api.entities.Produto;
import com.yuri.mymanager.api.entities.Usuario;
import com.yuri.mymanager.api.enums.TipoEnum;

public class GenericInfo {
	
	public static final String descricaoProduto = "produto teste";
	public static final String email = "teste@teste";
	public static final String nome = "root";
	public static final String senha = "root";
	public static final Date dataCriacao = new Date();
	public static final TipoEnum tipo = TipoEnum.COMPRA;
	public static final String cpf_cnpj = "39063491875";
	public static final String nomeIntegrante = "Yuri Oliveira";
	
	public static final Long id = 1L;
	
	public static final Usuario usuario = novoUsuario();
	public static final Produto produto = novoProduto();
	public static final Integrante integrante = novoIntegrante();
	public static final Cabecalho cabecalho = novoCabecalho();
	
	public static Produto novoProduto() {
		Produto produto = new Produto();
		produto.setDescricao(descricaoProduto);
		produto.setUsuario(usuario);
		produto.setId(id);
		
		return produto;
	}
	
	public static Usuario novoUsuario() {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setId(id);
		
		return usuario;
	}
	
	public static Cabecalho novoCabecalho() {
		Cabecalho cabecalho = new Cabecalho();
		cabecalho.setDataCriacao(dataCriacao);
		cabecalho.setDataMovimento(dataCriacao);
		cabecalho.setIntegrante(integrante);
		cabecalho.setTipo(tipo);
		cabecalho.setUsuario(usuario);
		cabecalho.setId(id);
		
		return cabecalho;
	}
	
	public static Integrante novoIntegrante() {
		Integrante integrante = new Integrante();
		integrante.setBairro("Luiza 2");
		integrante.setCidade("Franca");
		integrante.setComplemento("apto 11");
		integrante.setCpfCnpj(cpf_cnpj);
		integrante.setDataCriacao(dataCriacao);
		integrante.setDdd(16);
		integrante.setNome(nomeIntegrante);
		integrante.setNumero(2965);
		integrante.setRua("Rubens Carbone");
		integrante.setTelefone(Long.parseLong("992039581"));
		integrante.setUf("SP");
		integrante.setUsuario(usuario);
		integrante.setId(id);
		
		return integrante;
	}
}
