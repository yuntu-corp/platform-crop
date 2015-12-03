package com.unitever.module.wechat.manager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TokenManager token 自动刷新
 * @author 邓文杰
 *
 */
public class SessionManager{

	private static Map<String,Object> sessionMap = new LinkedHashMap<String,Object>();

	public static void addSession(String key,Object sessionObject){
		sessionMap.put(key, sessionObject);
	}
	
	public static Object getSession(String key){
		return sessionMap.get(key);
	}

}
