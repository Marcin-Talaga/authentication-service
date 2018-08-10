package com.epam.authenticationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.epam.authenticationservice.security.CustomizedAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${password}")
	private String password;
	@Value("${login}")
	private String username;

	@Autowired
	CustomizedAuthenticationEntryPoint entryPoint;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().httpBasic().authenticationEntryPoint(entryPoint).and().authorizeRequests()
				.anyRequest().authenticated();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		auth.inMemoryAuthentication().passwordEncoder(encoder).withUser(username).password(encoder.encode(password))
				.roles("USER");

	}


}
