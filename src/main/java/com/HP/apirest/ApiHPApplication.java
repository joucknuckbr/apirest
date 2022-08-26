package com.HP.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching  
@SpringBootApplication
public class ApiHPApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHPApplication.class, args);
	}

}
