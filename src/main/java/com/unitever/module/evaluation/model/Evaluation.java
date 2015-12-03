package com.unitever.module.evaluation.model; 

import java.util.Date;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.task.model.Task;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-9 下午1:42:35 
 * 类说明 :评价
 */
public class Evaluation {
	
	private String id;
	private String content;
	private String level;
	private Date createTime;
	private Task  task;
	private Employee publisher;
	private Employee receiver;
	
	/*星级评价：0**/
	public static final String LEVEL_0="0";
	/*星级评价：1**/
	public static final String LEVEL_1="1";
	/*星级评价：2**/
	public static final String LEVEL_2="2";
	/*星级评价：3**/
	public static final String LEVEL_3="3";
	/*星级评价：4**/
	public static final String LEVEL_4="4";
	/*星级评价：5**/
	public static final String LEVEL_5="5";
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Employee getPublisher() {
		return publisher;
	}
	public void setPublisher(Employee publisher) {
		this.publisher = publisher;
	}
	public Employee getReceiver() {
		return receiver;
	}
	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}
	
	
	
	
	

}
