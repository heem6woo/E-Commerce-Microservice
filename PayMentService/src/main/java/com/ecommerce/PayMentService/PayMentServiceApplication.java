package com.ecommerce.PayMentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PayMentServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayMentServiceApplication.class, args);
	}

}
