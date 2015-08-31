package com.unitever.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/dm/component")
public class ComponentController extends SpringController {

	@RequestMapping("/saveUEditor")
	@ResponseBody
	public void saveUEditor(@RequestParam(value = "modelName") String modelName) {
		System.out.println(modelName);
	}
	
	@RequestMapping("/validateRemote")
	@ResponseBody
	public String validateRemote() {
		String remote = this.getRequest().getParameter("remote");
		if("haha".equals(remote)){
			return "true";
		}
		return "false";
	}
	
}
