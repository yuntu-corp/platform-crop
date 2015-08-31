package com.unitever.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.unitever.platform.component.attachment.annotation.AttachmentAnnotation;

@AttachmentAnnotation
public class TBUser {

	private String id;
	private String name;
	private String email;
	private Integer age;
	private String deptId;
	private TBDept dept;
	private List<TBAddress> addresses = new ArrayList<TBAddress>();

	public TBUser() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TBDept getDept() {
		return dept;
	}

	public void setDept(TBDept dept) {
		this.dept = dept;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public List<TBAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<TBAddress> addresses) {
		this.addresses = addresses;
	}

	public String getAgeView(Integer age){
		if(age!=null){
			return age+"岁";
		}
		return null;
	}
	
}
