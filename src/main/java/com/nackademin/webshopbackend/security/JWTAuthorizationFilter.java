package com.nackademin.webshopbackend.security;

import com.nackademin.webshopbackend.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private final JWTUtils jwtUtils;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTUtils jwtUtils) {
        super(authManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        LOG.info("Försöker att logga in: {}", token);
        if (token.startsWith("Bearer ")) {
            token.substring("Bearer ".length());
            Users user = jwtUtils.validate(token);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }

            LOG.info("Kunde inte parsa token och därför inte logga in: {}", token);

            return null;
        }

        LOG.info("Kunde inte hitta token, har du en sådan i din header?");

        return null;
    }
}
