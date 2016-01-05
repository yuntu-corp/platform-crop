package com.unitever.module.log.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.log.dao.manual.LogDAO;
import com.unitever.module.log.model.Log;
import com.unitever.platform.core.dao.Page;

@Service
@Transactional
public class LogService {

	/**
	 * <p>
	 * 根据ID获取LOG信息
	 * </p>
	 * 
	 * @param id：log的id
	 * @return Log
	 */
	public Log getLogById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return LogDAO.get(id);
	}

	/**
	 * <p>
	 * 保存日志记录
	 * </p>
	 * 
	 * @param Log：log对象
	 */
	public void save(Log log) {
		if (log != null) {
			LogDAO.save(log);
		}
	}

	/**
	 * <p>
	 * 获取log分页对象
	 * </p>
	 * 
	 * @param page
	 * @param Log
	 * @return
	 */
	public Page<Log> getPage(Page<Log> page, Log log) {
		List<Log> LogList = LogDAO.getLogByLog(log);
		page.setTotalRecord(LogList.size());
		if (LogList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(LogList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(LogList.subList(page.getStartOfPage(), LogList.size()));
		}
		return page;
	}

	@Autowired
	private LogDAO LogDAO;

}