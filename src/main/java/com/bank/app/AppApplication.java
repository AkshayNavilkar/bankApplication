package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.bank.app.service"})
@EntityScan("com.bank.app.model")
@EnableJpaRepositories("com.bank.app.repository")

public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.bank.app.AppApplication.class, args);
	}

}
