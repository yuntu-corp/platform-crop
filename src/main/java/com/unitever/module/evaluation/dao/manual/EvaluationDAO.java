package com.unitever.module.evaluation.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.unitever.module.evaluation.model.Evaluation;
import com.unitever.platform.core.dao.BaseDAO;

@Repository
public class EvaluationDAO extends BaseDAO<Evaluation, String>{
	
	public List<Evaluation> getEvaluationByEvaluation(Evaluation evaluation) {
		return this.getList("getEvaluationByEvaluation", evaluation);
	}
	
	public void deleteEvaluationByTaskId(String taskId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("id", taskId);
		this.delete("deleteEvaluationByTaskId",taskId);
	}
	public int getLevelAvgByReceiveId(String receiverId){
		return this.get("getLevelAvgByReceiveId", (Object)receiverId);
	}
}