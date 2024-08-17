/**
 * ApiServerApplication -- Class for the main executable for the RESTful Back-end Server
 * Copyright (C) 2023  Allan DeBoe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 * /////////////////////////////////////////////////////////////
 *
 * @author	Allan DeBoe (allan.m.deboe@gmail.com)
 * @date	November 5th, 2023
 */
package com.focust.api;

////////////////////////////////////////////////////////////////////////////

/** Spring Framework **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
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
