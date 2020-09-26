package com.tirmizee.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessListener.class);
	
	public AuthenticationSuccessListener() {}

	@EventListener
	public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
		LOGGER.info("AuthenticationSuccessListener : {}", event.getAuthentication().getName());
	}
	
}
