package com.unitever.platform.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 字符串帮助类
 */
public class StringUtil {

	/**
	 * 填充位置枚举类型
	 * 
	 * @author Administrator
	 * 
	 */
	public enum FillLocation {
		BEFORE, AFTER
	}

	private static final String DEFAULT_PATH_SEPARATOR = ",";

	/**
	 * 将一个中间带逗号分隔符的字符串转换为ArrayList对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @return List对象
	 */
	public static List<String> strToArrayList(String str) {
		return strToArrayListManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为ArrayList对象
	 * 
	 * @param str
	 *            待转换的字符串对象
	 * @param separator
	 *            字符型分隔符
	 * @return List对象
	 */
	public static List<String> strToArrayList(String str, String separator) {
		return strToArrayListManager(str, separator);
	}

	private static List<String> strToArrayListManager(String str, String separator) {
		StringTokenizer strTokens = new StringTokenizer(str, separator);
		List<String> list = new ArrayList<String>();
		while (strTokens.hasMoreTokens()) {
			list.add(strTokens.nextToken().trim());
		}
		return list;
	}

	/**
	 * 将一个中间带逗号分隔符的字符串转换为字符型数组对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str) {
		return strToStrArrayManager(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串对象按给定的分隔符separator转象为字符型数组对象
	 * 
	 * @param str
	 *            待转换的符串对象
	 * @param separator
	 *            字符型分隔符
	 * @return 字符型数组
	 */
	public static String[] strToStrArray(String str, String separator) {
		return strToStrArrayManager(str, separator);
	}

	private static String[] strToStrArrayManager(String str, String separator) {
		StringTokenizer strTokens = new StringTokenizer(str, separator);
		String[] strArray = new String[strTokens.countTokens()];
		int i = 0;
		while (strTokens.hasMoreTokens()) {
			strArray[i] = strTokens.nextToken().trim();
			i++;
		}
		return strArray;
	}

	/**
	 * 将字符串转为set集合，使用,分割
	 * 
	 * @param str
	 * @return
	 */
	public static Set<String> strToSet(String str) {
		return strToSet(str, DEFAULT_PATH_SEPARATOR);
	}

	/**
	 * 将字符串转为set集合，
	 * 
	 * @param str
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static Set<String> strToSet(String str, String separator) {
		String[] values = strToStrArray(str, separator);
		Set<String> result = new LinkedHashSet<String>();
		for (int i = 0; i < values.length; i++) {
			result.add(values[i]);
		}
		return result;
	}

	/**
	 * 将一个数组，用逗号分隔变为一个字符串
	 * 
	 * @param array
	 * @return
	 */
	public static String arrayToStr(Object[] array) {
		if (array == null || array.length < 0) {
			return null;
		}
		String str = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				str += ",";
			}
			str += array[i].toString();
		}
		return str;
	}

	/**
	 * 使用指定字符串填充已有字符串
	 * 
	 * @param inStr
	 *            待填充字符串
	 * @param fill
	 *            填充字符串
	 * @param length
	 *            填充后的长度
	 * @param location
	 *            填充方向
	 * @return
	 */
	public static String fillStr(String inStr, String fill, int length, FillLocation location) {
		if (inStr == null || inStr.length() > length || inStr.length() == 0) {
			return inStr;
		}
		StringBuffer tempStr = new StringBuffer("");
		for (int i = 0; i < length - inStr.length(); i++) {
			tempStr.append(fill);
		}
		if (location == FillLocation.BEFORE) {
			return new String(tempStr.toString() + inStr);
		} else {
			return new String(inStr + tempStr.toString());
		}
	}

}
