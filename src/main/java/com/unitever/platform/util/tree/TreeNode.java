package com.unitever.platform.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 沈涛
 * 2011-9-24 下午03:44:31
 */
public class TreeNode {
	private String id;
	private String name;//结点显示字段
	private String value;//结点属性值
	private boolean isParent = false; //是否为父节点，主要用于异步时该结点是否可展开
	private String parentId;//父结点id
	private String url;//跳转地址
	private String num;//排序序号
	private boolean open = false;
	private boolean nocheck = false;//是否显示选择图标
	private String icon;
	private String iconOpen;
	private String iconClose;
	private String iconSkin;
	private String target = "_self";//跳转方式，弹出窗口、当前页面，暂时无用

	public String getTarget() {
		return target;
	}

	private List<TreeNode> nodes = new ArrayList<TreeNode>(0);

	/**
	 * 在当前窗口中显示url
	 */
	public static final String TARGET_SELF = "_self";
	/**
	 * 在新窗口中显示url
	 */
	public static final String TARGET_BLANK = "_blank";

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

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

}
