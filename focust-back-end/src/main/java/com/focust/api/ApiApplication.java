/***********************************************************************************************************************
 * ApiApplication.java
 *
 * The main executable java file for Focust's REST API.
 *
 * @author Allan DeBoe
 * @version 0.01, 10/31/2022
 * @since 0.01, 10/29/2022
 */
package com.focust.api;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Java Spring Boot
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// MAIN CLASS

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class	// Disables the "http://localhost:8080/login" page
})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
