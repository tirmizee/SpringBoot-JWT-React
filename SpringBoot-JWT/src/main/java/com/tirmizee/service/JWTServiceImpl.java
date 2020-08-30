package com.tirmizee.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.tirmizee.config.filter.JWTAuthorizationFilter;
import com.tirmizee.exception.JWTExpiredException;

import io.jsonwebtoken.Claims;

@Service
public class JWTServiceImpl implements JWTService, InitializingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	public static final String BLACKLIST_KEY = "TOKEN_BLACKLIST#";
	public static final String TRUST_KEY = "TOKEN_TRUST#";
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, String> tokenOperations;
	private HashOperations<String, String, Long> tokenBlacklistOperations;

	@Override
	public void afterPropertiesSet() throws Exception {
		tokenOperations = redisTemplate.opsForHash();
		tokenBlacklistOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public void addBlackListToken(String jti, Long exp) {
		tokenBlacklistOperations.put(BLACKLIST_KEY, jti, exp);
		redisTemplate.expire(BLACKLIST_KEY + jti, exp, TimeUnit.NANOSECONDS);
	}

	@Override
	public boolean isBlackList(String jti) {
		return tokenBlacklistOperations.get(BLACKLIST_KEY, jti) != null;
	}

	@Override
	public Long getBlackList(String jti) {
		return tokenBlacklistOperations.get(BLACKLIST_KEY, jti);
	}

	@Override
	public Map<String, Long> allBlackList() {
		return tokenBlacklistOperations.entries(BLACKLIST_KEY);
	}

	@Override
	public Long deleteBlackList(String jti) {
		return tokenBlacklistOperations.delete(BLACKLIST_KEY, jti);
	}
	
	@Override
	public void addToken(String username, String tokenId ,Date exp) {
		tokenOperations.put(TRUST_KEY, username, tokenId);
//		redisTemplate.expireAt(TRUST_KEY + username, exp);
	}
	
	@Override
	public String getToken(Claims claims) {
		return tokenOperations.get(TRUST_KEY, claims.getSubject());
	}

	@Override
	public void validateToken(Claims claims) {
		
		if (claims.getExpiration().before(new Date())) {
			throw new JWTExpiredException("Expired");
		}
		
		if (isBlackList(claims.getId())) {
			throw new JWTExpiredException("Expired");
		}	
		
		if (!claims.getId().equals(getToken(claims))) {
			throw new JWTExpiredException("Expired");
		}	
		
	}

	
}
