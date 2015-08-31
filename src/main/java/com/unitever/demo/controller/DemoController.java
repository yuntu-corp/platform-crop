package com.unitever.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.demo.model.TBUser;
import com.unitever.platform.core.web.controller.SpringController;
import com.unitever.platform.util.tree.TreeNode;
import com.unitever.platform.util.tree.TreeUtil;

@Controller
@RequestMapping("/dm")
public class DemoController extends SpringController {
	final static Logger logger = LoggerFactory.getLogger(DemoController.class);
	@RequestMapping("/test")
	public String test() {
		logger.error("这个东西错了！~");
		logger.warn("这个东西错了！~");
		logger.info("这个东西错了！~");
		logger.debug("这个东西错了！~");
		return "/demo/test";
	}

	@RequestMapping("/test2")
	@ResponseBody
	public String test2(@RequestParam(value = "a") String a) {
		return a;
	}

	@RequestMapping("/test3")
	@ResponseBody
	public String test3(String a, HttpServletResponse res) {
		return "OK:test3" + a;
	}

	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") TBUser user, Model model) {
		// model.addAttribute("user", user);
		user.setName("aaaaaaaaaa");
		return "demo/user";
	}
	
	@RequestMapping("/getMenuTreeJson")
	@ResponseBody
	public String getMenuTreeJson() {
		List<TreeNode> nodes = new ArrayList<TreeNode>(0);
		TreeNode root = new TreeNode();
		root.setName("网站");
		root.setIsParent(true);
		root.setOpen(true);
		root.setId("0");
		
		TreeNode node1 = new TreeNode();
		node1.setId("1");
		node1.setName("搜索");
		node1.setOpen(true);
		node1.setParentId("0");
		
		TreeNode node11 = new TreeNode();
		node11.setId("11");
		node11.setName("百度");
		node11.setUrl("http://www.baidu.com");
		node11.setParentId("1");
		TreeNode node12 = new TreeNode();
		node12.setId("12");
		node12.setName("谷歌");
		node12.setUrl("http://www.google.cn");
		node12.setParentId("1");
		
		TreeNode node2 = new TreeNode();
		node2.setId("2");
		node2.setName("购物");
		node2.setParentId("0");
		TreeNode node21 = new TreeNode();
		node21.setId("21");
		node21.setName("淘宝");
		node21.setUrl("http://www.taobao.com");
		node21.setParentId("2");
		TreeNode node22 = new TreeNode();
		node22.setId("22");
		node22.setName("京东");
		node22.setUrl("http://www.jd.com");
		node22.setParentId("2");
		
		nodes.add(root);
		nodes.add(node1);
		nodes.add(node11);
		nodes.add(node12);
		nodes.add(node2);
		nodes.add(node21);
		nodes.add(node22);
		String treeJson = TreeUtil.buildTreeByParent(nodes);
		System.out.println(treeJson);
		return treeJson;
	}
}
