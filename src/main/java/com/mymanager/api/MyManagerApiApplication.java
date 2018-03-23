package com.mymanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//resposável pelo startup da aplicação, atravez da anotação
@SpringBootApplication
@EnableCaching
public class MyManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyManagerApiApplication.class, args);
	}
}
