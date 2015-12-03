package com.unitever.module.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.unitever.module.evaluation.model.Evaluation;
import com.unitever.module.evaluation.service.EvaluationService;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping(value = "/evaluation")
public class EvaluationController extends SpringController {
	
	
	/**
	 * 跳转至评价index页面
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		return "module/evaluation/jsp/evaluation-index";
	}

	/**
	 * 跳转至评价列表页面
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(@FormModel("page") Page<Evaluation> page, @FormModel("model") Evaluation evaluation){
		System.out.println(evaluation.getLevel());
		setAttribute("pageObj", evaluationService.getPage(page, evaluation));
		setAttribute("model", evaluation);
		return "module/evaluation/jsp/evaluation-list";
	}
	/**
	 * 跳转至评价详情页面
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/view")
	public String view(@RequestParam(value="id") String id){
		setAttribute("model", evaluationService.getEvaluationById(id));
		return "module/evaluation/jsp/evaluation-view";
	}
	
	@Autowired
	private EvaluationService evaluationService;
}