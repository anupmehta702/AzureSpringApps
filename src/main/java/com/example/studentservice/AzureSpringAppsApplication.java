package com.example.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.studentservice")
public class AzureSpringAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureSpringAppsApplication.class, args);
	}
}


