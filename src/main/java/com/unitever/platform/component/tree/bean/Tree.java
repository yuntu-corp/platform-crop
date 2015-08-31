package com.unitever.platform.component.tree.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;


public class Tree extends AbstractUCBean {
	final public static String TEMPLATE = "tree";
	private String id;
	private String nodes;
	private String checkValue;
	private String checkStyle;
	private boolean checkEnable;
	private boolean dropdown;
	private String cascade;
	private String expandLevel;
	private String name;
	private String width;
	private boolean required;
	private String placeholder;
//	private String asyncUrl;
//	private String rightMenu;
//	private String isMove;
//	private String cssClass;
//	private String cssStyle;

	public Tree(HttpServletRequest request, HttpServletResponse response) {
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

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getCheckStyle() {
		return checkStyle;
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCascade() {
		return cascade;
	}

	public void setCascade(String cascade) {
		this.cascade = cascade;
	}

	public String getRequired() {
		return String.valueOf(required);
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getExpandLevel() {
		return expandLevel;
	}

	public void setExpandLevel(String expandLevel) {
		this.expandLevel = expandLevel;
	}

	public String getCheckEnable() {
		return String.valueOf(checkEnable);
	}

	public void setCheckEnable(boolean checkEnable) {
		this.checkEnable = checkEnable;
	}

	public String getDropdown() {
		return String.valueOf(dropdown);
	}

	public void setDropdown(boolean dropdown) {
		this.dropdown = dropdown;
	}
	
}
