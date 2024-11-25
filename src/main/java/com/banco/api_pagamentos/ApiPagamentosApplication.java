package com.banco.api_pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPagamentosApplication.class, args);
	}

}
