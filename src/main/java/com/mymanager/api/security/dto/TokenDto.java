package com.mymanager.api.security.dto;

/**
 * DTO responsável pela integração do front com o back. Controle do Token
 * autenticado
 * 
 * @author Yuri Oliveira
 *
 */
public class TokenDto {

	private String token;

	public TokenDto() {
	}

	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
