package com.unitever.module.task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpMessage;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.dicker.dao.manual.DickerDAO;
import com.unitever.module.dicker.model.Dicker;
import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.evaluation.dao.manual.EvaluationDAO;
import com.unitever.module.log.model.Log;
import com.unitever.module.task.dao.manual.TaskDAO;
import com.unitever.module.task.model.Task;
import com.unitever.module.task.model.TaskVo;
import com.unitever.module.user.model.User;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.module.wechat.util.LogUtil;
import com.unitever.platform.core.common.helper.UserHelper;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.DoubleUtil;
import com.unitever.platform.util.UUID;

@Service
@Transactional
public class TaskService {

	public Task getTaskById(String id) {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		return taskDAO.get(id);
	}

	public void save(Task task) {
		if (task != null) {
			taskDAO.save(task);
		}
	}

	public void update(Task task) {
		if (task != null) {
			taskDAO.update(task);
		}
	}

	public List<Task> getAllTasks() {
		return taskDAO.getAll();
	}

	public TaskVo getTaskVo() {
		TaskVo vo = new TaskVo();
		List<Task> unReceiveTaskList = new ArrayList<Task>();
		List<Task> unFinishTaskList = new ArrayList<Task>();
		List<Task> finishTaskList = new ArrayList<Task>();
		for (Task task : taskDAO.getAll()) {
			if (Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())) {
				unReceiveTaskList.add(task);
			}
			if (Task.TASK_STATE_UNFINISH.equals(task.getStatus())) {
				unFinishTaskList.add(task);
			}
			if (Task.TASK_STATE_FINISH.equals(task.getStatus())) {
				finishTaskList.add(task);
			}
			if (Task.TASK_STATE_UNVERIFY.equals(task.getStatus())) {
				finishTaskList.add(task);
			}
			if (Task.TASK_STATE_SUCCESS.equals(task.getStatus())) {
				finishTaskList.add(task);
			}
			if (Task.TASK_STATE_FAIL.equals(task.getStatus())) {
				finishTaskList.add(task);
			}
			if (Task.TASK_STATE_UNCOMMIT.equals(task.getStatus())) {
				finishTaskList.add(task);
			}
		}
		vo.setUnReceiveTaskList(unReceiveTaskList);
		vo.setUnFinishTaskList(unFinishTaskList);
		vo.setFinishTaskList(finishTaskList);
		return vo;
	}

	public List<Task> getTaskByPublisherId(String publisherId) {
		if (StringUtils.isBlank(publisherId)) {
			return null;
		}
		return taskDAO.getTaskByPublisherId(publisherId);
	}

	public List<Task> getTaskByReceiverId(String receiveId) {
		if (StringUtils.isBlank(receiveId)) {
			return null;
		}
		return taskDAO.getTaskByReceiverId(receiveId);
	}

	public String publishTask(Task task, String employeeId) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date date = df.parse(task.getFinishTime());
		task.setId(UUID.getUUID());
		task.getFinishDate().setHours(date.getHours());
		task.getFinishDate().setMinutes(date.getMinutes());
		task.setCreateTime(new Date());
		task.setIsPublisherEvaluate(Task.UNEVALUATE);
		task.setIsReceiverEvaluate(Task.UNEVALUATE);
		if (null == task.getFinishTime()) {
			return "请选择任务结束时间";
		}
		DateTime start = new DateTime(task.getCreateTime().getTime());
		DateTime end = new DateTime(task.getFinishDate().getTime());
		Period period = new Period(start, end);
		String result = null;
		if (period.getDays() == 0) {
			if (period.getHours() == 0) {
				result = period.getMinutes() + "分钟";
			} else {
				result = period.getHours() + "小时" + period.getMinutes() + "分钟";
			}
		} else {
			result = period.getDays() + "天" + period.getHours() + "小时" + period.getMinutes() + "分钟";
		}

		// Long
		// intervalDays=DateUtil.getIntervalDays(DateUtil.getDateString(task.getCreateTime()),
		// DateUtil.getDateString(task.getFinishDate()));
		task.setDurTime(result);
		task.setFinalBitcoin("0");
		Employee employee = employeeDAO.get(employeeId);
		// 更新员工发布任务数
		employee.setPublishTaskCount((Integer.parseInt(employee.getPublishTaskCount()) + 1) + "");
		employeeDAO.update(employee);
		task.setPublisher(employee);
		task.setStatus(Task.TASK_STATE_UNRECEIVE);
		Employee publisher = task.getPublisher();
		if (StringUtils.isBlank(task.getContent())) {
			return "内容不能为空";
		}
		if (StringUtils.isBlank(task.getEmployeesString())) {
			return "您还没有选择要发布给谁";
		}
		if (StringUtils.isBlank(task.getPreBitcoin())) {
			return "请填写项目所需虚拟币";
		}
		if (StringUtils.isNotBlank(task.getPreBitcoin())) {
			if (!task.getPreBitcoin().matches("[0-9]+")) {
				return "虚拟币必须为数字";
			}
		}
		if (task.getTaskType() == null || StringUtils.isBlank(task.getTaskType().getId())) {
			return "请选择项目类型";
		}
		if (DoubleUtil.sub(publisher.getBitcoinSurplus(), task.getPreBitcoin(), 8) < 0) {
			return "您的余额不足！";
		}
		taskDAO.save(task);
		LogUtil.saveLog(employee.getName()+"发布了任务："+task.getTitle(), employee.getName(), Log.ADMIN_TYPE_NO, employee.getId(), Log.LOGTYPE_EMPLOYEE, task.getId(), Log.OPERATE_TASK_SAVE);

		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		WxCpMessage me = new WxCpMessage();
		me.setMsgType(WxConsts.XML_MSG_TEXT);
		me.setAgentId("15");
		for (String employeeId_ : task.getEmployeesString().split(",")) {
			Employee employeeToHref = employeeDAO.getEmployeeByUserId(employeeId_);
			System.out.println(employeeId_);
			me.setContent(task.getPublisher().getName() + "给您发布了任务，请去个人中心查看！\n <a href=\"http://" + employeeToHref.getUser().getDomainName()
					+ "/platform/weChat/msgList?employeeId=" + employeeToHref.getId() + "&type=task " + "\">查看详情>>></a>");
			System.out.println(me.getContent());
			me.setToUser(employeeId_);
			try {
				service.messageSend(me);
			} catch (WxErrorException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	public List<Task> getUnReceivedTaskByReceiverId(String receiveId) {
		List<Dicker> dickerList = dickerDAO.getAll();
		Employee employee = employeeDAO.get(receiveId);
		Task task = new Task();
		task.setStatus(Task.TASK_STATE_UNRECEIVE);
		List<Task> taskList = taskDAO.getTaskByTask(task);
		List<Task> tasks = new ArrayList<Task>();
		for (Task task_ : taskList) {
			if (StringUtils.isNotBlank(task_.getEmployeesString())) {
				if (task_.getEmployeesString().contains(employee.getUserId())) {
					// 查看dicker表是否已经讨价还价
					if (dickerList != null && !dickerList.isEmpty()) {
						boolean isDicker = false;
						for (Dicker dicker : dickerList) {
							if (dicker.getTask().getId().equals(task_.getId())) {
								if (dicker.getPublisher().getId().equals(receiveId)) {
									isDicker = true;
								}
							}
							/*
							 * if
							 * (!(dicker.getTask().getId().equals(task_.getId())
							 * &&
							 * dicker.getPublisher().getId().equals(receiveId)))
							 * { tasks.add(task_); break; }
							 */
						}
						if (!isDicker) {
							tasks.add(task_);
						}
					} else {
						tasks.add(task_);
					}

				}
			}
		}
		return tasks;
	}

	public void refuseTask(String taskId, String employeeId, String refuseReason) throws WxErrorException {
		Employee employee = employeeDAO.get(employeeId);
		String userId = employee.getUserId();
		Task task = taskDAO.get(taskId);
		if (Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())) {
			String employeesString = task.getEmployeesString();
			if (StringUtils.isNotBlank(employeesString)) {
				if (userId.equals(employeesString)) {
					employeesString = "";
				} else {
					if (employeesString.contains(userId)) {
						if (employeesString.startsWith(userId)) {
							employeesString = employeesString.replace(userId + ",", "");
						} else {
							employeesString = employeesString.replace("," + userId, "");
						}
					}
				}

			}
			/*
			 * 设置接收消息人拒绝任务状态
			 */
			if (StringUtils.isBlank(task.getReceiverState())) {
				task.setReceiverState(employee.getName() + ":拒绝任务:" + employee.getUserId());
			} else {
				task.setReceiverState(task.getReceiverState() + "," + employee.getName() + ":拒绝任务:" + employee.getUserId());
			}
			task.setEmployeesString(employeesString);
			taskDAO.update(task);
			
			LogUtil.saveLog(employee.getName()+"拒绝了任务："+task.getTitle()+"，理由是："+refuseReason, employee.getName(), Log.ADMIN_TYPE_NO, employee.getId(), Log.LOGTYPE_TASK, task.getId(), Log.OPERATE_TASK_REFUSE);
			
			
			// 通知发布还价人
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
			WxCpMessage me = new WxCpMessage();
			me.setMsgType(WxConsts.XML_MSG_TEXT);
			me.setAgentId("15");
			me.setToUser(task.getPublisher().getUserId());
			Employee employeeToHref = employeeDAO.get(employeeId);
			me.setContent(employeeToHref.getName() + "已经拒绝了您发布的任务：" + task.getTitle() + "\n拒绝理由为：" + refuseReason + "\n <a href=\"http://"
					+ employeeToHref.getUser().getDomainName() + "/platform/weChat/taskView?id=" + task.getId() + "&employeeId=" + task.getPublisher().getId() + "\">查看详情>>></a>");
			service.messageSend(me);
		}
	}

	public String acceptTask(String taskId, String employeeId) throws WxErrorException {
		Task task = taskDAO.get(taskId);
		if (Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())) {
			task.setFinalBitcoin(task.getPreBitcoin());
			task.setStatus(Task.TASK_STATE_UNFINISH);
			Employee publisher = task.getPublisher();
			Employee receiver = employeeDAO.get(employeeId);
			task.setReceiver(receiver);
			if (DoubleUtil.sub(publisher.getBitcoinSurplus(), task.getFinalBitcoin(), 8) < 0) {
				return "error！";
			}
			
			publisher.setBitcoinConsume(DoubleUtil.add(publisher.getBitcoinConsume(), task.getPreBitcoin(), 8)+"");
			publisher.setBitcoinSurplus(DoubleUtil.sub(publisher.getBitcoinSurplus(), task.getPreBitcoin(),8)+ "");
			// publisher.setPublishTaskCount((Integer.parseInt(publisher.getPublishTaskCount())
			// + 1)+ "");
			
			receiver.setBitcoinSurplus(DoubleUtil.add(receiver.getBitcoinSurplus(), task.getPreBitcoin(), 8) + "");
			receiver.setBitcoinIncome(DoubleUtil.add(receiver.getBitcoinIncome(),task.getPreBitcoin(), 8) + "");
			receiver.setTaskCount((Integer.parseInt(receiver.getTaskCount()) + 1) + "");
			employeeDAO.update(publisher);
			employeeDAO.update(receiver);
			/*
			 * 设置接收消息人状态
			 */
			if (StringUtils.isBlank(task.getReceiverState())) {
				task.setReceiverState(receiver.getName() + ":接受任务:" + receiver.getUserId());
			} else {
				task.setReceiverState(task.getReceiverState() + "," + receiver.getName() + ":接受任务:" + receiver.getUserId());
			}
			taskDAO.update(task);
			
			LogUtil.saveLog(receiver.getName()+"接受了任务："+task.getTitle(),receiver.getName(), Log.ADMIN_TYPE_NO, receiver.getId(), Log.LOGTYPE_TASK, task.getId(), Log.OPERATE_TASK_ACCEPT);
			
			
			// 通知发布还价人
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
			WxCpMessage me = new WxCpMessage();
			me.setMsgType(WxConsts.XML_MSG_TEXT);
			me.setAgentId("15");
			me.setToUser(task.getPublisher().getUserId());
			Employee employeeToHref = employeeDAO.get(employeeId);
			me.setContent(employeeToHref.getName() + "已经接受了您发布的任务：" + task.getTitle() + "\n <a href=\"http://" + employeeToHref.getUser().getDomainName()
					+ "/platform/weChat/taskView?id=" + task.getId() + "&employeeId=" + task.getPublisher().getId() + "\">查看详情>>></a>");
			service.messageSend(me);

			// 通知除发布人和接收人之外的所有人此任务已经被接受
			for (String employeeId_ : task.getEmployeesString().split(",")) {
				if (!task.getPublisher().getUserId().equals(employeeId_) && !task.getReceiver().getUserId().equals(employeeId_)) {
					System.out.println(employeeId_);
					me.setContent(task.getPublisher().getName() + "发布的任务已经被" + receiver.getName() + "接受" + "\r\n 任务标题：" + task.getTitle() + "\r\n 任务内容：" + task.getContent()
							+ "\r\n 任务工期：" + task.getDurTime() + "\r\n 结束时间：" + DateUtil.getDateMinString(task.getFinishDate()) + "\r\n 虚拟币：" + task.getFinalBitcoin());
					System.out.println(me.getContent());
					me.setToUser(employeeId_);
					service.messageSend(me);
				}
			}
			return "success";
		} else {
			return "很抱歉，" + task.getReceiver().getName() + "已经接受了任务：" + task.getTitle();
		}
	}

	/**
	 * 每分钟都去检查所有任务是否过期，如果过期需要更新任务状态
	 * 
	 * @throws WxErrorException
	 */
	public void updateTaskStatus() throws WxErrorException {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<更新状态");
		List<Task> tasks = getAllTasks();
		for (Task task : tasks) {
			// 未完成任务到期后自动更新状态为已完成
			if (task.getStatus().equals(Task.TASK_STATE_UNFINISH)) {
				// 先检查是否是销售任务:如果不是销售任务
				if (!"xs".equals(task.getTaskType().getTypeKey())) {
					if (new Date().compareTo(task.getFinishDate()) >= 0) {
						task.setStatus(Task.TASK_STATE_FINISH);
						taskDAO.update(task);
					}
				} else {// 如果是销售任务
					DateTime dateTime = new DateTime(task.getFinishDate().getTime());
					//检查此任务是否已经提醒过用户
					if(Task.UNNOTICE.equals(task.getIsNotice())){
						// 销售任务已经到期，不过不更新状态，而是提醒用户赶快提交
						if (dateTime.isBeforeNow()) {
							task.setIsNotice(Task.NOTICE);
							taskDAO.update(task);
							// 通知发布还价人
							WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
							WxCpMessage me = new WxCpMessage();
							me.setMsgType(WxConsts.XML_MSG_TEXT);
							me.setAgentId("15");
							me.setToUser(task.getPublisher().getUserId());
							me.setContent("您发布的任务：" + task.getTitle() + "已经到期，请在3日内提交审核！否则系统将不会给您任何补偿！");
							service.messageSend(me);
						}
					}
					
					dateTime.plusDays(3);
					if (dateTime.isBeforeNow()) {
						task.setStatus(Task.TASK_STATE_UNCOMMIT);
						taskDAO.update(task);
						// 通知发布还价人
						WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
						WxCpMessage me = new WxCpMessage();
						me.setMsgType(WxConsts.XML_MSG_TEXT);
						me.setAgentId("15");
						me.setToUser(task.getPublisher().getUserId());
						me.setContent("您发布的任务：" + task.getTitle() + "在任务结束后3天内未提交审核！");
						service.messageSend(me);
					}
				}
			}
			// 未处理任务到期后自动更新状态为超时
			if (Task.TASK_STATE_UNRECEIVE.equals(task.getStatus())) {
				DateTime dateTime = new DateTime(task.getFinishDate().getTime());
				if (dateTime.isBeforeNow()) {
					System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<超时："+task.getTitle());
					task.setStatus(Task.TASK_STATE_OVER);
					taskDAO.update(task);
					// 通知发布还价人
					WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
					WxCpMessage me = new WxCpMessage();
					me.setMsgType(WxConsts.XML_MSG_TEXT);
					me.setAgentId("15");
					me.setToUser(task.getPublisher().getUserId());
					me.setContent("您发布的任务：" + task.getTitle() + "已经超时！");
					service.messageSend(me);
				}
			}

		}
	}

	public List<Task> getMyFinishedTask(String employeeId) {
		Task task = new Task();
		task.setPublisher(new Employee(employeeId));
		task.setStatus(Task.TASK_STATE_FINISH);
		return taskDAO.getTaskByTask(task);
	}

	/**
	 * 获取任务分页对象
	 * 
	 * @param page
	 * @param order
	 * @return
	 */
	public Page<Task> getPage(Page<Task> page, Task task) {
		List<Task> taskList = taskDAO.getTaskList(task);
		page.setTotalRecord(taskList.size());
		if (taskList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(taskList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(taskList.subList(page.getStartOfPage(), taskList.size()));
		}
		return page;
	}

	public List<Task> getTaskByStatus(String status) {
		Task task = new Task();
		task.setStatus(status);
		return taskDAO.getTaskByTask(task);
	}

	/**
	 * 删除任务
	 * 
	 * */
	public void deleteTask(String taskId) {
		Task task = taskDAO.get(taskId);
		taskDAO.deleteTaskByTaskId(taskId);
		// 日志记录
		User user = UserHelper.getCurrUser();
		LogUtil.saveLog("删除任务标题为：" + task.getTitle() + " 的任务。", user.getUsername(), Log.ADMIN_TYPE_YES, user.getId(),
				Log.LOGTYPE_TASK, task.getId(), Log.OPERATE_TASK_DELETE);
	}

	/**
	 * 提交审核（返回success代表成功，其他代表失败）
	 * 
	 * @param taskId
	 * @param employeeId
	 * @param isSuccess
	 * @return
	 * @throws WxErrorException
	 */
	public String commitVerify(String taskId, String employeeId, String isSuccess) throws WxErrorException {
		Task task = taskDAO.get(taskId);
		// 1、检查状态是否是正在进行中任务
		if (Task.TASK_STATE_UNFINISH.equals(task.getStatus())) {
			// 2、检查任务是否是销售任务
			if ("xs".equals(task.getTaskType().getTypeKey())) {
				// 3、检查该任务是否是此人发布
				if (task.getPublisher().getId().equals(employeeId)) {
					// 4、检查是否在任务截止时间后三天内提交
					DateTime dateTime = new DateTime(task.getFinishDate().getTime());
					dateTime.plusDays(3);
					if (dateTime.isAfterNow()) {
						task.setStatus(Task.TASK_STATE_UNVERIFY);
						task.setIsSuccess(isSuccess);
						taskDAO.update(task);
						LogUtil.saveLog(task.getPublisher().getName()+"对任务："+task.getTitle()+"("+task.getIsSuccessVal()+")"+"提交了审核",task.getPublisher().getName(), Log.ADMIN_TYPE_NO, task.getPublisher().getId(), Log.LOGTYPE_TASK, task.getId(), Log.OPERATE_TASK_COMMITVERIFY);
						// 通知管理员有员工提交审核
						WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
						WxCpMessage me = new WxCpMessage();
						me.setMsgType(WxConsts.XML_MSG_TEXT);
						me.setAgentId("15");
						me.setToUser("YT0017");
						me.setContent(task.getPublisher().getName() + "已经提交了任务：‘" + task.getTitle() + "’的审核材料，请登陆后台进行审核！");
						service.messageSend(me);
						return "success";
					}
				}
			}
		}
		return "fail";
	}
	
	
	/**
	 * 任务审核
	 * 
	 * @param task
	 *            Task对象
	 * @param refuseReason
	 *            拒绝任务情况下的拒绝理由
	 */
	public void checkTask(Task task, String refuseReason) throws WxErrorException {
		// 更新任务状态
		taskDAO.update(task);

		// 满足一定条件的情况下 进行 任务返利
		Employee employee = employeeDAO.get(task.getPublisher().getId());
		if (Task.TASK_STATE_SUCCESS.equals(task.getStatus())) {
			if (Task.FAIL.equals(task.getIsSuccess())) {
				employee.setBitcoinSurplus((Double.parseDouble(employee.getBitcoinSurplus())
						+ Double.parseDouble(task.getFinalBitcoin()) / 2) + "");
				employeeDAO.update(employee);
			}
		}

		// 日志记录
		User user = UserHelper.getCurrUser();
		String operateVal = "";
		if (Task.TASK_STATE_FAIL.equals(task.getStatus())) {
			operateVal = Log.OPERATE_TASK_VERIFYFAIL;
		} else if (Task.TASK_STATE_SUCCESS.equals(task.getStatus())) {
			operateVal = Log.OPERATE_TASK_VERIFYSUCCESS;
		}
		LogUtil.saveLog("审核任务 - " + task.getTitle() + " - 状态为：" + task.getStatusVal(), user.getUsername(),
				Log.ADMIN_TYPE_YES, user.getId(), Log.LOGTYPE_TASK, task.getId(), operateVal);

		// 给提交审核资料人员发送审核结果
		WxCpServiceImpl service = (WxCpServiceImpl) SessionManager.getSession("service");
		WxCpMessage me = new WxCpMessage();
		me.setMsgType(WxConsts.XML_MSG_TEXT);
		me.setAgentId("15");
		me.setToUser(employee.getUserId());
		String massageStr = "管理员已经对您提交的资料审核完毕\r\n任务为：" + task.getTitle() + "\r\n审核结果为：" + task.getStatusVal();
		if (StringUtils.isNotBlank(refuseReason) && task.getStatus().equals("6")) {
			massageStr += "\r\n拒绝理由为：" + refuseReason;
		}
		massageStr += "\r\n <a href=\"http://" + employee.getUser().getDomainName() + "/platform/weChat/taskView?id="
				+ task.getId() + "&employeeId=" + employee.getId() + "\">查看详情>>></a>";
		me.setContent(massageStr);
		service.messageSend(me);
	}

	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private DickerDAO dickerDAO;
	@Autowired
	private EvaluationDAO evaluationDAO;

}