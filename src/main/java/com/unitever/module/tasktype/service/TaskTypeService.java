package com.unitever.module.tasktype.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.log.model.Log;
import com.unitever.module.tasktype.dao.manual.TaskTypeDAO;
import com.unitever.module.tasktype.model.TaskType;
import com.unitever.module.user.model.User;
import com.unitever.module.wechat.util.LogUtil;
import com.unitever.platform.core.common.helper.UserHelper;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.util.UUID;

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
		User user = UserHelper.getCurrUser();
		if (taskType != null) {
			taskType.setState("1");
			taskType.setId(UUID.getUUID());
			taskTypeDAO.save(taskType);
			LogUtil.saveLog("添加新的任务类型：" + taskType.getTypeName(), user.getUsername(), Log.ADMIN_TYPE_YES, user.getId(),
					Log.LOGTYPE_TASKTYPE, taskType.getId(), Log.OPERATE_TASKTYPE_SAVE);
		}
	}

	public void update(TaskType taskType) {
		User user = UserHelper.getCurrUser();
		if (taskType != null) {
			taskTypeDAO.update(taskType);
			LogUtil.saveLog("更新任务类型：" + taskType.getTypeName() + " 的内容。", user.getUsername(), Log.ADMIN_TYPE_YES,
					user.getId(), Log.LOGTYPE_TASKTYPE, taskType.getId(), Log.OPERATE_TASKTYPE_UPDATE);
		}
	}

	public void updateTaskType(String taskTypeId) {
		User user = UserHelper.getCurrUser();
		if (StringUtils.isNotBlank(taskTypeId)) {
			TaskType taskType = taskTypeDAO.get(taskTypeId);
			taskType.setState("0");
			taskTypeDAO.update(taskType);

			LogUtil.saveLog("删除任务类型：" + taskType.getTypeName(), user.getUsername(), Log.ADMIN_TYPE_YES, user.getId(),
					Log.LOGTYPE_TASKTYPE, taskType.getId(), Log.OPERATE_TASKTYPE_DELETE);
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
	 * 
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