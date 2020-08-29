package com.tirmizee.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
	
	public static UserDetailsImpl getDetail(){
		return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	} 

}
