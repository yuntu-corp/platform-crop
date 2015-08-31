package com.unitever.platform.util.lucene;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 对外调用的工具类，code为子产品简称，避免和其他模块数据混淆
 * 
 * @author tianyl
 * 
 */
public class LuceneUtil {

	/**
	 * 添加索引
	 * 
	 * @param code
	 * @param docs
	 */
	public static final void addIndex(String code, LuceneDoc doc) {
		List<LuceneDoc> docs = new ArrayList<LuceneDoc>();
		docs.add(doc);
		LuceneServiceFactory.getService().addIndex(code, docs);
	}

	/**
	 * 添加索引
	 * 
	 * @param code
	 * @param docs
	 */
	public static final void addIndex(String code, List<LuceneDoc> docs) {
		LuceneServiceFactory.getService().addIndex(code, docs);
	}

	/**
	 * 更新索引
	 * 
	 * @param code
	 * @param docs
	 */
	public static final void updateIndex(String code, LuceneDoc docs) {
		LuceneServiceFactory.getService().updateIndex(code, docs);
	}

	/**
	 * 删除索引
	 * 
	 * @param code
	 * @param businessId
	 */
	public static final void deleteIndex(String code, String businessId) {
		LuceneServiceFactory.getService().deleteIndex(code, businessId);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @param maxCount
	 * @param keyWordsList
	 * @param searchRangeList
	 * @param sortFieldList
	 * @return businessId 组成的集合
	 */
	public static final List<String> searchIndex(String code, int maxCount, List<LuceneKeyWord> keyWordsList, List<LuceneLongRange> searchRangeList, List<LuceneSortField> sortFieldList) {
		if (StringUtils.isBlank(code)) {
			throw new RuntimeException("参数code不能为空");
		}
		if (keyWordsList == null) {
			keyWordsList = new ArrayList<LuceneKeyWord>();
		}
		if (searchRangeList == null) {
			searchRangeList = new ArrayList<LuceneLongRange>();
		}
		if (sortFieldList == null) {
			sortFieldList = new ArrayList<LuceneSortField>();
		}
		return LuceneServiceFactory.getService().searchIndex(code, maxCount, keyWordsList, searchRangeList, sortFieldList);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @param maxCount
	 * @param keyWordsList
	 * @param searchRangeList
	 * @return
	 */
	public static final List<String> searchIndex(String code, int maxCount, List<LuceneKeyWord> keyWordsList, List<LuceneLongRange> searchRangeList) {
		return searchIndex(code, maxCount, keyWordsList, searchRangeList, null);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @param maxCount
	 * @param keyWordsList
	 * @return
	 */
	public static final List<String> searchIndex(String code, int maxCount, List<LuceneKeyWord> keyWordsList) {
		return searchIndex(code, maxCount, keyWordsList, null, null);
	}

	/**
	 * 查询
	 * 
	 * @param code
	 * @param maxCount
	 * @param keyWord
	 * @return
	 */
	public static final List<String> searchIndex(String code, int maxCount, LuceneKeyWord keyWord) {
		List<LuceneKeyWord> keyWordsList = new ArrayList<LuceneKeyWord>();
		return searchIndex(code, maxCount, keyWordsList, null, null);
	}

}
