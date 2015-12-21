package com.unitever.module.wechat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.module.dicker.model.Dicker;
import com.unitever.module.dicker.service.DickerService;
import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.employee.service.EmployeeService;
import com.unitever.module.evaluation.model.Evaluation;
import com.unitever.module.evaluation.service.EvaluationService;
import com.unitever.module.task.model.Task;
import com.unitever.module.task.service.TaskService;
import com.unitever.module.tasktype.service.TaskTypeService;
import com.unitever.module.wechat.manager.SessionManager;
import com.unitever.module.wechat.service.WeChatService;
import com.unitever.module.wechat.util.CookieUtil;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/weChat")
public class WeChatController extends SpringController {

	@Autowired
	private WeChatService weChatService;

	/**
	 * 处理微信响应请求
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/weChatHandler")
	@ResponseBody
	public String weChatHandler(
			@RequestParam(value = "msg_signature", required = false) String msgSignature,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "nonce", required = false) String nonce,
			@RequestParam(value = "echostr", required = false) String echostr)
			throws IOException {
		WxCpInMemoryConfigStorage storage = (WxCpInMemoryConfigStorage) SessionManager
				.getSession("storage");
		WxCpService wxCpService = new WxCpServiceImpl();
		if ("GET".equals(getRequest().getMethod())) {
			if (!wxCpService.checkSignature(msgSignature, timestamp, nonce,
					echostr)) {
				WxCpCryptUtil cryptUtil = new WxCpCryptUtil(storage);
				return cryptUtil.decrypt(echostr);
			}
		} else if ("POST".equals(getRequest().getMethod())) {
			if (!wxCpService.checkSignature(msgSignature, timestamp, nonce,
					echostr)) {
				WxCpXmlMessage inMessage = WxCpXmlMessage.fromEncryptedXml(
						getRequest().getInputStream(), storage, timestamp,
						nonce, msgSignature);
				WxCpXmlOutMessage outMessage = weChatService
						.weChatHandler(inMessage);
				if (outMessage != null) {
					return outMessage.toEncryptedXml(storage);
				}
			}
		}
		return null;
	}

	/**
	 * 今日价格(菜单1)
	 * 
	 * @param code
	 * @param state
	 */
	@RequestMapping(value = "/currentInfo", method = RequestMethod.GET)
	public String currentInfo(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state) {
		try {
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
					.getSession("service");
			//当code不为空并且cookie中userID为空时，通过code获取userId（第一回点击）
			if(StringUtils.isNotBlank(code)){
				if(CookieUtil.getCookieByName(getRequest(), "userId")==null){
					// 通过code获取userid
					String userId = service.oauth2getUserInfo(code)[0];
					//第一次访问得到的userId保存起来
					CookieUtil.addCookie(getResponse(), "userid",  userId, 1000*60*5);
					//自己的信息
					Employee employee = employeeService.getEmployeeByUserId(userId);
					//重新获取头像
					employee=weChatService.reGetHeadImgUrl(employee);
					employee.setWxCpUser(service.userGet(userId));
					List<Employee> employeeList=employeeService.getAllEmployees();
					setAttribute("employeeList", employeeList);
					setAttribute("employee", employee);
					
				}else{//用户第二回点击直接，直接使用cookie中的userId
					String userId=CookieUtil.getCookieByName(getRequest(), "userId").getValue();
					Employee employee = employeeService.getEmployeeByUserId(userId);
					//重新获取头像
					employee=weChatService.reGetHeadImgUrl(employee);
					employee.setWxCpUser(service.userGet(userId));
					List<Employee> employeeList=employeeService.getAllEmployees();
					setAttribute("employeeList", employeeList);
					setAttribute("employee", employee);
				}
			}
			
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "module/weChat/jsp/weChat-currentInfo";
	}
	
	/**
	 * 任务公告(菜单2)
	 * 
	 * @param code
	 * @param state
	 */
	@RequestMapping(value = "/taskList", method = RequestMethod.GET)
	public String taskList(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "employeeId", required = false) String employeeId) {
		try {
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
					.getSession("service");
			if(StringUtils.isNotBlank(employeeId)){
				Employee employee = employeeService.getEmployeeById(employeeId);
				employee.setWxCpUser(service.userGet(employee.getUserId()));
				setAttribute("taskVo", taskService.getTaskVo());
				setAttribute("employee", employee);
				return "module/weChat/jsp/weChat-taskList";
			}
			//当code不为空并且session中userID为空时，通过code获取userId（第一回点击）
			if(StringUtils.isNotBlank(code)){
				if(CookieUtil.getCookieByName(getRequest(), "userId")==null){
					// 通过code获取userid
					String userId = service.oauth2getUserInfo(code)[0];
					//第一次访问得到的userId保存起来
					CookieUtil.addCookie(getResponse(), "userid",  userId, 1000*60*5);
					//自己的信息
					Employee employee = employeeService.getEmployeeByUserId(userId);
					employee.setWxCpUser(service.userGet(userId));
					setAttribute("taskVo", taskService.getTaskVo());
					setAttribute("employee", employee);
					
				}else{//用户第二回点击直接，直接使用cookie中的userId
					String userId=CookieUtil.getCookieByName(getRequest(), "userId").getValue();
					Employee employee = employeeService.getEmployeeByUserId(userId);
					employee.setWxCpUser(service.userGet(userId));
					setAttribute("taskVo", taskService.getTaskVo());
					setAttribute("employee", employee);
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "module/weChat/jsp/weChat-taskList";
	}
	
	/**
	 * 任务详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/taskView",method=RequestMethod.GET)
	public String taskView(@RequestParam(value="id") String id, @RequestParam(value="employeeId") String employeeId){
		setAttribute("task", taskService.getTaskById(id));
		setAttribute("employeeId", employeeId);
		return "module/weChat/jsp/weChat-taskView";
	}
	
	/**
	 * 个人中心(菜单3)
	 * 
	 * @param code
	 * @param state
	 */
	@RequestMapping(value = "/personalCenter", method = RequestMethod.GET)
	public String personalCenter(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "employeeId", required = false) String employeeId) {
		try {
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
					.getSession("service");
			if(StringUtils.isNotBlank(employeeId)){
				Employee employee = employeeService.getEmployeeById(employeeId);
				employee=weChatService.reGetHeadImgUrl(employee);
				employee.setWxCpUser(service.userGet(employee.getUserId()));
				setAttribute("employee", employee);
				return "module/weChat/jsp/weChat-personalCenter";
			}
			//当code不为空并且session中userID为空时，通过code获取userId（第一回点击）
			if(StringUtils.isNotBlank(code)){
				if(CookieUtil.getCookieByName(getRequest(), "userId")==null){
					// 通过code获取userid
					String userId = service.oauth2getUserInfo(code)[0];
					//第一次访问得到的userId保存起来
					//SessionManager.addSession("userId", userId);
					CookieUtil.addCookie(getResponse(), "userId", userId, 1000*60);
					//自己的信息
					Employee employee = employeeService.getEmployeeByUserId(userId);
					employee=weChatService.reGetHeadImgUrl(employee);
					employee.setWxCpUser(service.userGet(userId));
					setAttribute("employee", employee);
				}else{//用户第二回点击直接，直接使用session中的userId
					String userId=CookieUtil.getCookieByName(getRequest(), "userId").getValue();
					Employee employee = employeeService.getEmployeeByUserId(userId);
					employee=weChatService.reGetHeadImgUrl(employee);
					employee.setWxCpUser(service.userGet(userId));
					setAttribute("employee", employee);
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "module/weChat/jsp/weChat-personalCenter";
	}
	
	/**
	 * go发布任务
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/publishTask",method=RequestMethod.GET)
	public String goPublishTask(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,@RequestParam(value="employeeId", required = false) String employeeId){
		
		try {
			if(StringUtils.isNotBlank(employeeId)){
				setAttribute("allEmployes",employeeService.getAllEmployees());
				setAttribute("employeeId", employeeId);
				setAttribute("userId", employeeDAO.get(employeeId).getUserId());
				setAttribute("taskTypes",taskTypeService.getAllTaskTypes());
			}else{
				WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
						.getSession("service");
				//当code不为空并且session中userID为空时，通过code获取userId（第一回点击）
				if(StringUtils.isNotBlank(code)){
					if(CookieUtil.getCookieByName(getRequest(), "userId")==null){
						// 通过code获取userid
						String userId = service.oauth2getUserInfo(code)[0];
						//第一次访问得到的userId保存起来
						CookieUtil.addCookie(getResponse(), "userid",  userId, 1000*60*5);
						//自己的信息
						Employee employee = employeeService.getEmployeeByUserId(userId);
						setAttribute("allEmployes",employeeService.getAllEmployees());
						setAttribute("taskTypes",taskTypeService.getAllTaskTypes());
						setAttribute("employeeId", employee.getId());
						setAttribute("userId",userId);
						
					}else{//用户第二回点击直接，直接使用cookie中的userId
						String userId=CookieUtil.getCookieByName(getRequest(), "userId").getValue();
						Employee employee = employeeService.getEmployeeByUserId(userId);
						setAttribute("allEmployes",employeeService.getAllEmployees());
						setAttribute("employeeId", employee.getId());
						setAttribute("taskTypes",taskTypeService.getAllTaskTypes());
						setAttribute("userId",userId);
					}
				}
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return "module/weChat/jsp/weChat-publishTask";
	}
	
	
	/**
	 * 发布任务
	 * @throws ParseException 
	 */
	@RequestMapping(value="/doPublishTask",method=RequestMethod.POST)
	@ResponseBody
	public String doPublishTask(@FormModel("task") Task task,@RequestParam(value="employeeId") String employeeId) throws ParseException{
		return taskService.publishTask(task, employeeId);
	}
	
	/**
	 * 我发布的任务
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/myPublishedTask",method=RequestMethod.GET)
	public String myPublishedTask(@RequestParam(value="employeeId") String employeeId){
		setAttribute("taskList",taskService.getTaskByPublisherId(employeeId));
		setAttribute("employeeId", employeeId);
		return "module/weChat/jsp/weChat-myPublishedTask";
	}
	
	/**
	 * 我接受的任务
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/myReceivedTask",method=RequestMethod.GET)
	public String myReceivedTask(@RequestParam(value="employeeId") String employeeId){
		setAttribute("taskList",taskService.getTaskByReceiverId(employeeId));
		setAttribute("employeeId", employeeId);
		return "module/weChat/jsp/weChat-myReceivedTask";
	}
	
	/**
	 * 我已经完成的任务
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/myFinishedTask",method=RequestMethod.GET)
	public String myFinishedTask(@RequestParam(value="employeeId") String employeeId){
		setAttribute("taskList",taskService.getMyFinishedTask(employeeId));
		setAttribute("employeeId", employeeId);
		return "module/weChat/jsp/weChat-myFinishedTask";
	}
	
	/**
	 * 评价列表
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/evaluationList",method=RequestMethod.GET)
	public String evaluationList(@RequestParam(value="employeeId") String employeeId){
		//评价他人
		setAttribute("publishList",evaluationService.getEvaluationByPublisherId(employeeId));
		//来自他人
		setAttribute("receiveList", evaluationService.getEvaluationByReceiverId(employeeId));
		setAttribute("employeeId", employeeId);
		return "module/weChat/jsp/weChat-evaluationList";
	}
	
	
	/**
	 * 评价详情
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/evaluationView",method=RequestMethod.GET)
	public String evaluationView(@RequestParam(value="evaluationId") String evaluationId){
		setAttribute("evaluation",evaluationService.getEvaluationById(evaluationId));
		return "module/weChat/jsp/weChat-evaluationView";
	}
	
	/**
	 * go发布评价
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/publishEvaluation",method=RequestMethod.GET)
	public String publishEvaluation(@RequestParam(value="publisherId") String publisherId,
			@RequestParam(value="receiverId") String receiverId,
			@RequestParam(value="taskId") String taskId){
		//Task task=taskService.getTaskById(taskId);
		setAttribute("taskId", taskId);
		setAttribute("publisherId", publisherId);
		setAttribute("receiverId", receiverId);
		return "module/weChat/jsp/weChat-publishEvaluation";
	}
	
	
	/**
	 * 发布评价
	 */
	@RequestMapping(value="/dopublishEvaluation",method=RequestMethod.POST)
	@ResponseBody
	public String dopublishEvaluation(
			@RequestParam(value="taskId", required = false) String taskId,
			@RequestParam(value="publisherId", required = false) String publisherId,
			@RequestParam(value="receiverId", required = false) String receiverId,
			@RequestParam(value="content", required = false) String content,
			@RequestParam(value="level", required = false) String level){
		evaluationService.evaluate(taskId, publisherId, receiverId, content,level);
		return "success";
	}
	
	/**
	 * 待处理消息
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/msgList",method=RequestMethod.GET)
	public String msgList(
			@RequestParam(value="type",required = false) String type,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value="employeeId",required = false) String employeeId){
		if(type!=null){
			System.out.println("<<<<<<<<<<<<<<<type:"+type);
		}else{
			System.out.println("<<<<<<<<<<<<<<<type:null");
		}
		try {
			WxCpServiceImpl service = (WxCpServiceImpl) SessionManager
					.getSession("service");
			if(StringUtils.isNotBlank(employeeId)){
				setAttribute("dickerList", dickerService.getDickerByReceiverIdAndStatus(employeeId, Dicker.DICKER_STATE_UNTREATED));
				setAttribute("unTreatedMsgListByOthers", taskService.getUnReceivedTaskByReceiverId(employeeId));
				setAttribute("employee", employeeService.getEmployeeById(employeeId));
				setAttribute("type", type);
				return "module/weChat/jsp/weChat-msgList";
			}
			//当code不为空并且session中userID为空时，通过code获取userId（第一回点击）
			if(StringUtils.isNotBlank(code)){
				Employee employee=null;
				if(CookieUtil.getCookieByName(getRequest(), "userId")==null){
					// 通过code获取userid
					String userId = service.oauth2getUserInfo(code)[0];
					//第一次访问得到的userId保存起来
					//SessionManager.addSession("userId", userId);
					CookieUtil.addCookie(getResponse(), "userId", userId, 1000*60);
					//自己的信息
					employee = employeeService.getEmployeeByUserId(userId);
					employee=weChatService.reGetHeadImgUrl(employee);
					//employee.setWxCpUser(service.userGet(userId));
					
				}else{//用户第二回点击直接，直接使用session中的userId
					String userId=CookieUtil.getCookieByName(getRequest(), "userId").getValue();
					employee = employeeService.getEmployeeByUserId(userId);
					employee=weChatService.reGetHeadImgUrl(employee);
					//employee.setWxCpUser(service.userGet(userId));
				}
				setAttribute("dickerList", dickerService.getDickerByReceiverIdAndStatus(employee.getId(), Dicker.DICKER_STATE_UNTREATED));
				setAttribute("unTreatedMsgListByOthers", taskService.getUnReceivedTaskByReceiverId(employee.getId()));
				setAttribute("employee", employee);
				setAttribute("type", type);
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		
		
		
		return "module/weChat/jsp/weChat-msgList";
	}
	
	/**
	 * 拒绝还价
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/refuseDicker",method=RequestMethod.POST)
	@ResponseBody
	public void refuseDicker(
			@RequestParam(value="dickerId") String dickerId) throws WxErrorException{
		dickerService.refuseDicker(dickerId);
	}
	
	/**
	 * 接受还价
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/acceptDicker",method=RequestMethod.POST)
	@ResponseBody
	public String acceptDicker(
			@RequestParam(value="dickerId") String dickerId) throws WxErrorException{
		return dickerService.acceptDicker(dickerId);
	}

	
	/**
	 * go还价
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value="/goDicker",method=RequestMethod.GET)
	public String goDicker(@RequestParam(value="taskId") String taskId,
			@RequestParam(value="publisherId") String publisherId,
			@RequestParam(value="receiverId") String receiverId){
		setAttribute("publisherId", publisherId);
		setAttribute("receiverId", receiverId);
		setAttribute("task", taskService.getTaskById(taskId));
		return "module/weChat/jsp/weChat-goDicker";
	}
	
	/**
	 * do还价
	 * @param employeeId
	 * @return
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/doDicker",method=RequestMethod.POST)
	@ResponseBody
	public String doDicker(@RequestParam(value="taskId") String taskId,
			@RequestParam(value="publisherId") String publisherId,
			@RequestParam(value="receiverId") String receiverId,@FormModel("dicker") Dicker dicker) throws WxErrorException{
		return dickerService.doDicker(taskId, publisherId, dicker, receiverId);
	}
	
	
	/**
	 * 拒绝任务
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/refuseTask",method=RequestMethod.POST)
	@ResponseBody
	public void refuseTask(@RequestParam(value = "taskId") String taskId,
			@RequestParam(value = "employeeId") String employeeId,
			@RequestParam(value = "refuseReason") String refuseReason) throws WxErrorException {
		taskService.refuseTask(taskId, employeeId,refuseReason);
	}
	
	/**
	 * 接受任务
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/acceptTask",method=RequestMethod.POST)
	@ResponseBody
	public String acceptTask(
			@RequestParam(value="taskId") String taskId,@RequestParam(value="employeeId") String employeeId) throws WxErrorException{
		return taskService.acceptTask(taskId, employeeId);
	}
	
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void test(){
		System.out.println("test");
	}
	
	
	/**
	 * 更新今日价格
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/updateCurrentBitcoin",method=RequestMethod.POST)
	@ResponseBody
	public void updateCurrentBitcoin(Employee employee) {
		employeeService.update(employee);
	}
	
	/**
	 * go更新今日价格
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/goUpdateCurrentBitcoin",method=RequestMethod.GET)
	public String geUpdateCurrentBitcoin(String employeeId) {
		setAttribute("employee", employeeDAO.get(employeeId));
		return "module/weChat/jsp/weChat-goUpdateCurrentBitcoin";
	}
	
	/**
	 * 获取任务状态
	 */
	@RequestMapping(value = "/getTaskState", method = RequestMethod.POST)
	@ResponseBody
	public String getTaskState(@RequestParam(value = "taskId") String taskId) {
		Task task = taskService.getTaskById(taskId);
		return task.getStatus();
	}
	
	
	/**
	 * 提交审核
	 * @throws WxErrorException 
	 */
	@RequestMapping(value="/commitVerify",method=RequestMethod.POST)
	@ResponseBody
	public String commitVerify(
			@RequestParam(value="taskId") String taskId,
			@RequestParam(value="employeeId") String employeeId,
			@RequestParam(value="isSuccess") String isSuccess
			) throws WxErrorException{
		return taskService.commitVerify(taskId, employeeId,isSuccess);
	}

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private DickerService dickerService;
	@Autowired
	private EmployeeDAO employeeDAO;
	@Autowired
	private TaskTypeService taskTypeService;


}