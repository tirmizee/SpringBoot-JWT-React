package com.tirmizee.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTSignatureException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public JWTSignatureException(String msg) {
		super(msg);
	}
	
	public JWTSignatureException(Exception ex) {
		super(ex.getMessage());
	}

}
