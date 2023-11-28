package com.ctytech.gameszone;

import java.time.ZoneId;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class GameszoneApplication {

	@Value("${app.config.time-zone:#{null}}")
	private Optional<String> TIME_ZONE;

	public static void main(String[] args) {
		SpringApplication.run(GameszoneApplication.class, args);
	}

	@PostConstruct
	public void init() {

		if (TIME_ZONE.isPresent() && ZoneId.getAvailableZoneIds().contains(TIME_ZONE.get())) {
			TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of(TIME_ZONE.get())));
		} else {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		}

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
