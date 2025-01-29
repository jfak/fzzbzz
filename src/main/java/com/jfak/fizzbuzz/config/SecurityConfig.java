package com.jfak.fizzbuzz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private static final String[] WHITE_LIST_URL = {"/fizzbuzz/**", "/top/**", "/api-docs/**", "/swagger-ui/**"};
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
  		.csrf(AbstractHttpConfigurer::disable)
  		.authorizeHttpRequests( auth -> auth.requestMatchers(WHITE_LIST_URL).permitAll())
  		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
  		;	

  	    return http.build();
	}	
	
	
}
