package com.nackademin.webshopbackend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nackademin.webshopbackend.controllers.dto.UserDTO;
import com.nackademin.webshopbackend.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private AuthenticationManager authenticationManager;
	private JWTUtils jwtUtils;
	private ObjectMapper objectMapper;

	public JWTAuthenticationFilter(final AuthenticationManager authenticationManager, final JWTUtils jwtUtils,
	                               final ObjectMapper objectMapper) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.objectMapper = objectMapper;
	}


	@Override
	public Authentication attemptAuthentication(final HttpServletRequest req,
	                                            final HttpServletResponse res) throws AuthenticationException {
		return getPrincipal(req)
				.map(user -> authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								user.getUsername(),
								user.getPassword(),
								new ArrayList<>()))
				)
				.orElse(null);
	}

	private Optional<UserDTO> getPrincipal(HttpServletRequest req) {
		try {
			return Optional.of(objectMapper.readValue(req.getInputStream().readAllBytes(), UserDTO.class));
		} catch (IOException e) {
			LOG.info("Unable to fetch user from request");
			return Optional.empty();
		}
	}

	@Override
	protected void successfulAuthentication(final HttpServletRequest req,
	                                        final HttpServletResponse res,
	                                        final FilterChain chain,
	                                        final Authentication auth) throws IOException {
		Users user = (Users) auth.getPrincipal();
		String generateToken = jwtUtils.generateToken(user);
		res.getWriter().write(generateToken);
		res.getWriter().flush();
	}
}