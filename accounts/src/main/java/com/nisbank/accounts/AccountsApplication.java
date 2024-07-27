package com.nisbank.accounts;

import com.nisbank.accounts.dto.AccountContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "NisBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Nir Bar Levav",
						email = "nir@nisbank.com",
						url = "https://www.nisbank94.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.nisbank94.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "NisBank Accounts microservice REST API Documentation",
				url = "https://www.nisbank94.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
