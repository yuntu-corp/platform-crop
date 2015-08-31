package com.unitever.platform.component.abstractUC.bean;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import com.unitever.platform.core.web.template.FreemarkerTemplateEngine;

import freemarker.template.TemplateException;

public abstract class AbstractUCBean extends Component {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected Map<String, Object> dynamicAttributes = new HashMap<String, Object>();

	// base properties
	protected String id;
	protected String name;
	protected String cssStyle;
	protected String cssClass;
	protected String value;
	protected String title;
	protected String tabindex;
	protected String accessKey;
	protected String disabled;

	// base events
	protected String onclick;
	protected String onmousedown;
	protected String onmouseup;
	protected String onmouseover;
	protected String onmousemove;
	protected String onmouseout;
	protected String onfocus;
	protected String onblur;
	protected String onkeypress;
	protected String onkeydown;
	protected String onkeyup;
	protected String onselect;
	protected String onchange;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
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

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTabindex() {
		return tabindex;
	}

	public void setTabindex(String tabindex) {
		this.tabindex = tabindex;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Map<String, Object> getDynamicAttributes() {
		return dynamicAttributes;
	}

	public void setDynamicAttributes(Map<String, Object> dynamicAttributes) {
		this.dynamicAttributes = dynamicAttributes;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOnmousedown() {
		return onmousedown;
	}

	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	public String getOnmouseup() {
		return onmouseup;
	}

	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	public String getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmousemove() {
		return onmousemove;
	}

	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	public String getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnselect() {
		return onselect;
	}

	public void setOnselect(String onselect) {
		this.onselect = onselect;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public AbstractUCBean(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public boolean end(Writer writer, String body) throws JspException {
		super.end(writer, body);
		mergeTemplate(writer, getTemplate());
		return false;
	}

	protected void mergeTemplate(Writer writer, String template) throws JspException {
		if (engine == null) {
			engine = new FreemarkerTemplateEngine();
		}
		try {
			engine.renderTemplate("/template/uc", template, writer, this);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		} catch (TemplateException e) {
			e.printStackTrace();
			throw new JspException(e);
		}
	}

	private static FreemarkerTemplateEngine engine = null;

	protected abstract String getTemplate();
}
