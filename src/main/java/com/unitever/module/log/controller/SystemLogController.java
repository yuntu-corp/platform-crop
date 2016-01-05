package com.unitever.module.log.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unitever.module.log.model.Log;
import com.unitever.module.log.service.LogService;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/systemLog")
public class SystemLogController extends SpringController {
	/**
	 * 跳转至系统日志index页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "module/systemLog/jsp/systemLog-index";
	}

	/**
	 * 跳转至系统日志列表页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(@FormModel("page") Page<Log> page, @FormModel("model") Log log) {
		try {
			if (StringUtils.isNotBlank(log.getOperatorName())) {
				log.setOperatorName(URLDecoder.decode(log.getOperatorName(), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setAttribute("pageObj", logService.getPage(page, log));
		setAttribute("model", log);
		return "module/systemLog/jsp/systemLog-list";
	}

	/**
	 * 跳转至系统日志详情页面
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/view")
	public String view(@RequestParam(value = "id") String id) {
		setAttribute("model", logService.getLogById(id));
		return "module/systemLog/jsp/systemLog-view";
	}

	@Autowired
	private LogService logService;
}
