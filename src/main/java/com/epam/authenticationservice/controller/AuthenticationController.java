package com.epam.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.authenticationservice.entity.User;
import com.epam.authenticationservice.entity.UserValidator;

@RestController
public class AuthenticationController {
	
	@Autowired
	UserValidator validator;
	
	@RequestMapping(method = RequestMethod.POST, path = "/auth")
	public ResponseEntity<HttpStatus> authenticateUser(@RequestBody User user) {
		
	boolean result = validator.checkIfUserIsValid(user).isPresent();
	
	return result ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.UNAUTHORIZED);

	}

}
