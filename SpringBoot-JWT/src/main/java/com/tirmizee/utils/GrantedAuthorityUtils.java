package com.tirmizee.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tirmizee.jpa.entities.Permission;

/**
 * @author Pratya Yeekhaday
 *
 */
public class GrantedAuthorityUtils extends AuthorityUtils {

	public static List<GrantedAuthority> createGrantedAuthority(List<? extends Permission> permissions){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(permissions.size());
		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPerCode()));
		}
		return authorities;
	}
	
	public static List<GrantedAuthority> createAuthorityList(List<String> authoritiesString) {
		if (authoritiesString == null) {
			throw new IllegalArgumentException("Cannot pass null authority");
		}

		List<GrantedAuthority> authorities = new ArrayList<>(authoritiesString.size());
		for (String authority : authoritiesString) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}
	
}
