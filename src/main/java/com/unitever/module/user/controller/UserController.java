package com.unitever.module.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.user.model.User;
import com.unitever.module.user.service.UserService;
import com.unitever.platform.core.common.helper.UserHelper;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value="/user")
public class UserController extends SpringController {
	/**
	 * 跳转至销售话术index页面
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "module/user/jsp/user-index";
	}
	/**
	 * 跳转至销售话术列表页面
	 * @param page
	 * @param propaganda
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(@FormModel("page") Page<User> page, @FormModel("model") User user){
		setAttribute("pageObj", userService.getPage(page, user));
		setAttribute("model", user);
		return "module/user/jsp/user-list";
	}
	/**
	 * 跳转至修改用户密码
	 * @return
	 */
	@RequestMapping(value="/toChangePassword",method=RequestMethod.GET)
	public String toChangePassword(){
		setAttribute("model", UserHelper.getCurrUser());
		return "module/user/jsp/user-changePasword";
	}
	/**
	 * 修改用户密码
	 */
	@RequestMapping(value="/changePassword",method=RequestMethod.POST)
	@ResponseBody
	public void changePassword(){
		String userId = getRequest().getParameter("id");
		String password = getRequest().getParameter("password");
		userService.changePassworkWithUserId(userId, password);
	}
	/**
	 * 跳转至个人设置
	 * @return
	 */
	@RequestMapping(value="/userInput",method=RequestMethod.GET)
	public String userInput(@RequestParam(value="id") String id){
		setAttribute("model", userService.getUserWithId(id));
		return "module/user/jsp/user-userInput";
	}
	/**
	 * 跳转至个人设置
	 * @return
	 */
	@RequestMapping(value="/input",method=RequestMethod.GET)
	public String input(@RequestParam(value="id", required= false) String id){
		String inputKind = getRequest().getParameter("inputKind");
		if("update".equals(inputKind)) {
			setAttribute("model", userService.getUserWithId(id));
		}
		return "module/user/jsp/user-input";
	}
	/**
	 * 添加修改用户
	 */
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public void saveOrUpdate(@FormModel("model") User user){
		userService.update(user);
		//初始化菜单
		//WeChatUtil.initMenu(user.getId());
	}
	
	@Autowired
	private UserService userService;
}