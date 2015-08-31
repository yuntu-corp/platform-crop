package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.TextareaBean;

public class TextareaTag extends ValidateSupportTag {

	private static final long serialVersionUID = 8992086858428846221L;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new TextareaBean(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		TextareaBean bean = (TextareaBean) component;
		bean.setCols(cols);
		bean.setRows(rows);
		bean.setReadonly(readonly);
		bean.setMaxlength(maxlength);
		bean.setPlaceholder(placeholder);
	}

	protected String cols;
	protected String readonly;
	protected String rows;
	protected String maxlength;
	protected String placeholder;

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

}
