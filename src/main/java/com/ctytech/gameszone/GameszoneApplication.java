package com.ctytech.gameszone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:messages.properties" })
public class GameszoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameszoneApplication.class, args);
	}

}
