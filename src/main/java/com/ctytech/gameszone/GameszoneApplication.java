package com.ctytech.gameszone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class GameszoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameszoneApplication.class, args);
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			public void addCorsMappings(CorsRegistry corsRegistry) {
				corsRegistry
						.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowCredentials(false)
						.allowedHeaders("Origin", "Content-Type", "Accept", "Authorization", "Accept-Language",
								"connection", "Cache-Control", "Access-Control-Request-Method",
								"Access-Control-Request-Headers")
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD")
						.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
			}
		};
	}
}
