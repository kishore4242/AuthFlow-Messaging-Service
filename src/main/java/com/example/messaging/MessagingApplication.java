package com.example.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

}
