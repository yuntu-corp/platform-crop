package com.unitever.platform.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * map相关的工具类
 * 
 * @author tianyl
 * 
 */
public class MapUtil {

	/**
	 * 在Map&ltK,List&ltT>>中获取List&ltT>集合，如果集合不存在（null）则会创建ArrayList&ltT>并放在Map&ltK,List&ltT>>中
	 * 
	 * @param map
	 * @param key
	 * @return 集合
	 */
	public static <T, K> List<T> getListInMap(Map<K, List<T>> map, K key) {
		List<T> list = map.get(key);
		if (list == null) {
			list = new ArrayList<T>();
			map.put(key, list);
		}
		return list;
	}

	/**
	 * 在Map&ltK1, Map&ltK2, T>>中获取Map&ltK2, T>，如果不存在（null）则会创建HashMap&ltK2, T>并放入Map&ltK1, Map&ltK2, T>>中
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, T>>
	 * @param key
	 *            K1
	 * @return Map&ltK2, T>
	 */
	public static <T, K1, K2> Map<K2, T> getMapInMap(Map<K1, Map<K2, T>> map, K1 key) {
		Map<K2, T> mapTemp = map.get(key);
		if (mapTemp == null) {
			mapTemp = new HashMap<K2, T>();
			map.put(key, mapTemp);
		}
		return mapTemp;
	}

	/**
	 * 在Map&ltK1, Map&ltK2, Map&ltK3, T>>>中获取Map&ltK2, Map&ltK3, T>>，如果不存在（null）则会创建HashMap&ltK2, Map&ltK3, T>>并放入Map&ltK1, Map&ltK2, Map&ltK3, T>>>中
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, Map&ltK3, T>>>
	 * @param key
	 *            K1
	 * @return Map&ltK2, Map&ltK3, T>>
	 */
	public static <T, K1, K2, K3> Map<K2, Map<K3, T>> getMapMapInMap(Map<K1, Map<K2, Map<K3, T>>> map, K1 key) {
		Map<K2, Map<K3, T>> result = map.get(key);
		if (result == null) {
			result = new HashMap<K2, Map<K3, T>>();
			map.put(key, result);
		}
		return result;
	}

	/**
	 * 在Map&ltK1, Map&ltK2, Map&ltK3, Map&ltK4, T>>>>中获取Map&ltK2, Map&ltK3, Map&ltK4, T>>>，如果不存在（null）则会创建HashMap&ltK2, Map&ltK3, Map&ltK4, T>>>并放入Map&ltK1, Map&ltK2, Map&ltK3, Map&ltK4, T>>>>中
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, Map&ltK3, Map&ltK4, T>>>>
	 * @param key
	 *            K1
	 * @return Map&ltK2, Map&ltK3, Map&ltK4, T>>>
	 */
	public static <T, K1, K2, K3, K4> Map<K2, Map<K3, Map<K4, T>>> getMapMapMapInMap(Map<K1, Map<K2, Map<K3, Map<K4, T>>>> map, K1 key) {
		Map<K2, Map<K3, Map<K4, T>>> result = map.get(key);
		if (result == null) {
			result = new HashMap<K2, Map<K3, Map<K4, T>>>();
			map.put(key, result);
		}
		return result;
	}

	/**
	 * 在Map&ltK, String>中获取Value，若Value为null则返回空字符串（""）
	 * 
	 * @param map
	 *            Map&ltK, String>
	 * @param key
	 *            K
	 * @return
	 */
	public static <K> String getValue(Map<K, String> map, K key) {
		return map.containsKey(key) ? map.get(key) : "";
	}

	/**
	 * 在Map&ltK1, Map&ltK2, String>>中获取Value，若Value为null则返回空字符串（""）
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, String>>
	 * @param key1
	 *            K1
	 * @param key2
	 *            K2
	 * @return
	 */
	public static <K1, K2> String getValue(Map<K1, Map<K2, String>> map, K1 key1, K2 key2) {
		if (map.containsKey(key1) && map.get(key1) != null) {
			return getValue(map.get(key1), key2);
		}
		return "";
	}

	/**
	 * 在Map&ltK1, Map&ltK2, Map&ltK3, String>>>中获取Value，若Value为null则返回空字符串（""）
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, Map&ltK3, String>>>
	 * @param key1
	 *            K1
	 * @param key2
	 *            K2
	 * @param key3
	 *            K3
	 * @return
	 */
	public static <K1, K2, K3> String getValue(Map<K1, Map<K2, Map<K3, String>>> map, K1 key1, K2 key2, K3 key3) {
		if (map.containsKey(key1) && map.get(key1) != null) {
			return getValue(map.get(key1), key2, key3);
		}
		return "";
	}

	/**
	 * 在Map&ltK1, Map&ltK2, Map&ltK3, Map&ltK4, String>>>>中获取Value，若Value为null则返回空字符串（""）
	 * 
	 * @param map
	 *            Map&ltK1, Map&ltK2, Map&ltK3, Map&ltK4, String>>>>
	 * @param key1
	 *            K1
	 * @param key2
	 *            K2
	 * @param key3
	 *            K3
	 * @param key4
	 *            K4
	 * @return
	 */
	public static <K1, K2, K3, K4> String getValue(Map<K1, Map<K2, Map<K3, Map<K4, String>>>> map, K1 key1, K2 key2, K3 key3, K4 key4) {
		if (map.containsKey(key1) && map.get(key1) != null) {
			return getValue(map.get(key1), key2, key3, key4);
		}
		return "";
	}
}
