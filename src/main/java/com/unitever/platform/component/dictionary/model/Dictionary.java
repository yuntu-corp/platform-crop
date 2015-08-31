package com.unitever.platform.component.dictionary.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dictionary entity. @author MyEclipse Persistence Tools
 */

public class Dictionary implements java.io.Serializable {
	private static final long serialVersionUID = -4171600031472030044L;
	private String id;
	private String name;
	private String code;
	private String description;
	private String kind;
	private String enableFlag;
	private String editableFlag;
	private String systemFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;

	//------------------字典值可编辑标志
	/** 字典值不可编辑 */
	public static final String DICT_CANNOTEDIT = "0";
	/** 字典值可编辑 */
	public static final String DICT_CANEDIT = "1";

	/** 字典类型 */
	public static final String DICT_KIND_GENERAL = "0";// --普通字典
	public static final String DICT_KIND_TREE = "1"; // --树型字典

	/**
	 * @Description : TODO(得到字典类型Map)
	 * @author : wangdawei
	 */
	public static final Map<String, String> getDictKindMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(DICT_KIND_GENERAL, "普通字典");
		map.put(DICT_KIND_TREE, "树型字典");
		return map;
	}

	//--自定义属性
	private String editableFlagView;;
	
	
	public String getEditableFlagView() {
		if(this.editableFlag.equals(DICT_CANEDIT)){
			return "是";
		}
		return "否";
	}


	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dictionary(String id) {
		super();
		this.id = id;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getEnableFlag() {
		return this.enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getEditableFlag() {
		return this.editableFlag;
	}

	public void setEditableFlag(String editableFlag) {
		this.editableFlag = editableFlag;
	}

	public String getSystemFlag() {
		return this.systemFlag;
	}

	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}

	public String getFt() {
		return this.ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public String getLt() {
		return this.lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getFu() {
		return this.fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getLu() {
		return this.lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	/**
	 * @Description : TODO(得到字典值可编辑标志Map)
	 * @author : wangdawei
	 */
	public static final Map<String, String> getCanEditFlagMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(DICT_CANEDIT, "可编辑");
		map.put(DICT_CANNOTEDIT, "不可编辑");
		return map;
	}

	/**
	 * 字典是否允许用户编辑
	 */
	public boolean isCanEdit() {
		return DICT_CANEDIT.equals(this.editableFlag);
	}
	/**
	 * 普通字典?
	 */
	public boolean isKindGeneral() {
		return kind.equals(DICT_KIND_GENERAL);
	}
	/**
	 * 树型字典?
	 */
	public boolean isKindTree() {
		return kind.equals(DICT_KIND_TREE);
	}
}