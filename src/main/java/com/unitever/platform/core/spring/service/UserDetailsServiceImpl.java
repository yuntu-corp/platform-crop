package com.unitever.platform.core.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.user.dao.manual.UserDAO;
import com.unitever.module.user.model.User;
import com.unitever.platform.core.spring.security.SecurityUser;


@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	/**
	 * 为acegi提供用户信息
	 */
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException, DataAccessException {
		User user = userDAO.getUserWithLoginName(loginName);
		if (user == null) {
			return null;
		}
		return new SecurityUser(user);
	}
	
}
