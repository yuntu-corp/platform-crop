package com.unitever.module.dicker.service;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.unitever.module.dicker.dao.manual.DickerDAO;
import com.unitever.module.dicker.model.Dicker;
import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.task.dao.manual.TaskDAO;
import com.unitever.module.task.model.Task;
import com.unitever.module.task.service.TaskService;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.DoubleUtil;

@Service
@Transactional
public class DickerService {
	
	
	public Dicker getDickerById(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		return dickerDAO.get(id);
	}
	
	
	public void save(Dicker dicker){
		if(dicker!=null){
			dickerDAO.save(dicker);
		}
	}
	
	
	public void update(Dicker dicker){
		if(dicker!=null){
			dickerDAO.update(dicker);
		}
	}
	
	
	public List<Dicker> getDickerByPublisherIdAndStatus(String publisherId,String status){
		if(StringUtils.isBlank(publisherId)||StringUtils.isBlank(status)){
			return null;
		}
		Dicker dicker=new Dicker();
		Employee publisher=new Employee();
		publisher.setId(publisherId);
		dicker.setPublisher(publisher);
		dicker.setStatus(status);
		return dickerDAO.getDickerByDicker(dicker);
	}
	
	public List<Dicker> getDickerByReceiverIdAndStatus(String receiverId,String status){
		if(StringUtils.isBlank(receiverId)||StringUtils.isBlank(status)){
			return null;
		}
		Dicker dicker=new Dicker();
		Employee receiver=new Employee();
		receiver.setId(receiverId);
		dicker.setReceiver(receiver);
		dicker.setStatus(status);
		return dickerDAO.getDickerByDicker(dicker);
	}
	
