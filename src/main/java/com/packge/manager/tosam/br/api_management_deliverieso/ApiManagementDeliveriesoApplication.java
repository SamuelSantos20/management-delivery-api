package com.packge.manager.tosam.br.api_management_deliverieso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiManagementDeliveriesoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiManagementDeliveriesoApplication.class, args);
	}

}
