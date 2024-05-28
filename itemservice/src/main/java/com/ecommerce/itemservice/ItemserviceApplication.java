package com.ecommerce.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ItemserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ItemserviceApplication.class, args);
	}
}