	public String doDicker(String taskId,String publisherId,Dicker dicker,String receiverId) throws WxErrorException{
		Task task=taskDAO.get(taskId);
		if(Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())){
			dicker.setCreateTime(new Date());
			dicker.setPublisher(new Employee(publisherId));
			dicker.setReceiver(new Employee(receiverId));
			dicker.setStatus(Dicker.DICKER_STATE_UNTREATED);
			dicker.setTask(new Task(taskId));
			dickerDAO.save(dicker);
			/* 
			 * 设置接收消息人状态
			 */
			Employee publisherEmployee = employeeDAO.get(publisherId);
			if(StringUtils.isBlank(task.getReceiverState())){
				task.setReceiverState(publisherEmployee.getName() + ":还价:" + publisherEmployee.getUserId());
			} else {
				task.setReceiverState(task.getReceiverState() + "," + publisherEmployee.getName() + ":还价:" + publisherEmployee.getUserId());
			}
			taskDAO.update(task);
			// 通知发布还价人
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
			WxCpMessage me = new WxCpMessage();
			me.setMsgType(WxConsts.XML_MSG_TEXT);
			me.setAgentId("15");
			Employee employeeToHref = employeeDAO.get(receiverId);
			me.setToUser(employeeToHref.getUserId());
			me.setContent(employeeDAO.get(publisherId).getName() + "已经对您发布的项目：" + task.getTitle()
					+ "进行了还价请求！请去个人中心查看！ \n <a href=\"http://" + employeeToHref.getUser().getDomainName()
					+ "/platform/weChat/msgList?employeeId=" + task.getPublisher().getId()+ "&type=dicker" + "\">查看详情>>></a>");
			service.messageSend(me);
			return "success";
		}else{
			return  "很抱歉，"+task.getReceiver().getName()+"已经接受了任务："+task.getTitle();
		}
	}
	
	public void refuseDicker(String dickerId) throws WxErrorException{
		Dicker dicker=dickerDAO.get(dickerId);
		dicker.setStatus(Dicker.DICKER_STATE_REFUSED);
		dicker.setEndTime(new Date());
		dickerDAO.update(dicker);
		/* 
		 * 设置接收消息人状态
		 */
		Task task = taskDAO.get(dicker.getTask().getId());
		Employee employee = employeeDAO.get(dicker.getPublisher().getId());
		task.setReceiverState(task.getReceiverState().replaceAll(employee.getName() + ":还价:" + employee.getUserId(),
				employee.getName() + ":被拒绝还价:" + employee.getUserId()));
		taskDAO.update(task);
		//通知发布还价人
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		WxCpMessage me=new WxCpMessage();
		me.setMsgType(WxConsts.XML_MSG_TEXT);
		me.setAgentId("15");
		me.setToUser(dicker.getPublisher().getUserId());
		me.setContent("抱歉！"+task.getPublisher().getName()+"已经拒绝了您对项目"+task.getTitle()+"的还价请求！"
				+ "\n <a href=\"http://" + employee.getUser().getDomainName()
				+ "/platform/weChat/taskView?id="+task.getId()+"&employeeId="+employee.getId()+"\">查看详情>>></a>");
		service.messageSend(me);
	}
	
	public String acceptDicker(String dickerId) throws WxErrorException{
		Dicker dicker=dickerDAO.get(dickerId);
		dicker.setStatus(Dicker.DICKER_STATE_ACCEPT);
		dicker.setEndTime(new Date());
		Task task=dicker.getTask();
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
				.getSession("service");
		//通知发布还价人
		WxCpMessage me=new WxCpMessage();
		me.setMsgType(WxConsts.XML_MSG_TEXT);
		me.setAgentId("15");
		me.setToUser(dicker.getPublisher().getUserId());
		
		
		//判断任务状态是否已经被接受
		if(Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())){
			task.setStatus(Task.TASK_STATE_UNFINISH);
			task.setFinalBitcoin(dicker.getBitcoin());
			task.setReceiver(dicker.getPublisher());
			Employee publisher=task.getPublisher();
			Employee receiver=task.getReceiver();
			
			if(DoubleUtil.sub(publisher.getBitcoinSurplus(), task.getFinalBitcoin(), 2)<0){
				return "error";
			}
			
			//DoubleUtil.add(receiver.getBitcoinIncome(),task.getFinalBitcoin(), 2)
			publisher.setBitcoinConsume(DoubleUtil.add(publisher.getBitcoinConsume(), task.getFinalBitcoin(), 2)+"");
			publisher.setBitcoinSurplus(DoubleUtil.sub(publisher.getBitcoinSurplus(),task.getFinalBitcoin(), 2)+"");
			
			receiver.setBitcoinSurplus(DoubleUtil.add(receiver.getBitcoinSurplus(),task.getFinalBitcoin(), 2)+"");
			receiver.setBitcoinIncome(DoubleUtil.add(receiver.getBitcoinIncome(),task.getFinalBitcoin(), 2)+"");
			receiver.setTaskCount((Integer.parseInt(receiver.getTaskCount())+1)+"");
			employeeDAO.update(publisher);
			employeeDAO.update(receiver);
			/* 
			 * 设置接收消息人状态
			 */
			Employee employee = employeeDAO.get(dicker.getPublisher().getId());
			task.setReceiverState(task.getReceiverState().replaceAll(employee.getName() + ":还价:" + employee.getUserId(),
					employee.getName() +  ":还价通过:" + employee.getUserId()));
			taskDAO.update(task);
			dickerDAO.update(dicker);
			me.setContent(task.getPublisher().getName()+"已经接受了您对项目"+task.getTitle()+"的还价请求！"
					+ "\n <a href=\"http://" + employee.getUser().getDomainName()
					+ "/platform/weChat/taskView?id="+task.getId()+"&employeeId="+employee.getId()+"\">查看详情>>></a>");
			service.messageSend(me);
			
			//通知除发布人和接收人之外的所有人此任务已经被接受
			for (String employeeId_ : task.getEmployeesString().split(",")) {
				if(!task.getPublisher().getUserId().equals(employeeId_)&&!task.getReceiver().getUserId().equals(employeeId_)){
					System.out.println(employeeId_);
					me.setContent(task.getPublisher().getName()
							+ "发布的任务：'"+task.getTitle()+"'，已经被"+task.getReceiver().getName()+"接受"
							+"\r\n 任务内容："+task.getContent()+";"
							+"\r\n 任务工期："+task.getDurTime()+";"
							+"\r\n 结束时间："+DateUtil.getDateMinString(task.getFinishDate())+";"
							+"\r\n 虚拟币："+task.getFinalBitcoin()+";"
							);
					System.out.println(me.getContent());
					me.setToUser(employeeId_);
					service.messageSend(me);
				}
			}
			return "success";
		}else{
			me.setContent("抱歉！您所还价的项目:"+task.getTitle()+"已经被"+task.getReceiver().getName()+"接受！"
					+ "\n <a href=\"http://" + dicker.getPublisher().getUser().getDomainName()
					+ "/platform/weChat/taskView?id="+task.getId()+"&employeeId="+dicker.getPublisher().getId()+"\">查看详情>>></a>");
			service.messageSend(me);
			return "抱歉！您所还价的项目:"+task.getTitle()+"已经被"+task.getReceiver().getName()+"接受！";
		}
		
	}
	
	@Autowired
	private DickerDAO dickerDAO;
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
}