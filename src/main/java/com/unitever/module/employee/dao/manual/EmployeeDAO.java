package com.unitever.module.employee.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.module.employee.model.Employee;
import com.unitever.module.user.model.User;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class EmployeeDAO extends BaseDAO<Employee, String>{
	
	public Employee getEmployeeByUserId(String userId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		List<Employee> list = this.getList("getEmployeeByUserId", params);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public List<Employee> getEmployees(String id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return this.getList("getEmployees", params);
	}
	
	public List<Employee> getAllReceivedEmployees(String employees){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("employees", employees);
		return this.getList("getAllReceivedEmployees", params);
	}
	
	public void minusBitCoin(){
		this.update("minusBitCoin", null);
	}
	
	public List<Employee> getEmployeeList(Employee employee) {
		return this.getList("getEmployeesByEmployees", employee);
	}
}