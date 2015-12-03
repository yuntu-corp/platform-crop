package com.unitever.module.tasktype.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.task.model.Task;
import com.unitever.module.tasktype.dao.manual.TaskTypeDAO;
import com.unitever.module.tasktype.model.TaskType;
import com.unitever.platform.core.dao.Page;

@Service
@Transactional
public class TaskTypeService {
	public List<TaskType> getTaskTypeByTaskType(TaskType taskType) {
		return taskTypeDAO.getTaskTypeByTaskType(taskType);
	}

	public TaskType getTaskTypeById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return taskTypeDAO.get(id);
	}

	public void save(TaskType taskType) {
		if (taskType != null) {
			taskTypeDAO.save(taskType);
		}
	}

	public void update(TaskType taskType) {
		if (taskType != null) {
			taskTypeDAO.update(taskType);
		}
	}

	public void delete(String id) {
		if (StringUtils.isNotBlank(id)) {
			taskTypeDAO.delete(id);
		}
	}

	public List<TaskType> getAllTaskTypes() {
		return taskTypeDAO.getAll();
	}
	
	public List<TaskType> getTaskTypeByTypeKey(String typeKey) {
		return taskTypeDAO.getTaskTypeByTypeKey(typeKey);
	}
	
	/**
	 * 获取任务类型分页对象
	 * @param page
	 * @param order
	 * @return
	 */
	public Page<TaskType> getPage(Page<TaskType> page, TaskType taskType) {
		List<TaskType> taskTypeList = taskTypeDAO.getTaskTypeByTaskType(taskType);
		page.setTotalRecord(taskTypeList.size());
		if (taskTypeList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(taskTypeList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(taskTypeList.subList(page.getStartOfPage(), taskTypeList.size()));
		}
		return page;
	}
	

	@Autowired
	private TaskTypeDAO taskTypeDAO;

}