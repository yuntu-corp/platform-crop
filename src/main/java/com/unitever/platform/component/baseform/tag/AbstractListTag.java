package com.unitever.platform.component.baseform.tag;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.baseform.bean.AbstractListBean;

public abstract class AbstractListTag extends ValidateSupportTag {

	private static final long serialVersionUID = -4113636562056182822L;
	protected Object list;
	protected String listKey;
	protected String listValue;
	protected String cols;
	protected String tableLayout;

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

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getTableLayout() {
		return tableLayout;
	}

	public void setTableLayout(String tableLayout) {
		this.tableLayout = tableLayout;
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		AbstractListBean bean = (AbstractListBean) component;
		bean.setList(list);
		bean.setListKey(listKey);
		bean.setListValue(listValue);
		if (StringUtils.isNotBlank(cols)) {
			bean.setCols(Integer.valueOf(cols));
		}
		bean.setTableLayout(tableLayout);
	}
}
