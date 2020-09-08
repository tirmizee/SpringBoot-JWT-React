package com.tirmizee.config.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Pratya Yeekhaday
 *
 */

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String ipAddress;
	private String userAgent;
	private String jti;
	private String ip;
	private Long iat;
	private Long exp;
	private Collection<? extends GrantedAuthority> authorities;
	
	private boolean isEnabled;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	
	public UserDetailsImpl(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }
	
	public UserDetailsImpl(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.authorities = builder.authorities;
        this.isEnabled = builder.isEnabled;
        this.isAccountNonExpired = builder.isAccountNonExpired;
        this.isAccountNonLocked = builder.isAccountNonLocked;
        this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Override
    public boolean equals(Object obj) {
		if (obj instanceof UserDetails) {
			return username.equals(((UserDetails) obj).getUsername());
        }
        return false;
    }

	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : 0;
	}
	
	public static class Builder {
		
		private String username;
		private String password;
		private Collection<? extends GrantedAuthority> authorities;
		
		private boolean isEnabled;
		private boolean isAccountNonExpired;
		private boolean isAccountNonLocked;
		private boolean isCredentialsNonExpired;
		
		public Builder(){}
		
		public Builder username(String username){
			this.username = username;
			return this;
		}
		
		public Builder password(String password){
			this.password = password;
			return this;
		}
		
		public Builder authorities(Collection<? extends GrantedAuthority> authorities){
			this.authorities = authorities;
			return this;
		}
		
		public Builder isEnabled(boolean isEnabled){
			this.isEnabled = isEnabled;
			return this;
		}
		
		public Builder isAccountNonExpired(boolean isAccountNonExpired){
			this.isAccountNonExpired = isAccountNonExpired;
			return this;
		}
		
		public Builder isAccountNonLocked(boolean isAccountNonLocked){
			this.isAccountNonLocked = isAccountNonLocked;
			return this;
		}
		
		public Builder isCredentialsNonExpired(boolean isCredentialsNonExpired){
			this.isCredentialsNonExpired = isCredentialsNonExpired;
			return this;
		}
		
		public  UserDetailsImpl build() {
			return new UserDetailsImpl(this);
		}
		
	}

}
