package com.unitever.module.wechat.task; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.unitever.module.employee.service.EmployeeService;
import com.unitever.module.task.service.TaskService;

/** 
 * @author 作者 : 邓文杰
 * @version 创建时间：2015-11-17 下午2:08:11 
 * 类说明 :每分钟检查一下所有任务是否过期
 */
@Component
public class MinuteTask {
     /**  
     * 定时计算。每天凌晨 00:00 执行一次
     */    
	@Autowired
	private TaskService taskService;
	
	
	@Scheduled(cron = "0 */1 * * * ?")   
	public void show(){
		taskService.updateTaskStatus();
	}  
	
	

}
