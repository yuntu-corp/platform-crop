package com.unitever.module.dicker.model; 
import java.util.Date;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.task.model.Task;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-9 上午11:14:56 
 * 类说明 ：还价消息
 */
public class Dicker {
	
	private String id;
	private Task task;
	private String bitcoin;
	private Employee receiver;
	private Employee publisher;
	private String status;
	private Date createTime;
	private Date endTime;
	
	/*还价消息状态：拒绝**/
	public static final String DICKER_STATE_REFUSED="1";
	/*还价消息状态：接受**/
	public static final String DICKER_STATE_ACCEPT="2";
	/*还价消息状态：未处理**/
	public static final String DICKER_STATE_UNTREATED="0";
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getBitcoin() {
		return bitcoin;
	}
	public void setBitcoin(String bitcoin) {
		this.bitcoin = bitcoin;
	}
	public Employee getReceiver() {
		return receiver;
	}
	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}
	public Employee getPublisher() {
		return publisher;
	}
	public void setPublisher(Employee publisher) {
		this.publisher = publisher;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

	
	
	

}
