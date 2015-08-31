package com.unitever.platform.util;

import java.lang.reflect.Array;

/**
 * 数组工具类
 * 
 * @author Sheny on 2011-10-19
 */
public class ArrayUtil {

	/**
	 * 为对象数组添加新的对象
	 * 
	 * @param oldArray
	 * @param newObject
	 * @return
	 */
	public static Object[] addToArray(Object[] oldArray, Object newObject) {
		int length = oldArray.length;
		Object[] newArray = (Object[]) Array.newInstance(oldArray.getClass().getComponentType(), length + 1);
		System.arraycopy(oldArray, 0, newArray, 0, length);
		newArray[length] = newObject;
		return newArray;
	}

	/**
	 * 为对象数组添加另一个对象数组
	 * 
	 * @param oldArray
	 * @param newElements
	 * @return
	 */
	public static Object[] addToArray(Object[] oldArray, Object[] newElements) {
		int length = oldArray.length;
		int newLength = newElements.length;
		if (newLength == 0)
			return oldArray;
		Object[] newArray = (Object[]) Array.newInstance(oldArray.getClass().getComponentType(), length + newLength);
		System.arraycopy(oldArray, 0, newArray, 0, length);
		for (int i = 0; i < newLength; i++) {
			newArray[length + i] = newElements[i];
		}
		return newArray;
	}

	/**
	 * 判断一个元素是否在数组中
	 * 
	 * @param array
	 *            数组
	 * @param element
	 *            元素
	 * @return
	 */
	public static boolean isInArray(Object[] array, Object element) {
		if (array == null)
			return false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element)
				return true;
			if (array[i].equals(element))
				return true;
		}
		return false;
	}

	/**
	 * 把数组转为字符串，使用,分割
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String arrayToString(Object[] array) {
		if (array == null)
			return null;
		StringBuffer sb = new StringBuffer(array.length * 8);
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(array[i].toString());
		}
		return sb.toString();
	}

}
