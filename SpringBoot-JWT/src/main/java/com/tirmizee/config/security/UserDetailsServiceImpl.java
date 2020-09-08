package com.tirmizee.config.security;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tirmizee.jpa.entities.Permission;
import com.tirmizee.jpa.entities.User;
import com.tirmizee.jpa.repositories.PermissionRepository;
import com.tirmizee.jpa.repositories.UserRepository;
import com.tirmizee.utils.GrantedAuthorityUtils;

/**
 * @author Pratya Yeekhaday
 *
 */
@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	public final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			LOGGER.debug("User not found with username : {}", username );
			throw new UsernameNotFoundException("User not found with username : " + username );
		}
		
		List<Permission> permissions = permissionRepository.findByUsername(username);
		LOGGER.info("username : {}, authorities : {}", username, permissions.stream().map(e -> e.getPerCode()).collect(Collectors.toList()).toString());
		
		return new UserDetailsImpl.Builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.isEnabled(user.getEnabled())
			.isAccountNonExpired(user.getAccountNonExpired())
			.isAccountNonLocked(user.getAccountNonLocked())
			.isCredentialsNonExpired(user.getCredentialsNonExpired())
			.authorities(GrantedAuthorityUtils.createGrantedAuthority(permissions))
			.build();
	}
	
}
