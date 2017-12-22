package com.yuri.mymanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//resposável pelo startup da aplicação, atravez da anotação
@SpringBootApplication
public class MyManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyManagerApiApplication.class, args);
	}
}
