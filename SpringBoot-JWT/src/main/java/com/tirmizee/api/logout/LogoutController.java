package com.tirmizee.api.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tirmizee.config.security.CurrentUser;
import com.tirmizee.config.security.UserDetailsImpl;
import com.tirmizee.service.JWTService;

@RestController
@RequestMapping(path = "/auth/token/revoke")
public class LogoutController {
	
	@Autowired
	private JWTService jwtService;
	
	@GetMapping
	public Object logout() {
		UserDetailsImpl currentUser = CurrentUser.getDetail();
		jwtService.addBlackListToken(currentUser.getJti(),currentUser.getExp());
		return jwtService.allBlackList();
	}

}
