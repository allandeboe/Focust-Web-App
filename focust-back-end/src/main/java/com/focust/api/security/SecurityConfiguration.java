/**
 * SecurityConfiguration -- Used for security-related purposes (more detailed description below)
 * Copyright (C) 2024  Allan DeBoe
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
 * //////////////////////////////////////////////////////////////////////
 *
 * This class will be useful for later when handling security-related
 * implementations, like JWT tokens, and user authentication and
 * authorization.
 *
 * @author  Allan DeBoe (allan.m.deboe@gmail.com)
 * @date    April 7th, 2024
 */
package com.focust.api.security;

///////////////////////////////////////////////////////////

/** Spring Framework **/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

///////////////////////////////////////////////////////////

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // GET requests should be authorized for even anonymous users
        http.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET).permitAll());

        // Other kinds of requests will require proper authorization.
        // For now, this is done through spring auth, but later JWT tokens will be
        // implemented to handle this instead.
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
