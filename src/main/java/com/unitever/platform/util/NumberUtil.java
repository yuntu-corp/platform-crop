package com.unitever.platform.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	/**
	 * 判断是否是一位小数或整数
	 */
	public static boolean isDoubleNumber(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher isNum = pattern.matcher(str);
		boolean isInt = isNum.matches();
		if (isInt) {
			return true;
		} else {
			if (str.length() == 2 || str.indexOf(".") != str.length() - 2) {
				return false;
			} else {
				return pattern.matcher(str.replaceFirst("\\.", "")).matches();
			}
		}
	}

	/**
	 * 对double值四舍五入
	 * 
	 * @param value
	 *            要格式化的double值
	 * @param num
	 *            保留的小数后有效个数
	 * @return 格式化结果
	 */
	public static String formatDouble(Double value, int num) {
		String format = "#";
		if (num > 0) {
			format += ".";
		}
		for (int i = 0; i < num; i++) {
			format += "#";
		}
		DecimalFormat df = new DecimalFormat(format);
		df.setRoundingMode(RoundingMode.HALF_UP);
		String result = df.format(value);
		return result;
	}

}
