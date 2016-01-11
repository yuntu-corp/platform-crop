package com.unitever.module.distribute.model; 

import java.util.Date;

import com.unitever.module.employee.model.Employee;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2016-1-7 上午10:38:37 
 * 类说明 
 */
public class Distribute {
	
	private String id;
	private String bitcoin;
	private Employee employee;
	private Date date;
	private String reason;
	private String employeeId;
	
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBitcoin() {
		return bitcoin;
	}
	public void setBitcoin(String bitcoin) {
		this.bitcoin = bitcoin;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	

}
