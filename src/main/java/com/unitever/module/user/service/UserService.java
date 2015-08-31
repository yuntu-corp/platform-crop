package com.unitever.module.user.service;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unitever.module.user.dao.manual.UserDAO;
import com.unitever.module.user.model.User;
import com.unitever.platform.core.dao.Page;

@Service
@Transactional
public class UserService {
	
	public Page<User> getPage(Page<User> page, User user) {
		List<User> userList = userDAO.getUserWithUser(user);
		page.setTotalRecord(userList.size());
		if (userList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(userList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(userList.subList(page.getStartOfPage(), userList.size()));
		}
		return page;
	}

	public boolean validationUser(String loginName , String password) throws Exception{
		if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password)){
			throw new Exception("用户名或密码不能为空！(The username or password cannot be empty)");
		}
		User user = this.getUserWithLoginName(loginName);
		if(user == null){
			return false;
		}
		String passwordMD5 = new Md5PasswordEncoder().encodePassword(password, null);
		if(!passwordMD5.equals(user.getPassword())){
			return false;
		}
		return true;
	}
	
	public User getUserWithLoginName(String loginName){
		if(StringUtils.isBlank(loginName)){
			return null;
		}
		return userDAO.getUserWithLoginName(loginName);
	}

	public User getUserWithId(String id) {
		return userDAO.get(id);
	}

	public void changePassworkWithUserId(String userId, String password) {
		User user = userDAO.get(userId);
		user.setPassword(new Md5PasswordEncoder().encodePassword(password, null));
		userDAO.update(user);
	}
	
	public List<User> getUserList() {
		return userDAO.getAll();
	}
	
	public void update(User user) {
		userDAO.update(user);
	}
	
	public User getUserWithWeChatNum(String userWeChatNum) {
		return userDAO.getUserWithWeChatNum(userWeChatNum);
	}
	@Autowired
	private UserDAO userDAO;
}