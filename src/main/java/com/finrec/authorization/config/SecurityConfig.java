package com.finrec.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				// Disable CSRF for REST APIs
				.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth
						// Permit internal policy evaluation calls from Gateway
						.requestMatchers("/api/v1/authorize").permitAll()
						// Require auth for any other endpoints if present
						.anyRequest().authenticated())
				.build();
	}
}