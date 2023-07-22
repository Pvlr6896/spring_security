package com.lokesh.springsecurity;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//now we are trying to create our own AuthenticationProvider 
//without the UserDetailService
// this interface has two methods as below
//TODO
// To check this we have to comment the UserDetailService 
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	// the Authentication object has two parameters say user and password
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		// as the getCredentials is an object, covert it to string
		String password = authentication.getCredentials().toString();
		if ("Lokesh".equals(userName) && "Pathapati".equals(password)) {
			return new UsernamePasswordAuthenticationToken(userName, password, Arrays.asList());
		}
		return null;
	}

	// the second method is to tell we
	// support the UsernamePasswordAuthenticationToken and return a boolean
	@Override
	public boolean supports(Class<?> authentication) {
		// this checks for the UsernamePasswordAuthenticationToken
		// from the authentication object and returns true/false
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
