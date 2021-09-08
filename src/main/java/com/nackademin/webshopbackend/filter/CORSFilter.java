package com.nackademin.webshopbackend.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {


	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		response.setHeader("Access-control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
				"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, x-requested-with, x-auth-token,Jwt-Token");
		response.setHeader("Access-Control-Expose-Headers", "Jwt-Token,access-control-request-headers, access-control-request-method, accept, origin, Authorization, x-requested-with");

		if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
			try {
				chain.doFilter(req, res);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			System.out.println("Pre-flight");

			response.setHeader("Access-Control-Allowed-Methods", "POST, GET, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "content-type,x-auth-token,Jwt-Token," +
					"access-control-request-headers, access-control-request-method, accept, origin, Authorization, x-requested-with");
			response.setHeader("Access-Control-Expose-Headers", "Jwt-Token,access-control-request-headers, access-control-request-method, accept, origin, Authorization, x-requested-with");
			response.setStatus(HttpServletResponse.SC_OK);
		}

	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

}