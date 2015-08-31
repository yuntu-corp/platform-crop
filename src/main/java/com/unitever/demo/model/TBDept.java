package com.unitever.demo.model;

import java.util.ArrayList;
import java.util.List;

public class TBDept {

	private String id;

	private String name;

	private List<TBUser> users = new ArrayList<TBUser>();

	public TBDept() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TBUser> getUsers() {
		return users;
	}

	public void setUsers(List<TBUser> users) {
		this.users = users;
	}
	
}
