package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.baseform.bean.HiddenBean;

public class HiddenTag extends AbstractUCTag {

	private static final long serialVersionUID = 3326005165044875649L;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new HiddenBean(request, response);
	}

}
