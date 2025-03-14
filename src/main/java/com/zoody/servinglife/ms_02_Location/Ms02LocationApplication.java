package com.zoody.servinglife.ms_02_Location;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@OpenAPIDefinition(
		info = @Info(
				title = "SafeTap SOS API",
				version = "1.0",
				description = "API for tracking user locations and handling authentication"
		)
)
@SpringBootApplication
@EnableMongoRepositories
@EnableScheduling
@EnableWebSecurity
public class Ms02LocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms02LocationApplication.class, args);
	}


	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
