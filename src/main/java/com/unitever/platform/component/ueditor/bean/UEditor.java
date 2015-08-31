package com.unitever.platform.component.ueditor.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;


public class UEditor extends AbstractUCBean {
	final public static String TEMPLATE = "ueditor";
	private String id;
	private String name;
	private String value;
	private String style;
	private String zIndex = "2000";
	private boolean wordCount = false;// 字数统计开关
	private boolean autoHeightEnabled = false;// 编辑框部分可以随着编辑内容的增加而自动长高
	private boolean simpleEnabled = false;

	public UEditor(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getzIndex() {
		return zIndex;
	}

	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}

	public String getWordCount() {
		return String.valueOf(wordCount);
	}

	public void setWordCount(boolean wordCount) {
		this.wordCount = wordCount;
	}

	public String getAutoHeightEnabled() {
		return String.valueOf(autoHeightEnabled);
	}

	public void setAutoHeightEnabled(boolean autoHeightEnabled) {
		this.autoHeightEnabled = autoHeightEnabled;
	}

	public String getSimpleEnabled() {
		return String.valueOf(simpleEnabled);
	}

	public void setSimpleEnabled(boolean simpleEnabled) {
		this.simpleEnabled = simpleEnabled;
	}


}
