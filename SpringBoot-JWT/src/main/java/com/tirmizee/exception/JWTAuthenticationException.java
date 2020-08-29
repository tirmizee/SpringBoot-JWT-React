package com.tirmizee.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JWTAuthenticationException(String msg) {
		super(msg);
	}
	
	public JWTAuthenticationException(Exception exception) {
		super(exception.getMessage());
	}

}
