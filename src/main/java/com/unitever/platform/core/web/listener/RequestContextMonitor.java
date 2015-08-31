package com.unitever.platform.core.web.listener;

import javax.servlet.ServletRequestEvent;

public interface RequestContextMonitor {

	public void init(ServletRequestEvent evt);

	public void destroyed(ServletRequestEvent evt);
}
