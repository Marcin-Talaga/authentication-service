package com.epam.authenticationservice.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomizedAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authEx) {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("CustomizedRealm");
		super.afterPropertiesSet();
	}

}
