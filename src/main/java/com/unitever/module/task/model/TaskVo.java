package com.unitever.module.task.model; 

import java.util.List;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-20 下午1:58:38 
 * 类说明 
 */
public class TaskVo {
	
	private List<Task> unReceiveTaskList;
	
	private List<Task> unFinishTaskList;
	
	private List<Task> finishTaskList;

	public List<Task> getUnReceiveTaskList() {
		return unReceiveTaskList;
	}

	public void setUnReceiveTaskList(List<Task> unReceiveTaskList) {
		this.unReceiveTaskList = unReceiveTaskList;
	}

	public List<Task> getUnFinishTaskList() {
		return unFinishTaskList;
	}

	public void setUnFinishTaskList(List<Task> unFinishTaskList) {
		this.unFinishTaskList = unFinishTaskList;
	}

	public List<Task> getFinishTaskList() {
		return finishTaskList;
	}

	public void setFinishTaskList(List<Task> finishTaskList) {
		this.finishTaskList = finishTaskList;
	}
	
	

}
