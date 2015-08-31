package com.unitever.platform.component.dictionary.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.dictionary.bean.DictCheckBox;
import com.unitever.platform.component.dictionary.model.DictionaryValue;
import com.unitever.platform.component.dictionary.service.DictionaryValueService;
import com.unitever.platform.component.dictionary.vo.DictValueVO;
import com.unitever.platform.core.spring.SpringContextHolder;

public class DictCheckBoxTag extends AbstractUCTag {
	private static final long serialVersionUID = -6845849698760731800L;
	private String name;
	private String dictCode;
	private String checkValue;
	private String cols;
	private String required;
	private String other;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new DictCheckBox(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		DictCheckBox component_ = (DictCheckBox) component;
		component_.setName(name);
		component_.setDictCode(dictCode);
		component_.setCheckValue(checkValue);
		component_.setCols(cols);
		component_.setRequired(required);
		component_.setOther(other);
		List<DictValueVO> dictValueVOs = new ArrayList<DictValueVO>(0);
		DictionaryValueService dictValueService = SpringContextHolder.getBean(DictionaryValueService.class);
		Collection<DictionaryValue> list = dictValueService.findDictValueBy(this.getDictCode());
		for (DictionaryValue dictValue : list) {
			DictValueVO vo = new DictValueVO();
			vo.setId(dictValue.getId());
			vo.setCode(dictValue.getCode());
			vo.setValue(dictValue.getValueI18nName());
			dictValueVOs.add(vo);
		}
		component_.setDictValueVOs(dictValueVOs);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
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

}
