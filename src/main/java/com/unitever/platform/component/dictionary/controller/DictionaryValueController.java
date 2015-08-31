package com.unitever.platform.component.dictionary.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unitever.platform.component.dictionary.model.DictionaryValue;
import com.unitever.platform.component.dictionary.service.DictionaryService;
import com.unitever.platform.component.dictionary.service.DictionaryValueService;
import com.unitever.platform.core.web.argument.annotation.FormModel;
import com.unitever.platform.core.web.controller.SpringController;

@Controller
@RequestMapping("/sys/dictionaryValue")
public class DictionaryValueController extends SpringController {

	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private DictionaryValueService dictionaryValueService;

	@RequestMapping("/index")
	public String index() {
		return "/platform/component/dictionary/dictionaryValue/dictionaryValue-index";
	}

	@RequestMapping("/list")
	public String list(String dictId) {
		setAttribute("dictId", dictId);
		setAttribute("dictionaryValues", dictionaryValueService.getListWithDict(dictId));
		return "/platform/component/dictionary/dictionaryValue/dictionaryValue";
	}
	
	@RequestMapping("/listDataWithGeneral")
	@ResponseBody
	public void listData(String dictId){
		setAttribute("dictionaryValues", dictionaryValueService.getListWithDict(dictId));
	}
	
	@RequestMapping("/getDataJsonWithGeneral")
	@ResponseBody
	public String getDataJsonWithGeneral(String dictId){
		return dictionaryValueService.getListJsonWithGeneral(dictId);
	}
	
	@RequestMapping("/getDataJsonWithTree")
	@ResponseBody
	public String getDataJsonWithTree(String dictId ,String id){
		return dictionaryValueService.getListJsonWithTree(dictId,id);
	}
	
	@RequestMapping("/dictListDataWithGeneral")
	@ResponseBody
	public String dictListDataWithGeneral() {
		return dictionaryService.getListJsonWithGeneralAndCanedit();
	}
	
	@RequestMapping("/dictListDataWithTree")
	@ResponseBody
	public String dictListDataWithTree() {
		return dictionaryService.getListJsonWithTreeAndCanedit();
	}
	
	@RequestMapping("/input")
	public String input(String dictId ,String id) {
		if (StringUtils.isNotBlank(id)) {
			setAttribute("model", dictionaryValueService.get(id));
		}else{
			DictionaryValue model = new DictionaryValue();
			model.setDictionary(dictionaryService.get(dictId));
			setAttribute("model", model);
		}
		return "/platform/component/dictionary/dictionaryValue/dictionaryValue-input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(@FormModel("model") DictionaryValue model) {
		if (StringUtils.isBlank(model.getId())) {
			dictionaryValueService.save(model);
		} else {
			dictionaryValueService.update(model);
		}
		return model.getId();
	}

	@RequestMapping("/forbidden")
	@ResponseBody
	public void forbidden(String id) {
		dictionaryValueService.forbidden(id);
	}

	@RequestMapping("/enabled")
	@ResponseBody
	public void enabled(String id) {
		dictionaryValueService.enabled(id);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void delete(String[]sels) {
		dictionaryValueService.removeDictValues(sels);
	}

	@RequestMapping("/sortWithGeneral")
	@ResponseBody
	public void sortWithGeneral(String[]sels){
		dictionaryValueService.sortWithGeneral(sels);
	}
	
	@RequestMapping("/listTree")
	public String listTree(String dictId){
		setAttribute("dictId", dictId);
		return "/platform/component/dictionary/dictionaryValue/dictionaryValue-treelist";
	}
	
	@RequestMapping("/listTreeData")
	@ResponseBody
	public String listTreeData(String dictId){
		return dictionaryValueService.getDictTree(dictId,null);
	}
	
	@RequestMapping("/inputTree")
	public String inputTree(String id,String dictId) {
		if (StringUtils.isNotBlank(id)) {
			setAttribute("model", dictionaryValueService.get(id));
		}else{
			DictionaryValue model = new DictionaryValue();
			model.setDictionary(dictionaryService.get(dictId));
			setAttribute("model", model);
		}
		return "/platform/component/dictionary/dictionaryValue/dictionaryValue-treeInput";
	}

	@RequestMapping("/saveTree")
	@ResponseBody
	public String saveTree(@FormModel("model") DictionaryValue model) {
		return this.save(model);
	}

	@RequestMapping("/deleteTree")
	@ResponseBody
	public void deleteTree(String[] sels) {
		dictionaryValueService.removeWithDictTree(sels);
	}

	/**
	 * Ajax字典值编码检查
	 */
	@RequestMapping("/ajaxCheckCode")
	@ResponseBody
	public String ajaxCheckCode(String dictId ,@RequestParam(value = "model.code") String code , String excludeCode) {
		if (dictionaryValueService.isExitDictValueCode(dictId,code,excludeCode)) {
			return "false";
		}else{
			return "true";
		}
	}
	/**
	 * Ajax字典值检查
	 */
	@RequestMapping("/ajaxCheckValue")
	@ResponseBody
	public String ajaxCheckValue(String dictId ,@RequestParam(value = "model.value") String value , String excludeValue) {
		if (dictionaryValueService.isExitDictValue(dictId,value,excludeValue)) {
			return "false";
		}else{
			return "true";
		}
	}

	/**
	 * 根据字典条目ID得到数据字典树
	 */
	@RequestMapping("/getParentTree")
	public String getParentTree(String dictId , String hideId){
		String treeJson=dictionaryValueService.getDictTree(dictId,hideId);
		this.getRequest().setAttribute("treeJson", treeJson);
		this.getRequest().setAttribute("idHead", this.getRequest().getParameter("idHead"));
		this.getRequest().setAttribute("isFullPath", Boolean.valueOf(this.getRequest().getParameter("isFullPath")));
		return "/platform/component/dictionary/dictionaryValue/dictValue-parentTree";
	}
}
