package com.unitever.platform.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;

public class JsonUtil {

	/**
	 * 把json串转成对象
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param pojoClass
	 *            要转成的class，必须是public的
	 * @param map
	 *            集合类型中的元素类型， key为属性名称，value为类型
	 * @return
	 */
	public static <T> T json2JavaPojo(String jsonString, Class<T> pojoClass) {
		T pojo = JSON.parseObject(jsonString, pojoClass);
		return pojo;
	}

	/**
	 * 把json串转成Map
	 * 
	 * @param jsonString
	 * @return key和value对应json中的key和value
	 */
	public static Map<String, String> json2Map(String jsonString) {
		@SuppressWarnings("unchecked")
		Map<String, String> valueMap = JSON.parseObject(jsonString, Map.class);
		return valueMap;
	}

	/**
	 * 把json串转成对象集合
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static <T> List<T> json2List(String jsonString, Class<T> pojoClass) {
		List<T> list = JSON.parseArray(jsonString, pojoClass);
		return list;
	}

	/**
	 * 把java对象转换成json字符串，object中的集合和自定义类型不会转换
	 * 
	 * @param object
	 * @return
	 */
	public static String javaPojo2Json(Object object) {
		return javaPojo2Json(object, null);
	}

	/**
	 * 把java对象转换成json字符串，object中的集合和自定义类型不会转换
	 * 
	 * @param object
	 *            需要转为json的java pojo对象
	 * @param includeProperties
	 *            包含的需要附加转换的属性，一般是自定义类型
	 * @return
	 */
	public static String javaPojo2Json(Object object, List<String> includeProperties) {
		if (includeProperties == null) {
			includeProperties = new ArrayList<String>();
		}
		JSONObject json = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				String key = f.getName();
				f.setAccessible(true);
				if (Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
					map.put(key, f.get(object));
					continue;
				}
				if (isBaseType(f.getType()) || f.getType() == String.class) {
					map.put(key, invokeGetterMethod(object, key));
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		json = new JSONObject(map);
		for (String prop : includeProperties) {
			fillValue(json, prop, object);
		}
		return json.toString();
	}

	public static String collection2Json(Collection<? extends Object> collections) {
		return collection2Json(collections, null);
	}

	/**
	 * 将java集合转化为json字符串，集合元素类型可以是自定义类或Map
	 * 
	 * @param collections
	 *            需要转为json的集合，集合元素只能是普通的pojo类，不能是集合、map、数组
	 * @param includeProperties
	 *            集合中元素需要转换的属性，默认只转换8种基本类型和String类型，集合类型不转换
	 * @return
	 */
	public static String collection2Json(Collection<? extends Object> collections, List<String> includeProperties) {
		if (includeProperties == null) {
			includeProperties = new ArrayList<String>();
		}
		JSONArray result = new JSONArray();
		if (collections.size() == 0) {
			return result.toString();
		} else {
			Object obj = collections.iterator().next();
			if (obj instanceof Map) {// Map
				return JSONArray.toJSONString(collections);
			}
		}
		for (Object object : collections) {
			if (object == null) {
				continue;
			}
			JSONObject json = new JSONObject();
			Map<String, Object> map = new HashMap<String, Object>();
			@SuppressWarnings("rawtypes")
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field f : fields) {
				try {
					String key = f.getName();
					f.setAccessible(true);
					if (Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
						map.put(key, f.get(object));
						continue;
					}
					if (isBaseType(f.getType()) || f.getType() == String.class) {
						map.put(key, invokeGetterMethod(object, key));
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			json = new JSONObject(map);
			for (String prop : includeProperties) {
				fillValue(json, prop, object);
			}
			result.add(json);
		}
		return result.toString();
	}

	private static void fillValue(JSONObject json, String prop, Object object) {
		if (object == null) {
			return;
		}
		if (prop.contains(".")) {
			String key = prop.substring(0, prop.indexOf("."));
			if (!json.containsKey(key)) {
				JSONObject jsonObj = new JSONObject();
				Object value = invokeGetterMethod(object, key);
				fillValue(jsonObj, prop.replaceFirst(key + ".", ""), value);
				json.put(key, jsonObj);
			} else {
				JSONObject jsonObj = json.getJSONObject(key);
				Object value = invokeGetterMethod(object, key);
				fillValue(jsonObj, prop.replaceFirst(key + ".", ""), value);
				json.remove(key);
				json.put(key, jsonObj);
			}
		} else {
			Object value = invokeGetterMethod(object, prop);
			json.put(prop, value);
		}
	}

	private static Object invokeGetterMethod(Object target, String propertyName) {
		String getterMethodName = "get" + StringUtils.capitalize(propertyName);
		Method m = getGetterMethod(getterMethodName, target);
		if (m == null) {
			m = getGetterMethod("is" + StringUtils.capitalize(propertyName), target);
		}
		if (m == null) {
			return null;
		}
		try {
			return m.invoke(target);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Method getGetterMethod(String getterMethodName, Object target) {
		Method[] methods = target.getClass().getDeclaredMethods();
		for (Method m : methods) {
			if (m.getParameterTypes() == null || m.getParameterTypes().length == 0) {
				if (m.getName().endsWith(getterMethodName)) {
					return m;
				}
			}
		}
		return null;
	}

	private static boolean isBaseType(Class<?> type) {
		if (type.isPrimitive()) {// int char 类型
			return true;
		}
		if (type == Integer.class || type == Boolean.class || type == Character.class || type == Byte.class || type == Short.class || type == Long.class || type == Float.class || type == Double.class) {
			return true;
		}
		return false;
	}

	/**
	 * 将Map转换成json字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String map2Json(Map<String, ?> map) {
		@SuppressWarnings("unchecked")
		Map<String, Object> map2 = (Map<String, Object>) map;
		return new JSONObject(map2).toJSONString();
	}

	/**
	 * 把简单的json生成map，map保证顺序
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> parseSimpleJsonStrToMap(String str) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// fastjson不清楚如何保证顺序，先用mongo的实现
		Object obj = com.mongodb.util.JSON.parse(str);
		BasicDBObject obj2 = (BasicDBObject) obj;
		for (String key : obj2.keySet()) {
			result.put(key, obj2.getString(key));
		}
		return result;
	}

}
