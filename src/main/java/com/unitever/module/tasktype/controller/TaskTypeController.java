package com.unitever.module.tasktype.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.tasktype.model.TaskType;
import com.unitever.module.tasktype.service.TaskTypeService;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/taskType")
public class TaskTypeController extends SpringController {

	/**
	 * 跳转至任务类型index页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "module/taskType/jsp/taskType-index";
	}

	/**
	 * 跳转至任务类型列表页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(@FormModel("page") Page<TaskType> page, @FormModel("model") TaskType taskType) {
		try {
			taskType.setTypeName(URLDecoder.decode(taskType.getTypeName(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setAttribute("pageObj", taskTypeService.getPage(page, taskType));
		setAttribute("model", taskType);
		return "module/taskType/jsp/taskType-list";
	}

	/**
	 * 跳转至任务类型详情页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(@RequestParam(value = "id") String id) {
		setAttribute("model", taskTypeService.getTaskTypeById(id));
		return "module/taskType/jsp/taskType-view";
	}

	/**
	 * 跳转至任务类型输入页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/input")
	public String input(@RequestParam(value = "id", required = false) String id) {
		String inputKind = getRequest().getParameter("inputKind");
		if ("update".equals(inputKind)) {
			setAttribute("model", taskTypeService.getTaskTypeById(id));
		} else {
			setAttribute("model", new TaskType());
		}
		setAttribute("inputKind", inputKind);
		return "module/taskType/jsp/taskType-input";
	}

	/**
	 * 任务类型更新或保存
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public void saveOrUpdate(@FormModel("model") TaskType taskType) {
		String inputKind = getRequest().getParameter("inputKind");
		if ("update".equals(inputKind)) {
			taskTypeService.update(taskType);
		} else {
			taskTypeService.save(taskType);
		}
	}

	/**
	 * 任务类型删除
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public void delete(@RequestParam("taskTypeId") String taskTypeId) {
		taskTypeService.updateTaskType(taskTypeId);
	}

	@Autowired
	private TaskTypeService taskTypeService;
}