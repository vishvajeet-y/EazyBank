package com.vish.account;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservice REST API Documentation",
				description = "Eazy bank Account microservice REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Vishvajeet",
						email = "vish@gmail.com",
						url="https://eazybytes.com"
				),
				license = @License(
						name = "Apache 2.0",
						url="https://eazybytes.com"
				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Eazy Bank Account microservice REST API Documentation",
				url="https://www.eazybytes.com/swagger-ui.html"
		)

)
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

}
