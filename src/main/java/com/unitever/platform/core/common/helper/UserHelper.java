package com.unitever.platform.core.common.helper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.user.model.User;
import com.unitever.module.user.service.UserService;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.core.spring.security.SecurityUser;

/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class UserHelper{

	public static String getCurrUserID() {
		User user = getCurrUser();
		if(user == null){
			return null;
		}
		return user.getId();
	}
	
	/**
	 * 得到当前Spring安全 用户
	 */
	private static SecurityUser getCurrSecurityUser() {
		if (null == SecurityContextHolder.getContext()) {
			return null;
		}
		if (null == SecurityContextHolder.getContext().getAuthentication()) {
			return null;
		}
		Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user instanceof SecurityUser) {
			return (SecurityUser) user;
		} else {
			return null;
		}
	}
	

	private static boolean isExistsCurrUser(){
		return getCurrSecurityUser()!=null;
	}
	
	/**
	 *得到明文用户名
	 */
	public static String getCredentials(){
		if (null == SecurityContextHolder.getContext()) {
			return null;
		}
		if (null == SecurityContextHolder.getContext().getAuthentication()) {
			return null;
		}
		return (String)SecurityContextHolder.getContext().getAuthentication().getCredentials();
	}

	
	/**
	 * 得到当前系统用户
	 */
	public static User getCurrUser() {
		if (getCurrSecurityUser() == null) {
			return null;
		}
		User user = (User) SpringMVCUtil.getRequest().getSession().getAttribute("user");
		if(user != null){
			return user;
		}
		UserService userService = SpringContextHolder.getBean(UserService.class);
		return userService.getUserWithId(getCurrSecurityUser().getUser().getId());
	}

	
	/**
	 * 得到超级管理员用户
	 */
	public static User getSuperAdmin() {
		if (getCurrSecurityUser() == null) {
			return null;
		}
		UserService userService = SpringContextHolder.getBean(UserService.class);
		return userService.getUserWithLoginName(SecurityUser.SUPERADMIN_NAME);
	}
	
	/**
	 * 判断是否是超级管理员
	 */
	public static boolean isSuperAdmin() {
		//以前是用Authorize.ifAllGranted判断的，没写authorize，先用这个
		User currUser = getCurrUser();
		if (currUser != null && SecurityUser.SUPERADMIN_NAME.equals(currUser.getUsername())) {
			return true;
		}
		return false;
	}
}