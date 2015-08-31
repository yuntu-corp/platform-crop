package com.unitever.platform.core.cache;

import javax.servlet.ServletRequestEvent;

import org.springframework.stereotype.Component;

import com.unitever.platform.core.web.listener.RequestContextMonitor;

/**
 * 控制缓存的初始化和销毁
 * 
 * @author tianyl
 * 
 */
@Component("sys_Cache4RequestMonitor")
public class Cache4RequestMonitor implements RequestContextMonitor {

	@Override
	public void init(ServletRequestEvent sre) {
		Cache4RequestUtil.init();
	}

	@Override
	public void destroyed(ServletRequestEvent sre) {
		Cache4RequestUtil.clear();
	}

}
