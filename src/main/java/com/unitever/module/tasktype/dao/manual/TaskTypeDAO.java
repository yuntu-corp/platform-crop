package com.unitever.module.tasktype.dao.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unitever.module.tasktype.model.TaskType;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class TaskTypeDAO extends BaseDAO<TaskType, String> {

	public List<TaskType> getTaskTypeByTaskType(TaskType taskType) {
		return this.getList("getTaskTypeByTaskType", taskType);
	}

	public List<TaskType> getTaskTypeByTypeKey(String typeKey) {
		return this.getList("getTaskTypeByTypeKey", typeKey);
	}
}