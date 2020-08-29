package com.tirmizee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.tirmizee.config.properties.JWTProperty;
import com.tirmizee.config.properties.RabbitMQProperty;

@SpringBootApplication
@EnableConfigurationProperties({
	JWTProperty.class,
	RabbitMQProperty.class
})
public class SpringBootJwtApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

}
