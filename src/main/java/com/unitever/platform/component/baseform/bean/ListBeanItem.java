package com.unitever.platform.component.baseform.bean;

public class ListBeanItem {

	public ListBeanItem() {

	}

	private String key;
	private String value;
	private boolean sel;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSel() {
		return sel;
	}

	public void setSel(boolean sel) {
		this.sel = sel;
	}

}
