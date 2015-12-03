package com.unitever.module.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.employee.service.EmployeeService;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends SpringController {
	
	
	/**
	 * 跳转至人员信息index页面
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "module/employee/jsp/employee-index";
	}

	/**
	 * 跳转至人员信息列表页面
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(@FormModel("page") Page<Employee> page, @FormModel("model") Employee employee){
		setAttribute("pageObj", employeeService.getPage(page, employee));
		setAttribute("model", employee);
		return "module/employee/jsp/employee-list";
	}
	/**
	 * 跳转至人员信息详情页面
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/view")
	public String view(@RequestParam(value="id") String id){
		setAttribute("model", employeeService.getEmployeeById(id));
		return "module/employee/jsp/employee-view";
	}
	/**
	 * 跳转至人员信息修改页面
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/doUpdate")
	public String doUpdate(@RequestParam(value="id") String id){
		setAttribute("model", employeeService.getEmployeeById(id));
		return "module/employee/jsp/employee-employeeInput";
	}
	/**
	 * 人员信息修改
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public void update(@FormModel("model") Employee employee){
		employeeService.update(employee);
	}
	
	@Autowired
	private EmployeeService employeeService;
}