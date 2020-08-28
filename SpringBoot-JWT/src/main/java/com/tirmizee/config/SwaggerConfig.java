package com.tirmizee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Profile("dev")
@Configuration
public class SwaggerConfig {

	private final long MAX_AGE_SECS = 3600;
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components())
            .info(new Info().title("Contact Application API").description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
    }

//	@Override
//	protected void addCorsMappings(CorsRegistry registry) {
//		 registry
//		 	.addMapping("/*")
//		 	.allowedOrigins("*")
//		 	.allowedHeaders("*")
//		 	.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
//		 	.maxAge(MAX_AGE_SECS);
//	}
	
}
