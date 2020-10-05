package com.github.fgsantana.transportapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
public class TransportApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportApiApplication.class, args);
	}

}
