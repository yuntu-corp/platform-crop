package com.unitever.module.homepage.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.homepage.util.ValidatePatchca;
import com.unitever.module.user.model.User;
import com.unitever.module.user.service.UserService;
import com.unitever.platform.core.common.helper.UserHelper;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/bd/welcome")
public class WelcomeController extends SpringController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/welcome")
	public String welcome() {
		this.getRequest().setAttribute("productTitle", User.PRODUCTTITLE);
		return "/module/homepage/welcome";
	}
	
	//登陆，然后去toIndex页面，跳转到其他业务模块
	@RequestMapping("/login")
	public String login() {
		this.getRequest().getSession().setAttribute("user", UserHelper.getCurrUser());
		this.getRequest().getSession().setAttribute("productTitle", "后台管理系统");
		return "/module/index/index";
	}
	
	@RequestMapping("/ajaxValidationUser")
	@ResponseBody
	public void ajaxValidationUser() {
		String loginName = this.getRequest().getParameter("loginName");
		String password = this.getRequest().getParameter("password");
		
		String result = "{\"successStatus\":\"false\",\"errorMsg\":\"用户名或密码错误！(Username or password error)\"}";
		try {
			boolean isSuccess = userService.validationUser(loginName, password);
			if (isSuccess) {
				result = "{\"successStatus\":\"true\"}";
			}
		} catch (Exception e) {
			result = "{\"successStatus\":\"false\",\"errorMsg\":\"" + e.getMessage() + "\"}";
		}
		this.print(result);
	}
	/**
	 * 获取验证码图片
	 */
	@RequestMapping("/validatePatchca")
	@ResponseBody
	public void validatePatchca() {
		ValidatePatchca.patchca(this.getRequest(), this.getResponse());	
	}
	/**
	 * 获取验证码数字
	 */
	@RequestMapping("/getSessionPatchca")
	@ResponseBody
	public void getSessionPatchca() {
		String token = (String) this.getRequest().getSession().getAttribute("PATCHCA");
		this.print(token);
	}
	/**
	 * 验证 验证码是否正确
	 */
	@RequestMapping("/validateSessionPatchca")
	@ResponseBody
	public void validateSessionPatchca(String imgjudge) {
		if(StringUtils.isBlank(imgjudge)){
			this.print(String.valueOf(false));
		}
		String token = (String) this.getRequest().getSession().getAttribute("PATCHCA");
		this.print(String.valueOf(imgjudge.equals(token)));
	}
}