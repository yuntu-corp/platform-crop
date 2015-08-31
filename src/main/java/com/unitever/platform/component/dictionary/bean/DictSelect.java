package com.unitever.platform.component.dictionary.bean;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;
import com.unitever.platform.component.dictionary.vo.DictValueVO;


public class DictSelect extends AbstractUCBean {
	final public static String TEMPLATE = "dictSelect";
	private String dictCode;
	private String checkValue;
	private String headerKey;
	private String headerValue;
	private String required;
	private String other;
	private List<DictValueVO> dictValueVOs;

	public DictSelect(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public List<DictValueVO> getDictValueVOs() {
		return dictValueVOs;
	}

	public void setDictValueVOs(List<DictValueVO> dictValueVOs) {
		this.dictValueVOs = dictValueVOs;
	}
	
	
}
