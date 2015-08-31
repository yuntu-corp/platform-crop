package com.unitever.platform.util.lucene;

import java.util.List;

/**
 * Lucene服务接口,两个实现类，一个存储的本地，用于单机环境，一个存储在远程网络，用户分布式环境
 * 
 * @author tianyl
 * 
 */
public interface ILuceneService {

	void addIndex(String code, List<LuceneDoc> docs);

	void updateIndex(String code, LuceneDoc doc);

	void deleteIndex(String code, String businessId);

	List<String> searchIndex(String code, int maxCount, List<LuceneKeyWord> keyWordList, List<LuceneLongRange> searchRangeList, List<LuceneSortField> sortFieldList);

}
