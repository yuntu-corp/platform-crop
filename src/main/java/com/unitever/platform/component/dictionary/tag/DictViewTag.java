package com.unitever.platform.component.dictionary.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.unitever.platform.component.dictionary.helper.DictionaryHelper;
import com.unitever.platform.util.ExceptionUtil;


/**
 * 数据字典：显示值标签
 * @author : wangdawei E-mail:wdw917@yahoo.cn
 */
public class DictViewTag extends BodyTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3476889082234802937L;

	/**
	 * 
	 */

	private String dictCode;

	private String valueCode;

	private boolean isFullPath;

	public String getDictCode() {
		return dictCode;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public void setValueCode(String selectName) {
		this.valueCode = selectName;
	}

	@Override
	public int doStartTag() throws JspException {

		// TODO Auto-generated method stub
		return EVAL_BODY_BUFFERED;

	}

	@Override
	public int doEndTag() throws JspException {
		String str = DictionaryHelper.getDictValue(dictCode, valueCode, isFullPath);
		JspWriter out = pageContext.getOut();
		try {

			out.print(str);
		} catch (IOException e) {

			throw ExceptionUtil.convertExceptionToUnchecked(e);
		}

		return EVAL_PAGE;
	}

	public void setIsFullPath(String isFullPath) {
		if ("true".equals(isFullPath)) {
			this.isFullPath = true;
		} else {
			this.isFullPath = false;
		}
	}
}
