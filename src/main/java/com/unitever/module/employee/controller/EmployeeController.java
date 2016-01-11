package com.unitever.module.employee.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.employee.service.EmployeeService;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.DoubleUtil;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController extends SpringController {

	/**
	 * 跳转至人员信息index页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "module/employee/jsp/employee-index";
	}

	/**
	 * 跳转至人员信息列表页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(@FormModel("page") Page<Employee> page, @FormModel("model") Employee employee) {
		setAttribute("pageObj", employeeService.getPage(page, employee));
		setAttribute("model", employee);
		return "module/employee/jsp/employee-list";
	}

	/**
	 * 跳转至人员信息详情页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(@RequestParam(value = "id") String id) {
		setAttribute("model", employeeService.getEmployeeById(id));
		return "module/employee/jsp/employee-view";
	}

	/**
	 * 跳转至基础虚拟币修改页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/doUpdateBaseBitcoin")
	public String doUpdateBaseBitcoin(@RequestParam(value = "id") String id) {
		setAttribute("model", employeeService.getEmployeeById(id));
		return "module/employee/jsp/employee-updateBaseBitcoin";
	}

	/**
	 * 基础虚拟币修改
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/updateBaseBitcoin", method = RequestMethod.POST)
	@ResponseBody
	public void updateBaseBitcoin(@FormModel("model") Employee employee) {
		if (employee != null) {
			if (StringUtils.isNotBlank(employee.getBaseBitcoin())) {
				/*
				 * double returnstr =
				 * (double)Math.ceil((Double.parseDouble(employee.getBaseBitcoin
				 * ()) / DateUtil.getDayOfMonth()));
				 * DoubleUtil.div(employee.getBaseBitcoin(),
				 * DateUtil.getDayOfMonth()+"", 2);
				 */
				employee.setCurrentBitcoin(
						DoubleUtil.div(employee.getBaseBitcoin(), DateUtil.getDayOfMonth() + "", 8) + "");
			}
		}
		employeeService.updateBaseBitcoin(employee);
	}

	/**
	 * 跳转至人员信息添加页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping(value = "/goInput")
	public String goInput() throws WxErrorException {
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		setAttribute("model", service.departGet());
		return "module/employee/jsp/employee-employeeInput";
	}

	/**
	 * 人员信息添加
	 * 
	 * @param page
	 * @param order
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws WxErrorException
	 */
	@RequestMapping(value = "/doSave", method = RequestMethod.POST)
	@ResponseBody
	public String saveEmployee(@FormModel("model") Employee employee,
			@RequestParam(value = "departStr") Integer[] departStr) {
		return employeeService.saveEmployee(employee, departStr);
	}

	/**
	 * 跳转至员工信息修改页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping(value = "/doUpdate")
	public String doUpdate(@RequestParam(value = "id") String id, @RequestParam(value = "userId") String userId)
			throws WxErrorException {
		Employee employee = employeeService.getEmployeeById(id);
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		employee.setWxCpUser(service.userGet(userId));
		String departvalue = "";
		if (null != employee.getWxCpUser().getDepartIds()) {
			for (Integer depart : employee.getWxCpUser().getDepartIds()) {
				departvalue += depart + "";
			}
		}
		setAttribute("departList", service.departGet());
		setAttribute("model", employee);
		setAttribute("departvalue", departvalue);
		return "module/employee/jsp/employee-updateEmployee";
	}

	/**
	 * 员工信息修改
	 * 
	 * @param page
	 * @param order
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	@ResponseBody
	public String updateEmployee(@FormModel("model") Employee employee,
			@RequestParam(value = "departStr") Integer[] departStr) {
		return employeeService.updateEmployee(employee, departStr);
	}

	@Autowired
	private EmployeeService employeeService;
}