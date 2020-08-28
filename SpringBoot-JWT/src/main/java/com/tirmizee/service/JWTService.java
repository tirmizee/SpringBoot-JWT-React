package com.tirmizee.service;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;

public interface JWTService {

	void addBlackListToken(String jti, Long exp);
	
	boolean isBlackList(String jti);
	
	Long getBlackList(String jti);
	
	Long deleteBlackList(String jti);
	
	Map<String, Long> allBlackList();
	
	void addToken(String username, String tokenId ,Date exp);
	
	String getToken(Claims claims);
	
	void validateToken(Claims claims);
	
}
