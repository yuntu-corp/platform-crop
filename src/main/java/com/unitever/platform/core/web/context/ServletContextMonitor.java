package com.unitever.platform.core.web.context;

import javax.servlet.ServletContext;

/**
 * 用户服务器启动，销毁的回调。
 * 
 * @author tianyl
 * 
 */
public interface ServletContextMonitor {

	public void init(ServletContext context);

	public void destroyed(ServletContext context);

}
