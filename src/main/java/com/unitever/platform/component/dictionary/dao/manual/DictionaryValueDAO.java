package com.unitever.platform.component.dictionary.dao.manual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.unitever.platform.component.dictionary.model.Dictionary;
import com.unitever.platform.component.dictionary.model.DictionaryValue;
import com.unitever.platform.core.dao.BaseDAO;
import com.unitever.platform.core.dao.Page;

@Repository
public class DictionaryValueDAO extends BaseDAO<DictionaryValue, String>{


	public DictionaryValue getWithDictIdAndValueAndExclude(String dictId, String value , String excludeValue) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictId", dictId);
		params.put("value", value);
		if(StringUtils.isNotBlank(excludeValue)){
			params.put("excludeValue", excludeValue);
		}
		return this.get("getWithDictIdAndValueAndExclude", params);
	}

	public DictionaryValue getWithDictIdAndCodeAndExclude(String dictId, String code,String excludeCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictId", dictId);
		params.put("code", code);
		if(StringUtils.isNotBlank(excludeCode)){
			params.put("excludeCode", excludeCode);
		}
		return this.get("getWithDictIdAndCodeAndExclude", params);
	}

	/**
	 * 查找字典条目普通类型的字典值分页对象
	 * @param dictValue
	 * @param findCondition
	 */
	public Page<DictionaryValue> getPageWithGeneralDict(DictionaryValue dictValue, Page<DictionaryValue> page) {
		page.getParams().put("dictKind", Dictionary.DICT_KIND_GENERAL);
		page.getParams().put("dictEditableFlag", Dictionary.DICT_CANEDIT);
		if (dictValue.getDictionary()!=null &&StringUtils.isNotEmpty(dictValue.getDictionary().getId())) {
			page.getParams().put("dictId", dictValue.getDictionary().getId());
		}
		return this.getPage("getPageWithGeneralDict", page);
	}

	public List<DictionaryValue> getListWithDict(DictionaryValue dictValue) {
		if (dictValue.getDictionary()!=null &&StringUtils.isNotEmpty(dictValue.getDictionary().getId())) {
			return getListWithDict(dictValue.getDictionary().getId(), null);
		}
		return null;
	}

	public List<DictionaryValue> getListWithDict(String dictId) {
		return getListWithDict(dictId,null);
	}
	
	public List<DictionaryValue> getListWithDict(String dictId, String excludeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("enableFlag", DictionaryValue.ENABLE);
		params.put("dictId", dictId);
		params.put("excludeId", excludeId);
		return this.getList("getListWithDict", params);
	}

	/**
	 * 根据字典条目编码查找字典值列表
	 */
	public List<DictionaryValue> getListWithDictCode(String dictCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("enableFlag", DictionaryValue.ENABLE);
		params.put("dictCode", dictCode);
		return this.getList("getListWithDictCode", params);
	}
	
	/**
	 * 根据字典条目编码查找字典值列表
	 */
	public List<DictionaryValue> getListWithDictCodeAndJoinParent(String dictCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("enableFlag", DictionaryValue.ENABLE);
		params.put("dictCode", dictCode);
		return this.getList("getListWithDictCodeAndJoinParent", params);
	}

	/**
	 * 根据[字典条目编码]与[字典值编码]得到字典值对象
	 * @param dictCode
	 * @param valueCode
	 */
	public DictionaryValue getWithDictCodeAndValueCode(String dictCode, String valueCode) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictCode", dictCode);
		params.put("valueCode", valueCode);
		return this.get("getWithDictCodeAndValueCode", params);
	}
	
	/**
	 * 根据[字典条目编码]与[字典值]得到字典值对象
	 * @param dictCode
	 * @param value
	 */
	public DictionaryValue getWithDictCodeAndValue(String dictCode, String value) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictCode", dictCode);
		params.put("value", value);
		return this.get("getWithDictCodeAndValue", params);
	}

	
	/**
	 * 普通字典值排序
	 */
	public void sortWithGeneral(String[] ids){
		for(int i=0 ; i<ids.length ; i++){
//			this.batchExecute("update DictionaryValue dv set dv.num=? where dv.id=?",(i+1),ids[i]);
		}
	}
	
	/**
	 * 获取普通字典值最大排序号
	 */
	public int getMaxOrderNumWithGeneral(String dictId){
		return (Integer) this.get("getMaxOrderNumWithGeneral", dictId);
	}
	/**
	 * 获取普通字典值最大排序号
	 */
	public int getMaxOrderNumWithTree(String dictId,String parentId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictId", dictId);
		params.put("parentId", parentId);
		return (Integer) this.get("getMaxOrderNumWithTree", params);
	}
	
	/**
	 * 获取树型字典列表（相同层级）
	 */
	public List<DictionaryValue> getListWithTreeAndSameLevel(String dictId,String parentId){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("dictId", dictId);
		params.put("parentId", parentId);
		return this.getList("getListWithTreeAndSameLevel", params);
	}
	
	public void updateWithEnableFlag(String id , String enableFlag){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("enableFlag", enableFlag);
		this.update("updateWithEnableFlag", params);
	}
	
	public void deleteWithDictId(String dictId){
		this.delete("deleteWithDictId",dictId);
	}
}
