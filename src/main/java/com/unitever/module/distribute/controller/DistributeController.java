package com.unitever.module.distribute.controller;

import me.chanjar.weixin.common.exception.WxErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.distribute.model.Distribute;
import com.unitever.module.distribute.service.DistributeService;
import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.service.EmployeeService;
import com.unitever.module.tasktype.model.TaskType;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/distribute")
public class DistributeController extends SpringController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "module/distribute/jsp/distribute-index";
	}

	@RequestMapping(value = "/list")
	public String list(@FormModel("page") Page<Distribute> page, @FormModel("model") Distribute distribute) {
		setAttribute("pageObj", distributeService.getPage(page, distribute));
		setAttribute("model", distribute);
		return "module/distribute/jsp/distribute-list";
	}

	@RequestMapping(value = "/view")
	public String view(@RequestParam(value = "id") String id) {
		setAttribute("model", distributeService.getDistributeById(id));
		return "module/distribute/jsp/distribute-view";
	}

	@RequestMapping(value = "/goDistribute")
	public String goCheck(@RequestParam(value = "employeeId") String employeeId) {
		setAttribute("model", new TaskType());
		setAttribute("employeeId", employeeId);
		return "module/distribute/jsp/distribute-input";
	}


	@RequestMapping(value = "/doDistribute")
	@ResponseBody
	public void doDistribute(@FormModel("model") Distribute distribute) throws WxErrorException {
		distributeService.distribute(distribute);
	}

	@Autowired
	private DistributeService distributeService;
	@Autowired
	private EmployeeService employeeService;
}