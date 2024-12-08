package com.boilerPlate;

import com.boilerPlate.monitorEx.v0.OrderConfigV0;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;

//@Import(OrderConfigV0.class)
@EnableCaching
@SpringBootApplication
public class BoilerPlateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoilerPlateApplication.class, args);
	}

}
