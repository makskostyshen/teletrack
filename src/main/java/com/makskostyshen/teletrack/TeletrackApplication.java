package com.makskostyshen.teletrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.makskostyshen.teletrack.config")
public class TeletrackApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(TeletrackApplication.class, args);
	}
}
