package com.reto03.grupog6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude={SecurityAutoConfiguration.class})
public class Grupog6Application {

	public static void main(String[] args) {
		SpringApplication.run(Grupog6Application.class, args);
	}

}
