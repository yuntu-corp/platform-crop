package com.unitever.platform.core.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unitever.platform.core.spring.SpringContextHolder;

/**
 * @Description : ()
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */

public class UserUtil {
	final static Logger logger = LoggerFactory.getLogger(UserUtil.class);

	/**
	 * 得到当前用户的ID
	 * 
	 * @author : wangdawei
	 */
	public static String getCurrUserID() {
		IUserHelper userHelper = SpringContextHolder.getBeanOneOfType(IUserHelper.class);
		return userHelper == null ? null : userHelper.getCurrUserID();
	}
}
