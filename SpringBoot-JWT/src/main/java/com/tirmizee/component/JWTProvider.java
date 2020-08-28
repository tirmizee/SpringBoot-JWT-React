package com.tirmizee.component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.tirmizee.config.properties.JWTProperty;
import com.tirmizee.config.security.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private JWTProperty jwtProperty;
	
	public String getUsername(String token) {
		return getClaim(token).getSubject();
	}

	public Date getExpirationDate(String token) {
		return getClaim(token).getExpiration();
	}
	
	public String getId(String token) {
		return getClaim(token).getId();
	}

	public Claims getClaim(String token) {
		return Jwts.parser().setSigningKey(jwtProperty.getSecret()).parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDetailsImpl userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("ip", userDetails.getIpAddress());
		claims.put("authorities", AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public String refreshToken(String token) {
		Claims claims = getClaim(token);
		return refreshToken(claims);
	}
	
	public String refreshToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpire() * 60 * 1000 ))
				.compact();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		Random random = new Random();
		return Jwts.builder()
				.setClaims(claims)
				.setId(new UUID(random.nextLong(), random.nextLong()).toString())
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpire() * 60 * 1000 ))
				.signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
				.compact();
	}
	
	public void extendTokenExpire(String jwt) {
		Jwts
			.parser()
			.setSigningKey(jwtProperty.getSecret())
			.parseClaimsJws(jwt)
			.getBody()
			.setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpire()));
	}

}
