package com.tirmizee.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTUnsupportedException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JWTUnsupportedException(String msg) {
		super(msg);
	}

	public JWTUnsupportedException(Exception ex) {
		super(ex.getMessage());
	}

}
