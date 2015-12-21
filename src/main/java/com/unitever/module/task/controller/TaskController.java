package com.unitever.module.task.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.task.model.Task;
import com.unitever.module.task.service.TaskService;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;

@Controller
@RequestMapping(value = "/task")
public class TaskController extends SpringController {

	/**
	 * 跳转至任务index页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "module/task/jsp/task-index";
	}

	/**
	 * 跳转至任务列表页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(@FormModel("page") Page<Task> page, @FormModel("model") Task task) {
		try {
			task.setTitle(URLDecoder.decode(task.getTitle(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setAttribute("pageObj", taskService.getPage(page, task));
		setAttribute("model", task);
		return "module/task/jsp/task-list";
	}

	/**
	 * 跳转至任务详情页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(@RequestParam(value = "id") String id) {
		setAttribute("model", taskService.getTaskById(id));
		return "module/task/jsp/task-view";
	}

	/**
	 * 删除任务
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete(@RequestParam(value = "taskId") String taskId) {
		taskService.deleteTask(taskId);
	}

	/**
	 * 跳转至任务审核index页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/taskCheckIndex", method = RequestMethod.GET)
	public String taskCheckIndex() {
		return "module/task/jsp/taskCheck-index";
	}

	/**
	 * 跳转至任务审核列表页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/taskChecklist")
	public String taskChecklist(@FormModel("page") Page<Task> page, @FormModel("model") Task task) {
		try {
			task.setTitle(URLDecoder.decode(task.getTitle(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		task.setStatus("4");// 获取所有待审核任务
		setAttribute("pageObj", taskService.getPage(page, task));
		setAttribute("model", task);
		return "module/task/jsp/taskCheck-list";
	}

	/**
	 * 跳转至任务审核页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/goCheck")
	public String goCheck(@RequestParam(value = "id") String id) {
		setAttribute("model", taskService.getTaskById(id));
		return "module/task/jsp/taskCheck-goCheck";
	}

	/**
	 * 任务审核
	 * 
	 * @param page
	 * @param order
	 * @return
	 * @throws WxErrorException
	 */
	@RequestMapping(value = "/doCheck")
	@ResponseBody
	public void doCheck(@FormModel("model") Task task,
			@RequestParam(value = "refuseReason", required = false) String refuseReason) throws WxErrorException {
		taskService.update(task);
		// 满足一定条件的情况下 进行 任务返利
		Employee employee = employeeDAO.get(task.getPublisher().getId());
		if (Task.FAIL.equals(task.getIsSuccess())) {
			if (Task.TASK_STATE_SUCCESS.equals(task.getStatus())) {
				employee.setBitcoinSurplus((Double.parseDouble(employee.getBitcoinSurplus())
						+ Double.parseDouble(task.getFinalBitcoin()) / 2) + "");
				employeeDAO.update(employee);
			}
		}
		// 给提交审核资料人员发送审核结果
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		WxCpMessage me = new WxCpMessage();
		me.setMsgType(WxConsts.XML_MSG_TEXT);
		me.setAgentId("15");
		me.setToUser(employee.getUserId());
		String massageStr = "管理员已经对您提交的资料审核完毕\r\n任务为：" + task.getTitle() + "\r\n审核结果为：" + task.getStatusVal();
		if (StringUtils.isNotBlank(refuseReason) && task.getStatus().equals("6")) {
			massageStr += "\r\n拒绝理由为：" + refuseReason;
		}
		massageStr += "\r\n <a href=\"http://" + employee.getUser().getDomainName() + "/platform/weChat/taskView?id="
				+ task.getId() + "&employeeId=" + employee.getId() + "\">查看详情>>></a>";
		me.setContent(massageStr);
		service.messageSend(me);
	}

	@Autowired
	private TaskService taskService;
	@Autowired
	private EmployeeDAO employeeDAO;
}