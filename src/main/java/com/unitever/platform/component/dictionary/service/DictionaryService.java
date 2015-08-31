package com.unitever.platform.component.dictionary.service;

// Generated 2008-10-23 14:05:19 by Hibernate Tools 3.2.0.beta7 with hssgen
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.unitever.platform.component.dictionary.dao.manual.DictionaryDAO;
import com.unitever.platform.component.dictionary.model.Dictionary;
import com.unitever.platform.core.dao.Page;


/**
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
// Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class DictionaryService {
	
	/**
	 * 保存字典条目
	 */
	public void save(Dictionary dict) {
		dictDAO.save(dict);
	}
	/**
	 * 更新字典条目
	 */
	public void update(Dictionary dict) {
		dictDAO.update(dict);
	}
	
	/**
	 * 根据字典条目Id得到字典条目
	 */
	public Dictionary get(String id){
		return dictDAO.get(id);
	}
	
	/**
	 * 根据字典条目编码得到到字典条目
	 * @param code
	 */
	public Dictionary getDictWithCode(String code){
		return dictDAO.getDictWithCode(code);
	}
	
	public Page<Dictionary> findDictPage(Dictionary dict, Page<Dictionary> page) {
		return dictDAO.getPage(page);
	}

	/**
	 * 字典编码是否存在
	 * 
	 * @param dictCode
	 *            字典编码
	 * @return 存在返回true，不存在返回false
	 */
	public boolean isExistsDictCode(String code , String excludeCode) {
		return dictDAO.isExistsDictCode(code, excludeCode);
	}
	
	/**
	 * 得到普通字典条目列表(字典值允许编辑)
	 */
	public List<Dictionary> findDictWithGeneralsAndCanedit(){
		return dictDAO.getDictWithKindAndCanedit(Dictionary.DICT_KIND_GENERAL);
	}
	
	/**
	 * 得到树型字典条目列表(字典值允许编辑)
	 */
	public List<Dictionary> findDictWithTreeAndCanedit(){
		return dictDAO.getDictWithKindAndCanedit(Dictionary.DICT_KIND_TREE);
	}
	
	/**
	 * 得到普通字典条目列表
	 */
	public List<Dictionary> findDictByGenerals(){
		return dictDAO.getDictWithKind(Dictionary.DICT_KIND_GENERAL);
	}
	
	/**
	 * 得到树型字典条目列表
	 */
	public List<Dictionary> findDictByTree(){
		return dictDAO.getDictWithKind(Dictionary.DICT_KIND_TREE);
	}
	
	/**
	 * 删除字典条目
	 */
	public void delete(String id) {
		dictionaryValueService.deleteWithDictId(id);
		dictDAO.delete(id);
	}

	/**
	 * 得到普通字典列表JSON串（属性：id、name）
	 */
	public String getListJsonWithGeneral(){
		List<Dictionary> dictionaries = this.findDictByGenerals();
		return buildListJson(dictionaries);
	}
	/**
	 * 得到树型字典列表JSON串（属性：id、name）
	 */
	public String getListJsonWithTree(){
		List<Dictionary> dictionaries = this.findDictByTree();
		return buildListJson(dictionaries);
	}
	
	/**
	 * 得到普通字典列表JSON串(字典值可编辑)
	 */
	public String getListJsonWithGeneralAndCanedit(){
		List<Dictionary> dictionaries = this.findDictWithGeneralsAndCanedit();
		return buildListJson(dictionaries);
	}
	/**
	 * 得到树型字典列表JSON串(字典值可编辑)
	 */
	public String getListJsonWithTreeAndCanedit(){
		List<Dictionary> dictionaries = this.findDictWithTreeAndCanedit();
		return buildListJson(dictionaries);
	}
	
	public String buildListJson(List<Dictionary> dictionaries){
		if(dictionaries==null)return"";
		List<Map<String, String>> list= new ArrayList<Map<String,String>>(0);
		for (Dictionary dictionary : dictionaries) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", dictionary.getId());
			map.put("name", dictionary.getName());
			map.put("type", dictionary.getKind());
			list.add(map);
		}
		return JSONArray.toJSONString(list);
	}
	
	@Autowired
	private DictionaryDAO dictDAO;
	@Autowired
	private DictionaryValueService dictionaryValueService;
}
