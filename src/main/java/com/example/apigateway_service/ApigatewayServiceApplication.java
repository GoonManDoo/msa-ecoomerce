package com.example.apigateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApigatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayServiceApplication.class, args);
	}
	// mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=포트번호'
}
