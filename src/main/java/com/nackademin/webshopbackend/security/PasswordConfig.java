package com.nackademin.webshopbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-02
 * Time:  21:17
 * Project: webshop-backend
 * Copyright: MIT
 */
@Configuration
public class PasswordConfig {
	@Bean
	public PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder(10);
	}
}
