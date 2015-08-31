package com.unitever.platform.component.baseform.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.util.JsonUtil;
import com.unitever.platform.util.ReflectUtil;

public abstract class AbstractListBean extends ValidateBean {

	public AbstractListBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	protected Object list;
	protected String listKey;
	protected String listValue;
	protected Integer cols;
	protected String tableLayout;

	private List<ListBeanItem> dataItems = new ArrayList<ListBeanItem>();

	private boolean hasParseData = false;// 是否已解析数据

	public Object getList() {
		return list;
	}

	public void setList(Object list) {
		this.list = list;
	}

	public String getListKey() {
		return listKey;
	}

	public void setListKey(String listKey) {
		this.listKey = listKey;
	}

	public String getListValue() {
		return listValue;
	}

	public void setListValue(String listValue) {
		this.listValue = listValue;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public String getTableLayout() {
		return tableLayout;
	}

	public void setTableLayout(String tableLayout) {
		this.tableLayout = tableLayout;
	}

	public List<ListBeanItem> getDataItems() {
		if (!hasParseData) {
			parseData();
			hasParseData = true;
		}
		return dataItems;
	}

	public void setDataItems(List<ListBeanItem> dataItems) {
		this.dataItems = dataItems;
	}

	@SuppressWarnings("unchecked")
	private void parseData() {
		if (list == null) {
			return;
		}
		if (list instanceof Map) {
			Map<Object, Object> map = (Map<Object, Object>) list;
			for (Object obj : map.keySet()) {
				ListBeanItem item = new ListBeanItem();
				item.setKey(obj.toString());
				item.setValue(map.get(obj).toString());
				parseValue(item);
				dataItems.add(item);
			}
		} else if (list instanceof List) {
			List<Object> objList = (List<Object>) list;
			for (Object obj : objList) {
				String key = getValue(obj, listKey);
				String value = getValue(obj, listValue);
				ListBeanItem item = new ListBeanItem();
				item.setKey(key);
				item.setValue(value);
				parseValue(item);
				dataItems.add(item);
			}
		} else if (list.getClass().isArray()) {
			for (int j = 0; j < Array.getLength(list); j++) {
				Object obj = Array.get(value, j);
				String key = getValue(obj, listKey);
				String value = getValue(obj, listValue);
				ListBeanItem item = new ListBeanItem();
				item.setKey(key);
				item.setValue(value);
				parseValue(item);
				dataItems.add(item);
			}
		} else if ((list instanceof String) && list.toString().trim().startsWith("{")) {
			Map<String, String> map = JsonUtil.parseSimpleJsonStrToMap(list.toString());
			for (String key : map.keySet()) {
				ListBeanItem item = new ListBeanItem();
				item.setKey(key);
				item.setValue(map.get(key));
				parseValue(item);
				dataItems.add(item);
			}
		} else {
			throw new IllegalArgumentException("list 只能为Map,List,Array及简单的json格式（{1:\"学生\",2:\"教师\"}）");
		}
	}

	private void parseValue(ListBeanItem item) {
		if (StringUtils.isBlank(value)) {
			return;
		}
		for (String str : value.split(",")) {
			if (StringUtils.isNotBlank(str)) {
				if (str.equals(item.getKey())) {
					item.setSel(true);
				}
			}
		}
	}

	private String getValue(Object obj, String listKey2) {
		Object result = ReflectUtil.invokeGetterMethodWithCascade(obj, listKey2);
		return result.toString();
	}

}
