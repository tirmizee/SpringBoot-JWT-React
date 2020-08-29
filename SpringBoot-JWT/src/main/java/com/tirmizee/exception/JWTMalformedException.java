package com.tirmizee.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTMalformedException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JWTMalformedException(String msg) {
		super(msg);
	}
	
	public JWTMalformedException(Exception ex) {
		super(ex.getMessage());
	}

}
