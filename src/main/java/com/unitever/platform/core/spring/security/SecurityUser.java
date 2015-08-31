package com.unitever.platform.core.spring.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.unitever.module.user.model.User;


/**
 * @author : wdw
 */

public class SecurityUser implements UserDetails {
	private static final long serialVersionUID = 5515208224430753487L;
	
	private User user;
	public static final String AUTH_PREFIX = "AUTH_";
	// 虚拟的超级管理员授权，只在设置了超级管理员起作用时，会把系统所有的资源都授于此权限
	public static final String AUTH_SUPERADMIN = AUTH_PREFIX + "SUPERADMIN";
	public static final String AUTH_DEFAULT = AUTH_PREFIX + "DEFAULT";
	public static final String SUPERADMIN_NAME = "administrator";
	public static final String ADMIN_NAME = "admin";
	
	
	public SecurityUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		authSet.add(new GrantedAuthorityImpl(AUTH_DEFAULT));
		//超级管理员
		if (user.getUsername().equals(SUPERADMIN_NAME)) {
			authSet.add(new GrantedAuthorityImpl(AUTH_SUPERADMIN));
			
		}else{
			//用户自定义权限
			
		}
		return authSet;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.user==null){
			return false;
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
}


