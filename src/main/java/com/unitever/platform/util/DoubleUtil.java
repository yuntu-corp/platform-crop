package com.unitever.platform.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

public class DoubleUtil {

	/** 进制为10进制 */
	private static final int DEF_DIV_SCALE = 10;

	public static void main(String[] args) {
		System.out.println(getIntNum(1222.125153));
		System.out.println(changeDecimal(12.125153, 4));
	}

	/**
	 * 得到整数位数
	 * 
	 * @param value
	 * @return
	 */
	public static int getIntNum(Double value) {
		DecimalFormat df = new DecimalFormat("#");
		String str = df.format(value).replaceAll("-", "");
		return str.length();
	}

	/**
	 * 四舍五入
	 * 
	 * @param value
	 *            值
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static double changeDecimal(double value, int num) {
		BigDecimal b = new BigDecimal(value);
		double v = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();// 表明四舍五入，保留两位小数
		return v;
	}

	/** 得到不以科学计数数据格式的字符串 */
	public static String toDecimalString(String str, int decimal) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		return DoubleUtil.toDecimalString(Double.valueOf(str.trim()), decimal);
	}

	/**
	 * 得到不以科学计数数据格式的字符串 四舍五入
	 * 
	 * @param num
	 * @param decimal
	 * @return
	 */
	public static String toDecimalString(Double num, int decimal) {
		if (num == null) {
			return "";
		}
		String str = "" + num;
		String formatStr = "0.";
		for (int i = 0; i < decimal; i++) {
			formatStr += "0";
		}
		DecimalFormat df = new DecimalFormat(formatStr);
		df.setRoundingMode(RoundingMode.HALF_UP);// 四舍五入
		try {
			str = "" + df.format(num);

		} catch (Exception e) {

		}
		return str;
	}

	/**
	 * 得到以,号分隔的计数数据格式的字符串 四舍五入
	 * 
	 * @param num
	 * @param decimal
	 * @return
	 */
	public static String toSplitDecimalString(Double num, int decimal) {
		if (num == null) {
			return "";
		}
		String str = "" + num;
		String formatStr = "###,###.";
		for (int i = 0; i < decimal; i++) {
			formatStr += "0";
		}
		DecimalFormat df = new DecimalFormat(formatStr);
		df.setRoundingMode(RoundingMode.HALF_UP);// 四舍五入
		try {
			str = "" + df.format(num);
		} catch (Exception e) {

		}
		return str;
	}

	/** 得到以,号分隔的计数数据格式的字符串 */
	public static String toSplitDecimalString(String str, int decimal) {
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		return DoubleUtil.toSplitDecimalString(Double.valueOf(str.trim()), decimal);
	}

	/**
	 * 两个Double数相加
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两个Double数相加
	 * 
	 * @param v1
	 * @param v2
	 * @param num
	 *            小数点后保留几位
	 * @return
	 */
	public static Double add(Double v1, Double v2, int num) {
		return changeDecimal(add(v1, v2), num);
	}

	/**
	 * 两个Double数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 两个double相减,并把结果设置精度
	 * 
	 * @param v1
	 * @param v2
	 * @param num
	 *            小数点后保留几位
	 * @return
	 */
	public static Double sub(Double v1, Double v2, int num) {
		return changeDecimal(sub(v1, v2), num);
	}

	/**
	 * 两个Double数相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 两个double相乘,并把结果设置精度
	 * 
	 * @param v1
	 * @param v2
	 * @param num
	 *            小数点后保留几位
	 * @return
	 */
	public static Double mul(Double v1, Double v2, int num) {
		return changeDecimal(mul(v1, v2), num);
	}

	/**
	 * 两个Double数相除
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 两个Double数相除，并保留scale位小数
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 *            小数点后保留几位
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
