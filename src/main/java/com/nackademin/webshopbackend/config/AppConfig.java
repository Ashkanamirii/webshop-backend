package com.nackademin.webshopbackend.config;

import com.nackademin.webshopbackend.models.Role;
import com.nackademin.webshopbackend.models.Users;
import com.nackademin.webshopbackend.repos.UserDAO;
import com.nackademin.webshopbackend.security.JWTUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.List;

/**
 * Created by Ashkan Amiri
 * Date:  2021-09-03
 * Time:  00:25
 * Project: webshop-backend
 * Copyright: MIT
 */
@Configuration
public class AppConfig {
	@Value("${security.signingKey}")
	private String signingKey;

	@Value("${security.algorithm}")
	private String algorithm;

	@Value("${security.validMinutes}")
	private Integer validMinutes;


	@Bean
	public UserRepository userRepository(BCryptPasswordEncoder passwordEncoder) {
		UserDAO userRepository = new UserDAO();
		Users u = new Users();
		u.setEmail("Hakim@livs.com");
		u.setPassword(passwordEncoder.encode("123456hakim"));
		u.setRoles(List.of(new Role(1L,"ADMIN")));
		userRepository.save(u);
		return userRepository;
	}

	@Bean
	public SignupService signupService(UserRepository userRepository) {
		SignupService signupService = new SignupService(userRepository);
		return signupService;
	}

	@Bean
	public JWTUtils jwtUtils() {
		final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.forName(algorithm);
		final byte[] signingKeyBytes = Base64.encodeBase64(signingKey.getBytes());
		return new JWTUtils(new SecretKeySpec(signingKeyBytes, signatureAlgorithm.getJcaName()),
				Duration.ofMinutes(validMinutes));
	}
}
