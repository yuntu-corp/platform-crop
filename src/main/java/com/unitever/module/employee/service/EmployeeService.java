package com.unitever.module.employee.service;
import java.util.Calendar;
import java.util.List;

import me.chanjar.weixin.cp.bean.WxCpUser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.user.dao.manual.UserDAO;
import com.unitever.module.wechat.service.WeChatService;
import com.unitever.platform.core.dao.Page;

@Service
@Transactional
public class EmployeeService {
	
	
	public Employee getEmployeeById(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		return employeeDAO.get(id);
	}
	
	
	public void save(Employee employee){
		if(employee!=null){
			employeeDAO.save(employee);
		}
	}
	
	
	public void update(Employee employee){
		if(employee!=null){
			employeeDAO.update(employee);
		}
	}
	
	
	public void updateCurrentBitcoin(Employee employee){
		if(employee!=null){
			if(StringUtils.isNotBlank(employee.getId())&&StringUtils.isNotBlank(employee.getCurrentBitcoin())){
				employeeDAO.update(employee);	
			}
		}
	}
	
	
	public Employee getEmployeeByUserId(String userId){
		if(StringUtils.isBlank(userId)){
			return null;
		}
		return employeeDAO.getEmployeeByUserId(userId);
	}
	
	public List<Employee> getEmployees(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		return employeeDAO.getEmployees(id);
	}
	
	public List<Employee> getAllEmployees(){
		//检查是否有的用户没有头像
		List<Employee> list=employeeDAO.getAll();
		for(Employee employee:list){
			if(StringUtils.isBlank(employee.getHeadimgurl())){
				employee=weChatService.reGetHeadImgUrl(employee);
			}
		}
		return list;
	}
	
	public void initEmployee(WxCpUser user){
		if(employeeDAO.getEmployeeByUserId(user.getUserId())==null){
			Employee employee=new Employee();
			employee.setBitcoinConsume("0");
			employee.setBitcoinIncome("0");
			employee.setBitcoinSurplus("0");
			employee.setBitcoinSurplus("4000");
			employee.setHeadimgurl(user.getAvatar());
			employee.setName(user.getName());
			employee.setStatus(Employee.EMPLOYE_STATUS_NORMAL);
			employee.setUser(userDAO.getUserWithCorpId("wxef727939c7cd2d6c"));
			employee.setUserId(user.getUserId());
			employee.setWxCpUser(user);
			employee.setTaskCount(0+"");
			employee.setPublishTaskCount(0+"");
			employee.setEvaluationLevel("0");
			//Employee emp=initBaseBitCoin(employee);
			employee.setCurrentBitcoin(0+"");
			employeeDAO.save(employee);
		}
	}
	
	
	public Employee initBaseBitCoin(Employee employee){
		//技术部
		if("邓文杰".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}else if("闫枫".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("张娟".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("景晓燕".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		//运营部
		else if("王慧敏".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("李晓玲".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		//行政财务部
		else if("蒋楠".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("杨建梅".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		//公司高层
		else if("郝佩琳".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("李文涛".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		else if("王玲玲".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		//市场部
		else if("孟雪荣".equals(employee.getName())){
			employee.setBaseBitcoin("4000");
		}
		return employee;
	}
	
	public void minusBitCoin(){
		employeeDAO.minusBitCoin();
	}
	
	/**
	 * 取得当月天数
	 * */
	public static int getCurrentMonthLastDay()
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 得到指定月的天数
	 * */
	public static int getMonthLastDay(int year, int month)
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	
	public void updateTaskCount(String id){
		employeeDAO.updateTaskCount(id);
	}
	
	/**
	 * 获取人员分页对象
	 * @param page
	 * @param order
	 * @return
	 */
	public Page<Employee> getPage(Page<Employee> page, Employee employee) {
		List<Employee> employeeList = employeeDAO.getEmployeeList(employee);
		page.setTotalRecord(employeeList.size());
		if (employeeList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(employeeList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(employeeList.subList(page.getStartOfPage(), employeeList.size()));
		}
		return page;
	}
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private WeChatService weChatService;
}