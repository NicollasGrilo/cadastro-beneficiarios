package com.example.cadastrobeneficiario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@SpringBootApplication
public class CadastroBeneficiarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroBeneficiarioApplication.class, args);
	}

}
