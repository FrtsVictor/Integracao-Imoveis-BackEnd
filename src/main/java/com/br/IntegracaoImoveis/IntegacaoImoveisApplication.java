package com.br.IntegracaoImoveis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
public class IntegacaoImoveisApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegacaoImoveisApplication.class, args);
	}

}
