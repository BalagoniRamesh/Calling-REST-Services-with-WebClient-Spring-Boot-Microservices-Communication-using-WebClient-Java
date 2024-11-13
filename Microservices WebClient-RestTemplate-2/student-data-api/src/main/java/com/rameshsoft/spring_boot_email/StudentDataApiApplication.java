package com.rameshsoft.spring_boot_email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentDataApiApplication {

@Bean
public WebClient.Builder getWebClient(){
	return WebClient.builder();
}
	public static void main(String[] args) {
		SpringApplication.run(StudentDataApiApplication.class, args);		
	}

}
