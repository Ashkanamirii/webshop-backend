package com.nackademin.webshopbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nackademin.webshopbackend.repos.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-02
 * Time:  21:05
 * Project: webshop-backend
 * Copyright: MIT
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDAO userRepo;
	private final PasswordEncoder passwordEncoder;
	private final JWTUtils jwtUtils;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		final JWTAuthenticationFilter filter =
				new JWTAuthenticationFilter(authenticationManager(), jwtUtils, new ObjectMapper());
		final JWTAuthorizationFilter jwtAuthorizationFilter =
				new JWTAuthorizationFilter(authenticationManager(), jwtUtils);

		http
				.csrf()
				.disable()
				.cors()
				.disable()
				.authorizeRequests()
				.antMatchers("/user/*").permitAll()
				.antMatchers("/swagger-ui/*", "/swagger-ui.html",
						"/webjars/**", "/v2/**", "/swagger-resources/**").permitAll()
				.anyRequest().authenticated().and()
				.addFilter(filter)
				.addFilter(jwtAuthorizationFilter)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userRepo :: findByEmail)
				.passwordEncoder(passwordEncoder);
	}
}
