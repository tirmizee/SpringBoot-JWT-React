package com.tirmizee.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTExpiredException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JWTExpiredException(String msg) {
		super(msg);
	}
	
	public JWTExpiredException(Exception ex) {
		super(ex.getMessage());
	}

}
