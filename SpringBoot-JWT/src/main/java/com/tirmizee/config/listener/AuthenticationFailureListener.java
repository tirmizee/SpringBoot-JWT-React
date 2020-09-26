package com.tirmizee.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener {

	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureListener.class);
	
	public AuthenticationFailureListener() {}
	
	@EventListener
	public void onAuthenticationFailureBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
		LOGGER.info("AuthenticationFailureListener : {}", event.getAuthentication().getName());
	}
	
}
