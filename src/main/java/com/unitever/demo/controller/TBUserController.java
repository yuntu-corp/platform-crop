package com.unitever.demo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.unitever.demo.model.TBUser;
import com.unitever.demo.service.TBDeptService;
import com.unitever.demo.service.TBUserService;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.argument.util.MapWapper;
import com.unitever.platform.core.web.controller.SpringController;
import com.unitever.platform.core.web.template.StaticPageData;
import com.unitever.platform.core.web.template.StaticPageUtil;

@Controller
@RequestMapping("/dm/user")
public class TBUserController extends SpringController {

	@Autowired
	private TBUserService userService;
	@Autowired
	private TBDeptService deptService;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		model.addAttribute("list", userService.getAll());
		return "/demo/module/user/user_list";
	}
	

	@RequestMapping("/input")
	public String input(String id) {
		if(StringUtils.isNotBlank(id)){
			setAttribute("model", userService.get(id));
		}else{
			setAttribute("model", new TBUser());
		}
		setAttribute("deptList", deptService.getAll());
		setAttribute("name", "好看");
		return "/demo/module/user/user_input";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") TBUser model) {
		if(StringUtils.isNotBlank(model.getId())){
			userService.update(model);
		}else{
			userService.save(model);
		}
		return "OK" + model.getName() + "," + (model.getDept() != null ? model.getDept().getName() : "dept=null");
	}
	
	@RequestMapping("/delete/{delId}")
	@ResponseBody
	public String delete(@PathVariable String delId) {
		userService.delete(delId);
		return "OK";
	}
	
	@RequestMapping(value = "/other")
    public ModelAndView other(HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("redirect:/dm/user/list");
        ModelAndView mv = new ModelAndView("/demo/module/user/user_other");
        mv.addObject("title", "这是UserController对应的user_other.html");
        return mv;
	}

	
	@RequestMapping("/commitUser1")
	@ResponseBody
	public String commitUser1(@ModelAttribute("user") TBUser user) {
		return "OK:" + user.getName() + "," + (user.getDept() != null ? user.getDept().getName() : "dept=null");
	}
	
	@RequestMapping("/commitUser2")
	@ResponseBody
	public String commitUser2(@FormModel("user") TBUser user) {
		return "OK:" + user.getName() + "," + (user.getDept() != null ? user.getDept().getName() : "dept=null");
	}
	
	@RequestMapping("/commitMap1")
	@ResponseBody
	public String commitMap1(@FormModel("valMap") MapWapper<String, String> valMap) {
		System.out.println(this);
		return "OK:" + valMap.get("name");
	}

	@RequestMapping("/commitMap2")
	@ResponseBody
	public String commitMap2(@FormModel("valMap") MapWapper<String, TBUser> valMap) {
		System.out.println(valMap);
		return "OK:";
	}

	@RequestMapping("/commitList1")
	@ResponseBody
	public String commitList1(@FormModel("nameList") List<String> nameList) {
		return "OK:" + nameList.size();
	}

	@RequestMapping("/commitList2")
	@ResponseBody
	public String commitList2(@FormModel("userList") List<TBUser> userList) {
		return "OK:" + userList.size();
	}

	@RequestMapping(value = "/getUserListJson/{userType}/{userDeptId}")
    @ResponseBody
    public List<TBUser> getUserListJson(@PathVariable String userType, @PathVariable String userDeptId,@RequestParam("userName") String userName) throws Exception {
		System.out.println("userType:"+userType+", userDeptId:"+userDeptId + ", userName:"+userName);
		List<TBUser> users = userService.getAll();
//		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>(0);
//		for(TBUser user : users){
//			Map<String, Object> map = new LinkedHashMap<String, Object>(0);
//			map.put("name", user.getName());
//			map.put("age", user.getAge());
//			map.put("email", user.getEmail());
//			list.add(map);
//		}
        return users;
    }
	
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file) {

        String fileName = file.getOriginalFilename();
        return "OK:"+fileName;
    }
    
	@RequestMapping("/createStaticPage")
	@ResponseBody
	public String createStaticPage() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("users", userService.getAll());
//        StaticPageUtil.createHtml(data, "/demo/module/user/ftl/test.ftl", "/demo/module/user/static/test.html");
        StaticPageUtil.createHtmlWithThread("/demo/module/user/ftl/test.ftl", "/demo/module/user/static/test.html",new StaticPageData() {
			@Override
			public HashMap<String, Object> getData() {
				HashMap<String, Object> data = new HashMap<String, Object>();
		        data.put("users", userService.getAll());
				return data;
			}
		});
		return "OK";
	}

}
