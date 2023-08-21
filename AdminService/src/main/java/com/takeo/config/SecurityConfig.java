package com.takeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//It allows you to use Spring Security's method-level security annotations on your beans. The prePostEnabled attribute, when set to true, 
//allows the use of Spring's @PreAuthorize and @PostAuthorize annotations for access control at the method level.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

		// OAuth configuration

		security.authorizeHttpRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();
		return security.build();

	}

}
