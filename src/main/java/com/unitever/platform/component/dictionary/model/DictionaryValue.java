package com.unitever.platform.component.dictionary.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * DictionaryValue entity. @author MyEclipse Persistence Tools
 */

public class DictionaryValue implements java.io.Serializable {
	private static final long serialVersionUID = -3574194813571047066L;
	private String id;
	private Dictionary dictionary;
	private DictionaryValue parent;
	private String parentId;
	private String code;
	private String value;
	private String valueI18n;
	private Integer num;
	private String description;
	private String enableFlag;
	private String editableFlag;
	private String systemFlag;
	private String ft;
	private String lt;
	private String fu;
	private String lu;
	private Set<DictionaryValue> children = new HashSet<DictionaryValue>(0);


	//--自定义属性
	private String enableFlagView;
	
	/** 启用禁用常量 */
	public static final String DISABLED = "0";
	public static final String ENABLE = "1";
	
	// Property accessors

	public String getEnableFlagView() {
		if (ENABLE.equals(this.enableFlag)) {
			return "启用";
		}
		return "禁用";
	}

	public DictionaryValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DictionaryValue(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueI18n() {
		return valueI18n;
	}

	public void setValueI18n(String valueI18n) {
		this.valueI18n = valueI18n;
	}

	public String getValueI18nName() {
		if(valueI18n==null || "".equals(valueI18n)){
			return value;
		}
		if(LocaleContextHolder.getLocale().getLanguage().equals("zh")){
			return value;
		}
		return valueI18n;
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public DictionaryValue getParent() {
		return parent;
	}

	public void setParent(DictionaryValue parent) {
		this.parent = parent;
	}

	public Set<DictionaryValue> getChildren() {
		return children;
	}

	public void setChildren(Set<DictionaryValue> children) {
		this.children = children;
	}

	public int getOrderNum(){
		return num!=null?num:0;
	}
	
	public boolean isDictParent() {
		if (StringUtils.isNotEmpty(this.getParentId())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Description : TODO(判断字典值是否启用)
	 */
	public boolean getEnabledBool() {
		if (ENABLE.equals(this.enableFlag)) {
			return true;
		}
		return false;
	}
}