package com.unitever.platform.util.lucene.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.unitever.platform.core.dataSource.helper.DataSourceHolder;
import com.unitever.platform.core.exception.BusinessException;
import com.unitever.platform.util.PropertyUtil;
import com.unitever.platform.util.lucene.ILuceneService;
import com.unitever.platform.util.lucene.LuceneDoc;
import com.unitever.platform.util.lucene.LuceneKeyWord;
import com.unitever.platform.util.lucene.LuceneLongRange;
import com.unitever.platform.util.lucene.LuceneSortField;

public class NetLuceneService implements ILuceneService {

	private static String luceneServiceNetUrl = null;

	static {
		luceneServiceNetUrl = PropertyUtil.getProperty("/conf/lucene.properties", "lucene.netUrl");
	}

	private String getDsName() {
		Object dsName = DataSourceHolder.getCurDataSourceKey();
		String dsNameStr = "";
		if (dsName != null && StringUtils.isNotBlank(dsName.toString())) {
			dsName.toString();
		}
		return dsNameStr;
	}

	private String sendToServer(String json) {
		if (StringUtils.isBlank(luceneServiceNetUrl)) {
			throw new RuntimeException("lucene.netUrl值不能为空，请检查配置");
		}
		try {
			URLConnection conn = new URL(luceneServiceNetUrl + "/luceneService").openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("utf-8"));
			os.flush();
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
			StringBuffer sb = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Lucene Service访问错误，请检查配置lucene.netUrl");
		}
	}

	@Override
	public void addIndex(String code, List<LuceneDoc> docs) {
		String dataSourceName = getDsName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exeMathod", "addIndex");
		map.put("dataSourceName", dataSourceName);
		map.put("code", code);
		map.put("docs", docs);
		String json = JSON.toJSONString(map);
		sendToServer(json);
	}

	@Override
	public void updateIndex(String code, LuceneDoc doc) {
		String dataSourceName = getDsName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exeMathod", "updateIndex");
		map.put("dataSourceName", dataSourceName);
		map.put("code", code);
		map.put("doc", doc);
		String json = JSON.toJSONString(map);
		sendToServer(json);
	}

	@Override
	public void deleteIndex(String code, String businessId) {
		String dataSourceName = getDsName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exeMathod", "deleteIndex");
		map.put("dataSourceName", dataSourceName);
		map.put("code", code);
		map.put("businessId", businessId);
		String json = JSON.toJSONString(map);
		sendToServer(json);
	}

	@Override
	public List<String> searchIndex(String code, int maxCount, List<LuceneKeyWord> keyWordList, List<LuceneLongRange> searchRangeList, List<LuceneSortField> sortFieldList) {
		String dataSourceName = getDsName();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exeMathod", "searchIndex");
		map.put("dataSourceName", dataSourceName);
		map.put("code", code);
		map.put("maxCount", maxCount);
		map.put("keyWordList", keyWordList);
		map.put("searchRangeList", searchRangeList);
		map.put("sortFieldList", sortFieldList);
		String json = JSON.toJSONString(map);
		String result = sendToServer(json);
		List<String> resultList = new ArrayList<String>();
		for (String str : result.split(",")) {
			if (StringUtils.isNotBlank(str)) {
				resultList.add(str);
			}
		}
		return resultList;
	}

}
