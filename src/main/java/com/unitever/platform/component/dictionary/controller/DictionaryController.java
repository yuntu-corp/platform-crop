package com.unitever.platform.component.dictionary.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.platform.component.dictionary.model.Dictionary;
import com.unitever.platform.component.dictionary.service.DictionaryService;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/sys/dictionary")
public class DictionaryController extends SpringController {

	@Autowired
	private DictionaryService dictionaryService;

	@RequestMapping("/index")
	public String index() {
		return "/platform/component/dictionary/dictionary-index";
	}

	@RequestMapping("/list")
	public String list() {
		return "/platform/component/dictionary/dictionary/dictionary-list";
	}
	
	@RequestMapping("/listDataWithGeneral")
	@ResponseBody
	public String listDataWithGeneral() {
		return dictionaryService.getListJsonWithGeneral();
	}
	
	@RequestMapping("/listDataWithTree")
	@ResponseBody
	public String listDataWithTree() {
		return dictionaryService.getListJsonWithTree();
	}
	
	@RequestMapping("/input")
	public String input(String id) {
		//--得到字典类型
		this.getRequest().setAttribute("DictKindMap", Dictionary.getDictKindMap());
		this.getRequest().setAttribute("canEditFlagMap", Dictionary.getCanEditFlagMap());
		if (StringUtils.isNotBlank(id)) {
			setAttribute("model", dictionaryService.get(id));
		}else{
			setAttribute("model", new Dictionary());
		}
		return "/platform/component/dictionary/dictionary/dictionary-input";
	}

	@RequestMapping("/info")
	public String info(String id) {
		this.getRequest().setAttribute("DictKindMap", Dictionary.getDictKindMap());
		this.getRequest().setAttribute("canEditFlagMap", Dictionary.getCanEditFlagMap());
		setAttribute("model", dictionaryService.get(id));
		return "/platform/component/dictionary/dictionary/dictionary-info";
	}
	
	/**
	 * 删除字典条目
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String id) {
		dictionaryService.delete(id);
	}

	/**
	 * 保存或更新字典条目
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") Dictionary model) {
		if (StringUtils.isBlank(model.getId())) {
			dictionaryService.save(model);
		} else {
			dictionaryService.update(model);
		}
		return model.getId();
	}

	/**
	 * Ajax字典编码检查
	 * @return 0 通过验证 其它返回结果代表没有通过验证，并且结果为错误信息
	 */
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(@RequestParam(value = "model.code") String code , String excludeCode) {
		if (this.dictionaryService.isExistsDictCode(code, excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
}
