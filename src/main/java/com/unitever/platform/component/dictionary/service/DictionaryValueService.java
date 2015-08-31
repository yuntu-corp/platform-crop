package com.unitever.platform.component.dictionary.service;

// Generated 2008-10-23 14:05:19 by Hibernate Tools 3.2.0.beta7 with hssgen
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.unitever.platform.component.dictionary.dao.manual.DictionaryDAO;
import com.unitever.platform.component.dictionary.dao.manual.DictionaryValueDAO;
import com.unitever.platform.component.dictionary.model.Dictionary;
import com.unitever.platform.component.dictionary.model.DictionaryValue;
import com.unitever.platform.core.dao.Page;
import com.unitever.platform.util.tree.TreeNode;
import com.unitever.platform.util.tree.TreeUtil;


/**
 * @Description : (字典值服务)
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
// Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class DictionaryValueService {

	/**
	 * 通过字典编号找到字典的值
	 */
	public List<DictionaryValue> findDictValueBy(String dictCode) {
		return dictValueDAO.getListWithDictCode(dictCode);
	}
	
	public List<DictionaryValue> getListWithDict(String dictId){
		return dictValueDAO.getListWithDict(dictId);
	}
	
	/**
	 * 根据字典编号和字典值得到字典的显示值
	 */
	public String getDictViewWithDictCodeAndValueCode(String dictCode, String valueCode, boolean isFullPath) {
		DictionaryValue dictValue = dictValueDAO.getWithDictCodeAndValueCode(dictCode, valueCode);
		if (dictValue != null) {
			if (isFullPath) {
				Map<String, DictionaryValue> dictValueMap = getDictValueObjMapWithDictId(dictValue.getDictionary().getId());
				return getDictFullPathName(dictValue,dictValueMap);
			} else {
				return dictValue.getValueI18nName();
			}
		}
		return "";
	}

	/**
	 * 根据字典编号和字典的显示值得到字典编码
	 * @author : wangdawei
	 */
	public String getValueCodeWithDictCodeAndValue(String dictCode, String value) {
		DictionaryValue dictValue = dictValueDAO.getWithDictCodeAndValue(dictCode, value);
		if(dictValue != null){
			return dictValue.getCode();
		}
		return null;
	}

	/**
	 * 得到树型字典全路径名称
	 * @author : wangdawei
	 */
	public String getDictFullPathName(DictionaryValue dictValue,Map<String,DictionaryValue> dictValueMap) {
		String dictFullPathName = dictValue.getValueI18nName();
		if (dictValue.isDictParent()) {
			dictFullPathName = getDictFullPathName(dictValueMap.get(dictValue.getParentId()),dictValueMap) + "-" + dictFullPathName;
		}
		return dictFullPathName;
	}

	/**
	 * 根据字典条目编码查找树类型的字典值列表JSON对象字符串
	 */
	public String getDictValueTreejsonWithDictcode(String dictCode) {
		List<TreeNode> nodes = new ArrayList<TreeNode>(0);
		List<DictionaryValue> dictvalueList = dictValueDAO.getListWithDictCodeAndJoinParent(dictCode);
		Dictionary dict = dictService.getDictWithCode(dictCode);
		TreeNode root = new TreeNode();
		root.setId("-1");
		root.setName(dict.getName());
		root.setNocheck(true);
		nodes.add(root);
		for (DictionaryValue dictValue : dictvalueList) {
			TreeNode node = new TreeNode();
			node.setId(dictValue.getCode());
			node.setValue(dictValue.getCode());
			node.setName(dictValue.getValueI18nName());
			if (dictValue.isDictParent()) {
				node.setParentId(dictValue.getParent().getCode());
			} else {
				node.setParentId(root.getId());
			}
			nodes.add(node);
		}
		return TreeUtil.buildTreeByParent(nodes);
	}

	/**
	 * 保存字典值
	 */
	public void save(DictionaryValue dictValue) {
		dictValue.setEnableFlag(DictionaryValue.ENABLE);
		if (StringUtils.isEmpty(dictValue.getParentId())) {
			dictValue.setParentId(null);
		}
		Dictionary dictionary= dictDAO.get(dictValue.getDictionary().getId());
		int num=0;
		if(dictionary.isKindGeneral()){
			num=dictValueDAO.getMaxOrderNumWithGeneral(dictionary.getId());
		}else{
			num=dictValueDAO.getMaxOrderNumWithTree(dictionary.getId(),dictValue.getParentId());
		}
		
		dictValue.setNum(num+1);
		dictValueDAO.save(dictValue);
	}

	/**
	 * 更新字典值
	 */
	public void update(DictionaryValue dictValue) {
		if (StringUtils.isEmpty(dictValue.getParentId())) {
			dictValue.setParent(null);
		}
		dictValueDAO.update(dictValue);
	}

	/**
	 * 获取普通字典值列表JSON串（根据字典ID）
	 */
	public String getListJsonWithGeneral(String dictId){
		List<DictionaryValue> dictionaryValues = dictValueDAO.getListWithDict(dictId);
		return buildListJson(dictionaryValues);
	}
	
	/**
	 * 获取树型字典值列表JSON串（根据字典ID）
	 */
	public String getListJsonWithTree(String dictId,String dictValueId){
		DictionaryValue dictValue=dictValueDAO.get(dictValueId);
		List<DictionaryValue> dictionaryValues = dictValueDAO.getListWithTreeAndSameLevel(dictId,dictValue.getParentId());
		return buildListJson(dictionaryValues);
	}
	
	private String buildListJson(List<DictionaryValue> dictionaryValues){
		if(dictionaryValues==null)return"";
		List<Map<String, String>> list= new ArrayList<Map<String,String>>(0);
		for (DictionaryValue model : dictionaryValues) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", model.getId());
			map.put("name", model.getValue());
			list.add(map);
		}
		return JSONArray.toJSONString(list);
	}
	
	/**
	 * 普通字典值排序
	 */
	public void sortWithGeneral(String[] ids){
		dictValueDAO.sortWithGeneral(ids);
	}
	
	/**
	 * 查找字典条目普通类型的字典值分页对象
	 * @param dictValue
	 */
	public Page<DictionaryValue> findDictValueByDictKind(String dictId,DictionaryValue dictValue, Page<DictionaryValue> page) {
		dictValue.setDictionary(new Dictionary(dictId));
		return dictValueDAO.getPageWithGeneralDict(dictValue, page);
	}

	/**
	 * 查找字典条目树类型的字典值列表
	 * @param dictValue
	 */
	public List<DictionaryValue> findDictValueByDictKindTree(DictionaryValue dictValue) {
		return dictValueDAO.getListWithDict(dictValue);
	}


	private Map<String, DictionaryValue> getDictValueObjMapWithDictId(String dictId) {
		Map<String,DictionaryValue> dictValueMap = new HashMap<String, DictionaryValue>(0);
		List<DictionaryValue> dictionaryValues = this.getListWithDict(dictId);
		for(DictionaryValue dictionaryValue: dictionaryValues){
			dictValueMap.put(dictionaryValue.getId(), dictionaryValue);
		}
		return dictValueMap;
	}
	
	/**
	 * 获取字典值编码Map（根据字典编码获取）
	 * @param dictCode 字典编码
	 * @return Map<字典值编码,字典值>
	 */
	public Map<String, String> getDictValueMapWithDictCode(String dictCode) {
		Map<String, String> dictionaryValueMap=new LinkedHashMap<String, String>(0);
		List<DictionaryValue> dictionaryValues =this.findDictValueBy(dictCode);
		for(DictionaryValue dictionaryValue:dictionaryValues){
			dictionaryValueMap.put(dictionaryValue.getValue(), dictionaryValue.getCode());
		}
		return dictionaryValueMap;
	}
	
	/**
	 * 获取字典值Map（根据字典编码获取）
	 * 
	 * @param dictCode
	 *            字典编码
	 * @return Map<字典值,字典值编码>
	 */
	public Map<String, String> getDictValueCodeMapWithDictCode(String dictCode) {
		Map<String, String> dictionaryValueMap=new LinkedHashMap<String, String>(0);
		List<DictionaryValue> dictionaryValues =this.findDictValueBy(dictCode);
		for(DictionaryValue dictionaryValue:dictionaryValues){
			dictionaryValueMap.put(dictionaryValue.getCode(), dictionaryValue.getValue());
		}
		return dictionaryValueMap;
	}
	
	/**
	 * 根据字典值Id得到字典值
	 */
	public DictionaryValue get(String id) {
		return dictValueDAO.get(id);
	}

	/**
	 * 字典显示值是否存在
	 */
	public boolean isExitDictValue(String dictId, String value) {
		DictionaryValue dictValue = dictValueDAO.getWithDictIdAndValueAndExclude(dictId, value, null);
		if (dictValue != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字典值编码是否存在
	 */
	public boolean isExitDictValueCode(String dictId, String dictValueCode,String excludeValueCode) {
		DictionaryValue dictValue = dictValueDAO.getWithDictIdAndCodeAndExclude(dictId, dictValueCode, excludeValueCode);
		if (dictValue != null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 字典值是否存在
	 */
	public boolean isExitDictValue(String dictId, String value,String excludeDictValue) {
		DictionaryValue dictValue = dictValueDAO.getWithDictIdAndValueAndExclude(dictId, value, excludeDictValue);
		if (dictValue != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除字典值
	 */
	public void removeDictValues(String[] ids) {
		for(String id : ids){
			dictValueDAO.delete(id);
		}
	}

	/**
	 * 删除字典值
	 */
	public void removeWithDictTree(String[] ids) {
		Map<String,List<String>> dictValueParentChildrenMap = new HashMap<String, List<String>>(0);
		DictionaryValue dictValue = this.get(ids[0]);
		List<DictionaryValue> dictionaryValues = this.getListWithDict(dictValue.getDictionary().getId());
		for(DictionaryValue dictionaryValue : dictionaryValues){
			if(!dictionaryValue.isDictParent()){
				continue;
			}
			if(!dictValueParentChildrenMap.containsKey(dictionaryValue.getParentId())){
				dictValueParentChildrenMap.put(dictionaryValue.getParentId(), new ArrayList<String>());
			}
			dictValueParentChildrenMap.get(dictionaryValue.getParentId()).add(dictionaryValue.getId());
		}
		
		List<String>  deletedIdList=new ArrayList<String>(0);
		for(String id:ids){
			if(!deletedIdList.contains(id)){
				List<String> removeIds = removeWithRecursive(id,dictValueParentChildrenMap);
				deletedIdList.addAll(removeIds);
			}
		}
	}
	
	public void deleteWithDictId(String dictId){
		dictValueDAO.deleteWithDictId(dictId);
	}
	
	private List<String> removeWithRecursive(String id , Map<String,List<String>> dictValueParentChildrenMap){
		List<String>  deletedIdList=new ArrayList<String>(0);
		if(dictValueParentChildrenMap.containsKey(id)){
			List<String> childrenIds = dictValueParentChildrenMap.get(id);
			for(String childrenId:childrenIds){
				List<String> removeWithRecursiveIds = removeWithRecursive(childrenId,dictValueParentChildrenMap);
				deletedIdList.addAll(removeWithRecursiveIds);
			}
		}
		dictValueDAO.delete(id);
		deletedIdList.add(id);
		return deletedIdList;
	}
	
	/**
	 * 禁用
	 */
	public void forbidden(String id) {
		dictValueDAO.updateWithEnableFlag(id, DictionaryValue.DISABLED);
	}

	/**
	 * 启用
	 */
	public void enabled(String id) {
		dictValueDAO.updateWithEnableFlag(id, DictionaryValue.ENABLE);
	}

	/**
	 * 根据字典条目编码得到数据字典树
	 * @param dictCode　字典条目编码
	 * @param selectMode 树选择框模式
	 */
	public String getDictTree(String dictId,String excludeId) {
		Dictionary dict = dictDAO.get(dictId);
		if (dict != null) {
			List<DictionaryValue> dictValues = dictValueDAO.getListWithDict(dictId, excludeId);
			List<TreeNode> nodes =new ArrayList<TreeNode>();
			TreeNode root = new TreeNode();
			root.setId("-1");
			root.setName(dict.getName());
			root.setNocheck(true);
			nodes.add(root);
			for (DictionaryValue dictValue : dictValues) {
				TreeNode node = new TreeNode();
				node.setId(dictValue.getId());
				node.setName(dictValue.getValue());
				if (dictValue.isDictParent()) {
					node.setParentId(dictValue.getParentId());
				} else {
					node.setParentId(root.getId());
				}
				nodes.add(node);
			}
			
			return TreeUtil.buildTreeByParent(nodes);
		}
		return "";
	}

	/**
	 * 根据[字典条目编码]与[字典值编码]得到字典值对象
	 * @param dictCode
	 * @param valueCode
	 */
	public DictionaryValue getDictValueBy(String dictCode, String valueCode) {
		return dictValueDAO.getWithDictCodeAndValueCode(dictCode, valueCode);
	}

	@Autowired
	private DictionaryDAO dictDAO;
	@Autowired
	private DictionaryValueDAO dictValueDAO;
	@Autowired
	private DictionaryService dictService;
}
