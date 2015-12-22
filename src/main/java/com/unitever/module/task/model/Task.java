package com.unitever.module.task.model; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.tasktype.model.TaskType;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-9 上午9:49:46 
 * 类说明 
 */
public class Task {
	
	private String id;
	private String content;
	private String title;
	private String preBitcoin;
	private String finalBitcoin;
	private Employee publisher;
	private Employee receiver;
	private List<Employee> employees;//所有接收到任务消息的员工
	private Date createTime;
	private Date finishDate;
	private String durTime;
	private String status;
	private String employeesString;
	private TaskType taskType;
	private String finishTime;
	private String receiverState;
	private String isNotice;//是否已经提醒用户（过期还未提交审核）
	private String isSuccess;//此字段专为销售任务定制（是否谈单成功）
	private List<String[]> receiverStateList;//接受消息的人的处理情况List，用于前端页面显示，对receiverState做了   切割成List<String[]>格式的   处理
	
	
	/**任务状态：未接受的任务**/
	public static final String TASK_STATE_UNRECEIVE="0";
	/**任务状态：正在进行中的任务**/
	public static final String TASK_STATE_UNFINISH="1";
	/**任务状态：已完成的任务**/
	public static final String TASK_STATE_FINISH="2";
	/**任务状态：已超时的任务**/
	public static final String TASK_STATE_OVER="3";
	
	/**任务状态：待审核任务**/
	public static final String TASK_STATE_UNVERIFY="4";
	/**任务状态：审核通过**/
	public static final String TASK_STATE_SUCCESS="5";
	/**任务状态：审核失败**/
	public static final String TASK_STATE_FAIL="6";
	/**任务状态：未提交审核**/
	public static final String TASK_STATE_UNCOMMIT="7";
	
	
	
	
	
	/**任务类型：技术开发**/
	public static final String TASK_TYPE_1="0";
	/**任务类型：售后**/
	public static final String TASK_TYPE_2="1";
	/**任务类型：销售**/
	public static final String TASK_TYPE_3="2";
	
	
	/**销售任务是否成功：成功**/
	public static final String SUCCESS="1";
	/**任务类型：失败**/
	public static final String FAIL="0";
	
	/**是否提醒：提醒**/
	public static final String NOTICE="1";
	/**是否提醒：未提醒**/
	public static final String UNNOTICE="0";
	
	
	private String statusVal;//任务状态字符串
	private String timeVal;//任务距离完成日期剩余秒数
	private String isSuccessVal;// 是否谈单成功字符串
	

	

	
	
	public String getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(String isNotice) {
		this.isNotice = isNotice;
	}
	public String getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public Task(String id) {
		this.id = id;
	}
	public Task() {
	}
	
	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	
	public String getEmployeesString() {
		return employeesString;
	}
	public void setEmployeesString(String employeesString) {
		this.employeesString = employeesString;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPreBitcoin() {
		return preBitcoin;
	}
	public void setPreBitcoin(String preBitcoin) {
		this.preBitcoin = preBitcoin;
	}
	public String getFinalBitcoin() {
		return finalBitcoin;
	}
	public void setFinalBitcoin(String finalBitcoin) {
		this.finalBitcoin = finalBitcoin;
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
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getDurTime() {
		return durTime;
	}
	public void setDurTime(String durTime) {
		this.durTime = durTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取任务状态字符串
	 * */
	public String getStatusVal() {
		if(TASK_STATE_UNRECEIVE.equals(status)){
			return "未接受";
		} else if(TASK_STATE_UNFINISH.equals(status)){
			return "正在进行";
		} else if(TASK_STATE_FINISH.equals(status)){
			return "已完成";
		}else if(TASK_STATE_OVER.equals(status)){
			return "已超时";
		}else if(TASK_STATE_UNVERIFY.equals(status)){
			return "待审核";
		}else if(TASK_STATE_SUCCESS.equals(status)){
			return "审核通过";
		}else if(TASK_STATE_FAIL.equals(status)){
			return "审核失败";
		}else if(TASK_STATE_UNCOMMIT.equals(status)){
			return "未提交审核";
		}
		return statusVal;
	}
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	
	/**
	 * 任务距离完成日期剩余秒数
	 * */
	public String getTimeVal() {
		timeVal = "0";
		/*Long time = (finishTime.getTime() - (new Date()).getTime()) / 1000;
		if(!(0 >= time)){
			timeVal = time.toString();
		}*/
		return timeVal;
	}
	public void setTimeVal(String timeVal) {
		this.timeVal = timeVal;
	}
	
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	
	/**
	 * 接收到任务的人的详情List
	 * */
	public List<String[]> getReceiverStateList() {
		if(StringUtils.isNotBlank(receiverState)){
			List<String[]> strings = new ArrayList<String[]>();
			String[] receiverStateArray = receiverState.split(",");
			for (String receiver : receiverStateArray){
				strings.add(receiver.split(":"));
			}
			return strings;
		}
		return receiverStateList;
	}
	public void setReceiverStateList(List<String[]> receiverStateList) {
		this.receiverStateList = receiverStateList;
	}
	
	public String getIsSuccessVal() {
		isSuccessVal = "";
		if (StringUtils.isNotBlank(isSuccess)) {
			if (SUCCESS.equals(isSuccess)) {
				isSuccessVal = "谈单成功";
			} else if (FAIL.equals(isSuccess)) {
				isSuccessVal = "谈单失败";
			}
		}
		return isSuccessVal;
	}

	public void setIsSuccessVal(String isSuccessVal) {
		this.isSuccessVal = isSuccessVal;
	}


}
