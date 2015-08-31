package com.unitever.platform.component.abstractUC.bean;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;

public class Component {

	public Component() {

	}

	public boolean start(Writer writer) {
		return true;
	}

	public boolean end(Writer writer, String body) throws JspException {
		try {
			writer.write(body);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		}
		return false;
	}

	public boolean usesBody() {
		return false;
	}

}
