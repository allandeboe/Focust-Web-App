/**
 * ApiServerApplication
 *
 * This is the main class for the RESTful back-end server
 *
 * @author	Allan DeBoe (allan.m.deboe@gmail.com)
 * @date	November 5th, 2023
 */
package com.focust.api;

////////////////////////////////////////////////////////////////////////////

/** Spring Framework **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

////////////////////////////////////////////////////////////////////////////

@SpringBootApplication
@EnableAutoConfiguration(exclude={
		SecurityAutoConfiguration.class	// This disables the "http://localhost:8080/login" page.
})
public class ApiServerApplication {

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication spring_app = new SpringApplication(ApiServerApplication.class);
		spring_app.run(args);
	}

	@Bean
	public WebMvcConfigurer corsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:" + environment.getProperty("server.port"));
			}
		};
	}

}
