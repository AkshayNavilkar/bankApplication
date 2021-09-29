package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.bank.app.AppApplication.class, args);
	}

}
