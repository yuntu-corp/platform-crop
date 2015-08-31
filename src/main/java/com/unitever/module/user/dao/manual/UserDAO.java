package com.unitever.module.user.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.module.user.model.User;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class UserDAO extends BaseDAO<User, String>{

	/**
	 * 根据用户名得到启用的系统用户
	 */
	public User getUserWithLoginName(String loginName){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("username", loginName);
		List<User> usrList = this.getList("getUserWithLoginName", params);
		return usrList.size() > 0 ? usrList.get(0) : null;
	}

	public User getUserWithWeChatNum(String userWeChatNum) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("weChatNum", userWeChatNum);
		List<User> usrList = this.getList("getUserWithWeChatNum", params);
		return usrList.size() > 0 ? usrList.get(0) : null;
	}

	public List<User> getUserWithUser(User user) {
		return this.getList("getUserListWithUser", user);
	}
}