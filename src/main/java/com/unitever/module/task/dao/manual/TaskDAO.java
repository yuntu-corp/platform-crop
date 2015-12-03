package com.unitever.module.task.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.unitever.module.evaluation.model.Evaluation;
import com.unitever.module.task.model.Task;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class TaskDAO extends BaseDAO<Task, String>{
	
	public List<Task> getTaskByPublisherId(String id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.getList("getTaskByPublisherId", params);
	}
	
	public List<Task> getTaskByReceiverId(String id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.getList("getTaskByReceiverId", params);
	}
	
	public List<Task> getTaskByTask(Task task) {
		return this.getList("getTaskByTask", task);
	}
	
	public List<Task> getTaskList(Task task) {
		return this.getList("getTaskByTask", task);
	}
	
	public void deleteTaskByTaskId(String id) {
//		Map<String,Object> params = new HashMap<String, Object>();
//		params.put("id", id);
		this.delete("deleteTaskByTaskId", id);
	}
}