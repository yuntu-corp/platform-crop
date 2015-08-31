package com.unitever.platform.core.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于request的缓存工具类，没有request缓存会失败
 * 
 * @author tianyl
 * 
 */
public class Cache4RequestUtil {

	private final static Map<String, Map<String, Object>> CACHE_MAP = Collections.synchronizedMap(new HashMap<String, Map<String, Object>>());;

	/**
	 * 添加缓存
	 * 
	 * @param key
	 * @param value
	 */
	public static void put(String key, Object value) {
		String threadId = Thread.currentThread().getId() + "";
		if (CACHE_MAP.get(threadId) != null) {
			Map<String, Object> map = CACHE_MAP.get(threadId);
			map.put(key, value);
		}
	}

	/**
	 * 移除缓存
	 * 
	 * @param key
	 */
	public static void remove(String key) {
		String threadId = Thread.currentThread().getId() + "";
		if (CACHE_MAP.get(threadId) != null) {
			Map<String, Object> map = CACHE_MAP.get(threadId);
			map.remove(key);
		}
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		String threadId = Thread.currentThread().getId() + "";
		if (CACHE_MAP.get(threadId) != null) {
			return (T) (CACHE_MAP.get(threadId).get(key));
		}
		return null;
	}

	/**
	 * 初始化缓存
	 * 
	 * @param threadId
	 */
	public static void init() {
		CACHE_MAP.put(Thread.currentThread().getId() + "", new HashMap<String, Object>());
	}

	/**
	 * 清空缓存
	 * 
	 */
	public static void clear() {
		CACHE_MAP.remove(Thread.currentThread().getId() + "");
	}

}
