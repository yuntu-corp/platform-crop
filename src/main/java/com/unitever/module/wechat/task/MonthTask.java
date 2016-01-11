package com.unitever.module.wechat.task; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.unitever.module.employee.service.EmployeeService;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-17 下午2:08:11 
 * 类说明 
 */
@Component
public class MonthTask {
     /**  
     * 定时计算。每月第一天00:00 执行一次
     */    
	@Autowired
	private EmployeeService employeeService;
	
	
	@Scheduled(cron = "0 1 0 1 * ?")
	public void show(){
		employeeService.initBitcoin();
	}  
	
	

}
