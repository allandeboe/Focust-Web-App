/**
 * SecurityConfiguration.java
 *
 * Will be useful for later when handling security-related
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
