package com.unitever.module.evaluation.service;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitever.module.employee.dao.manual.EmployeeDAO;
import com.unitever.module.employee.model.Employee;
import com.unitever.module.evaluation.dao.manual.EvaluationDAO;
import com.unitever.module.evaluation.model.Evaluation;
import com.unitever.module.log.model.Log;
import com.unitever.module.task.dao.manual.TaskDAO;
import com.unitever.module.task.model.Task;
import com.unitever.module.wechat.util.LogUtil;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.util.UUID;

@Service
@Transactional
public class EvaluationService {
	
	
	public Evaluation getEvaluationById(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		return evaluationDAO.get(id);
	}
	
	
	public void save(Evaluation evaluation){
		if(evaluation!=null){
			evaluationDAO.save(evaluation);
		}
	}
	
	
	public void update(Evaluation evaluation){
		if(evaluation!=null){
			evaluationDAO.update(evaluation);
		}
	}
	
	
	public List<Evaluation> getEvaluationByPublisherId(String publisherId){
		if(StringUtils.isBlank(publisherId)){
			return null;
		}
		Evaluation evaluation=new Evaluation();
		Employee publisher=new Employee();
		publisher.setId(publisherId);
		evaluation.setPublisher(publisher);
		return evaluationDAO.getEvaluationByEvaluation(evaluation);
	}
	
	public List<Evaluation> getEvaluationByReceiverId(String receiverId){
		if(StringUtils.isBlank(receiverId)){
			return null;
		}
		Evaluation evaluation=new Evaluation();
		Employee receiver=new Employee();
		receiver.setId(receiverId);
		evaluation.setReceiver(receiver);
		return evaluationDAO.getEvaluationByEvaluation(evaluation);
	}
	/**
	 * 获取评价分页对象
	 * @param page
	 * @param order
	 * @return
	 */
	public Page<Evaluation> getPage(Page<Evaluation> page, Evaluation evaluation) {
		List<Evaluation> evaluationList = evaluationDAO.getEvaluationByEvaluation(evaluation);
		page.setTotalRecord(evaluationList.size());
		if (evaluationList.size() >= (page.getStartOfPage() + page.getPageSize())) {
			page.setResults(evaluationList.subList(page.getStartOfPage(), page.getStartOfPage() + page.getPageSize()));
		} else {
			page.setResults(evaluationList.subList(page.getStartOfPage(), evaluationList.size()));
		}
		return page;
	}
	
	public void evaluate(String taskId, String publisherId,String receiverId, String content, String level){
		Task task=taskDAO.get(taskId);
		//检查任务发布人是否已经评价
		if(task.getPublisher().getId().equals(publisherId)){
			if(Task.UNEVALUATE.equals(task.getIsPublisherEvaluate())){
				Evaluation evaluation=new Evaluation();
				//1、保存评价
				evaluation.setId(UUID.getUUID());
				evaluation.setCreateTime(new Date());
				evaluation.setPublisher(new Employee(publisherId));
				evaluation.setReceiver(new Employee(receiverId));
				evaluation.setTask(new Task(taskId));
				evaluation.setLevel(level);
				evaluation.setContent(content);
				evaluationDAO.save(evaluation);
				LogUtil.saveLog(task.getPublisher().getName()+"向"+task.getReceiver().getName()+"发布了评价："+evaluation.getContent(), task.getPublisher().getName(), Log.ADMIN_TYPE_NO,task.getPublisher().getId(), Log.LOGTYPE_EVALUATION, evaluation.getId(), Log.OPERATE_EVALUATION_SAVE);
				//2、修改被评价人星级
				Employee employee=new Employee(receiverId);
				employee.setEvaluationLevel(evaluationDAO.getLevelAvgByReceiveId(receiverId)+"");
				employeeDAO.update(employee);
				//3、更新任务
				task.setIsPublisherEvaluate(Task.EVALUATE);
				taskDAO.update(task);
			}
		}
		//检查接收人是否评价
		if(task.getReceiver().getId().equals(publisherId)){
			if(Task.UNEVALUATE.equals(task.getIsReceiverEvaluate())){
				Evaluation evaluation=new Evaluation();
				//1、保存评价
				evaluation.setCreateTime(new Date());
				evaluation.setPublisher(new Employee(publisherId));
				evaluation.setReceiver(new Employee(receiverId));
				evaluation.setTask(new Task(taskId));
				evaluation.setLevel(level);
				evaluation.setContent(content);
				evaluationDAO.save(evaluation);
				LogUtil.saveLog(task.getReceiver().getName()+"向"+task.getPublisher().getName()+"发布了评价："+evaluation.getContent(), task.getReceiver().getName(), Log.ADMIN_TYPE_NO,task.getReceiver().getId(), Log.LOGTYPE_EVALUATION, evaluation.getId(), Log.OPERATE_EVALUATION_SAVE);
				//2、修改被评价人星级
				Employee employee=new Employee(receiverId);
				employee.setEvaluationLevel(evaluationDAO.getLevelAvgByReceiveId(receiverId)+"");
				employeeDAO.update(employee);
				//3、更新任务
				task.setIsReceiverEvaluate(Task.EVALUATE);
				taskDAO.update(task);
			}
		}
	}
	
	@Autowired
	private EvaluationDAO evaluationDAO;
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private EmployeeDAO employeeDAO;
}