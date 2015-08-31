package com.unitever.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unitever.demo.model.TBUser;
import com.unitever.demo.service.TBUserService;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/dm/page")
public class PageController extends SpringController {
	final static Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private TBUserService userService;
	@RequestMapping("/demo1")
	public String demo1(@FormModel("page") Page<TBUser> page) {
		this.getRequest().setAttribute("pageObj", userService.getPageWithDemo(page));
		return "/demo/component/page/demo1";
	}
	
	@RequestMapping("/ajaxDemo1/{type}")
	public String ajaxDemo1(@FormModel("page") Page<TBUser> page,@PathVariable String type) {
		this.getRequest().setAttribute("pageObj", userService.getPageWithDemo(page));
		this.getRequest().setAttribute("type", type);
		return "/demo/component/page/ajaxDemo1";
	}
	

	
}
