package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.bank.app.AppApplication.class, args);
	}

}
