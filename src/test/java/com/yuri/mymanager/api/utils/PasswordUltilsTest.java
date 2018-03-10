package com.yuri.mymanager.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUltilsTest {
	
	private static final String senha = "root";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
	
	//@Test
	public void testSenhaNulo(){
		assertNull(PasswordUtils.gerarBCrypt(null));
	}
	
	//@Test
	public void testHash() {
		String hash = PasswordUtils.gerarBCrypt(senha);
		//System.out.println(hash);
		
		assertTrue(bCryptEncoder.matches(senha, hash));		
	}
}
