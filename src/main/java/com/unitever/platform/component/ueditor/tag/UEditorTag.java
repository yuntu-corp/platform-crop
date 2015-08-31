package com.unitever.platform.component.ueditor.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.ueditor.bean.UEditor;

public class UEditorTag extends AbstractUCTag {

	private static final long serialVersionUID = 2379514558954921018L;
	private String id = "";
	private String name = "";
	private String value = "";
	private String style = "";
	private String zIndex = "2000";
	private boolean wordCount = false;
	private boolean autoHeightEnabled = false;
	private boolean simpleEnabled = false;
	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new UEditor(request, response);
	}
	

	@Override
	protected void populateParams() {
		super.populateParams();
		UEditor uEditor = (UEditor) component;
		uEditor.setId(id);
		uEditor.setName(name);
		uEditor.setValue(value);
		uEditor.setStyle(style);
		uEditor.setAutoHeightEnabled(autoHeightEnabled);
		uEditor.setWordCount(wordCount);
		uEditor.setzIndex(zIndex);
		uEditor.setSimpleEnabled(simpleEnabled);
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

	public boolean isWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = Boolean.valueOf(wordCount);
	}

	public boolean isAutoHeightEnabled() {
		return autoHeightEnabled;
	}

	public void setAutoHeightEnabled(String autoHeightEnabled) {
		this.autoHeightEnabled = Boolean.valueOf(autoHeightEnabled);
	}

	public boolean isSimpleEnabled() {
		return simpleEnabled;
	}

	public void setSimpleEnabled(String simpleEnabled) {
		this.simpleEnabled = Boolean.valueOf(simpleEnabled);
	}

}
