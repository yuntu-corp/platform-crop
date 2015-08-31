package com.unitever.platform.util.lucene;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般情况下对应数据库中一条数据
 * 
 * @author tianyl
 * 
 */
public class LuceneDoc {

	/**
	 * 文档id，一般对应数据库中一条数据的id，删除、更新、查询（返回结果）需要此值
	 */
	private String businessId;

	/**
	 * 用于建立索引的map，key为名称，value为内容
	 */
	private Map<String, String> searchFieldMap = new HashMap<String, String>();

	/**
	 * 按范围搜索时设置的搜索字段
	 */
	private Map<String, Long> rangeFieldMap = new HashMap<String, Long>();

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Map<String, String> getSearchFieldMap() {
		return searchFieldMap;
	}

	public void setSearchFieldMap(Map<String, String> searchFieldMap) {
		this.searchFieldMap = searchFieldMap;
	}

	public Map<String, Long> getRangeFieldMap() {
		return rangeFieldMap;
	}

	public void setRangeFieldMap(Map<String, Long> rangeFieldMap) {
		this.rangeFieldMap = rangeFieldMap;
	}

	public void addSearchField(String key, String value) {
		this.searchFieldMap.put(key, value);
	}

	public void addRangeFieldMap(String key, Long value) {
		this.rangeFieldMap.put(key, value);
	}
}
