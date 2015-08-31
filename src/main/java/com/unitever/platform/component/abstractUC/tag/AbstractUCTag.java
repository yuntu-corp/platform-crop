package com.unitever.platform.component.abstractUC.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;
import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.util.UUID;

public abstract class AbstractUCTag extends BodyTagSupport implements DynamicAttributes {

	private static final long serialVersionUID = -8796908879472460629L;

	protected Map<String, Object> dynamicAttributes = new HashMap<String, Object>();

	protected Component component;

	@Override
	public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
		dynamicAttributes.put(localName, value);
	}

	public abstract Component getBean(HttpServletRequest request, HttpServletResponse response);

	public Component getComponent() {
		return component;
	}

	public int doEndTag() throws JspException {
		component.end(pageContext.getOut(), getBody());
		component = null;
		return EVAL_PAGE;
	}

	private String getBody() {
		if (bodyContent == null) {
			return "";
		} else {
			return bodyContent.getString().trim();
		}
	}

	public int doStartTag() throws JspException {
		component = getBean((HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse());
		populateParams();
		boolean evalBody = component.start(pageContext.getOut());

		if (evalBody) {
			return component.usesBody() ? EVAL_BODY_BUFFERED : EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}

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

	protected void populateParams() {
		AbstractUCBean bean = (AbstractUCBean) component;
		bean.setAccessKey(accessKey);
		bean.setCssClass(cssClass);
		bean.setCssStyle(cssStyle);
		bean.setDisabled(disabled);
		bean.setId(StringUtils.isBlank(id) ? UUID.getUUID() : id);
		bean.setName(name);
		bean.setOnblur(onblur);
		bean.setOnchange(onchange);
		bean.setOnclick(onclick);
		bean.setOnfocus(onfocus);
		bean.setOnkeydown(onkeydown);
		bean.setOnkeypress(onkeypress);
		bean.setOnkeyup(onkeyup);
		bean.setOnmousedown(onmousedown);
		bean.setOnmousemove(onmousemove);
		bean.setOnmouseout(onmouseout);
		bean.setOnmouseover(onmouseover);
		bean.setOnmouseup(onmouseup);
		bean.setOnselect(onselect);
		bean.setTabindex(tabindex);
		bean.setTitle(title);
		bean.setValue(value);
		bean.setDynamicAttributes(dynamicAttributes);
	}

	public Map<String, Object> getDynamicAttributes() {
		return dynamicAttributes;
	}

	public void setDynamicAttributes(Map<String, Object> dynamicAttributes) {
		this.dynamicAttributes = dynamicAttributes;
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

}
