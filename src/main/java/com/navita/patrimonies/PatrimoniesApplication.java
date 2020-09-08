package com.navita.patrimonies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PatrimoniesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrimoniesApplication.class, args);
	}
}
