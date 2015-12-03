package com.unitever.module.tasktype.model;

/**
 * @author 作者 : 闫枫
 * @version 创建时间：2015-11-19 上午9:49:46 类说明
 */
public class TaskType {

	private String id;
	private String typeKey;
	private String typeName;
	private String state;
	
	/** 任务类型状态：正常 * */
	public final static String TASKTYPE_STATUS_NORMAL = "1";
	/** 任务类型状态：删除 * */
	public final static String TASKTYPE_STATUS_REMOVE = "0";
	
	/** 任务类型状态值 * */
	private String stateVal;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateVal() {
		if(TASKTYPE_STATUS_NORMAL.equals(state)){
			return "正常";
		} else if(TASKTYPE_STATUS_REMOVE.equals(state)){
			return "删除";
		}
		return stateVal;
	}

	public void setStateVal(String stateVal) {
		this.stateVal = stateVal;
	}

	
}
