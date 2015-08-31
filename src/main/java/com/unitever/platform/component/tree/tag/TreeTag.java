package com.unitever.platform.component.tree.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.tree.bean.Tree;

public class TreeTag extends AbstractUCTag {
	private static final long serialVersionUID = -3366412089362155793L;
	private String id = "";
	private String nodes = "";
	private String checkValue = "";
	private String checkStyle = "checkbox";
	private boolean checkEnable = false;
	private boolean dropdown = false;
	private String cascade = "";
	private String expandLevel="";
	private String name = "";
	private String width = "";
	private boolean required = false;//是否必填
	private String placeholder = "";

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new Tree(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		Tree component_ = (Tree) component;
		component_.setId(id);
		component_.setNodes(nodes);
		component_.setCheckEnable(checkEnable);
		component_.setCheckValue(checkValue);
		component_.setCheckStyle(checkStyle);
		component_.setDropdown(dropdown);
		component_.setCascade(cascade);
		component_.setExpandLevel(expandLevel);
		component_.setWidth(width);
		component_.setName(name);
		component_.setRequired(required);
		component_.setPlaceholder(placeholder);
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

	public boolean isRequired() {
		return required;
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

	public boolean isCheckEnable() {
		return checkEnable;
	}

	public void setCheckEnable(boolean checkEnable) {
		this.checkEnable = checkEnable;
	}

	public boolean isDropdown() {
		return dropdown;
	}

	public void setDropdown(boolean dropdown) {
		this.dropdown = dropdown;
	}
	
}
