package com.boilerPlate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BoilerPlateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoilerPlateApplication.class, args);
	}

}
