package com.ecommerce.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication{
//
//	@Value("${spring.sleuth.web.skipPattern}")
//	private String skipPattern;
	 
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
}
