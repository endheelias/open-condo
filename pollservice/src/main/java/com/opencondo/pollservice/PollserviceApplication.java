package com.opencondo.pollservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application class, with a main method to start the embedded server.
 *
 * @author Endhe Elias
 * @version 0.1
 * @since 0.1
 */
@SpringBootApplication
public class PollserviceApplication {

	/**
	 * Starts this project with an embedded server.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PollserviceApplication.class, args);
	}
}
