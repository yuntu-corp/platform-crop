package com.unitever.platform.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 */
public class DateUtil {

	/**
	 * 根据自定义的格式获取日期字符串
	 * 
	 * @param customFormat
	 *            自定义的格式
	 * @return
	 */
	public static String getCurrDateString(String customFormat) {
		return new SimpleDateFormat(customFormat).format(new Date());
	}

	/**
	 * 得到当前日期字符串，格式为 yyyy-MM-dd
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 按日期得到字符串，格式为 yyyy-MM-dd
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 得到当前日期字符串，格式为 yyyy年MM月dd日
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateStringChina() {
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}

	/**
	 * 按日期得到字符串，格式为 yyyy年MM月dd日
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getDateStringChina(Date date) {
		return new SimpleDateFormat("yyyy年MM月dd日").format(date);
	}

	/**
	 * 得到当前精确到分的日期字符串，格式为 yyyy-MM-dd HH:mm
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateMinString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}

	/**
	 * 按日期得到精确到秒的字符串，格式为 yyyy-MM-dd HH:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateMinString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	/**
	 * 得到当前精确到秒的日期字符串，格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateSecondString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 按日期得到精确到秒的日期字符串，格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateSecondString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 得到当前精确到毫秒的日期字符串，格式为yyyy-MM-dd HH:mm:ss:SSS
	 * 
	 * @return 当前日期的字符串
	 */
	public static String getCurrDateMillisecondString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
	}

	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return
	 */
	public static Date getDate(String dateStr) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		}
		return date;
	}

	public static Date getDate(String dateStr, String customFormat) {
		if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(customFormat)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat(customFormat);
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		}
		return date;
	}

	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDateMin(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			throw ExceptionUtil.convertExceptionToUnchecked(e);
		}
		return date;
	}

	/**
	 * 按字符串得到日期对象，格式为 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static Date getDateSecond(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到下个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextMonthFirstDay(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.MONTH, 1);
		calender.set(Calendar.DAY_OF_MONTH, 1);
		Date d = calender.getTime();
		return d;
	}

	/**
	 * 是否过期
	 * 
	 * @param dateString
	 *            开始日期字符串，格式 yyyy-MM-dd HH:mm
	 * @param limit
	 *            有效天数
	 * @return
	 */
	public static boolean isTimeOut(String dateString, int limit) {
		Date dueDate = getDateMin(dateString);
		Date nowDate = getDateMin(DateUtil.getCurrDateMinString());
		if ((nowDate.getTime() - dueDate.getTime()) < limit * 24 * 60 * 60 * 1000) {
			return false;
		}
		return true;
	}

	/**
	 * 比较时间大小
	 * 
	 * @param d1
	 *            格式为 yyyy-MM-dd HH:mm:ss
	 * @param d2
	 *            格式为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean compareTime(String d1, String d2) {
		Date date1 = getDateSecond(d1);
		Date date2 = getDateSecond(d2);
		if (date1.getTime() - date2.getTime() >= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 比较日期大小
	 * 
	 * @param d1
	 *            格式为 yyyy-MM-dd
	 * @param d2
	 *            格式为 yyyy-MM-dd
	 * @return
	 */
	public static boolean compareDay(String d1, String d2) {
		Date date1 = getDate(d1);
		Date date2 = getDate(d2);
		if (date1.getTime() - date2.getTime() >= 24 * 60 * 60 * 1000) {
			return true;
		}
		return false;
	}

	/**
	 * 根据指定的年、月、日返回当前是星期几。
	 * 
	 * @param date
	 *            (yyyy-mm-dd)
	 * @return 返回一个代表当期日期是星期几的数字。0表示星期天、1表示星期一、6表示星期六。
	 */
	public static int getDayOfWeek(String dateStr) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getDate(dateStr));
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return w;
	}

	/**
	 * 得到星期显示值
	 * 
	 * @param dateStr
	 *            日期字符串，格式yyyy-MM-dd
	 * @return
	 */
	public static String getWeekStr(String dateStr) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weekDays[getDayOfWeek(dateStr)];
	}

	/**
	 * 求两个日期相差天数
	 * 
	 * @param startDateStr
	 *            开始日期字符串，格式yyyy-MM-dd
	 * @param endDateStr
	 *            结束日期字符串，格式yyyy-MM-dd
	 * @return 天数，整数
	 */
	public static long getIntervalDays(String startDateStr, String endDateStr) {
		Date d1 = getDate(startDateStr);
		Date d2 = getDate(endDateStr);
		return (d2.getTime() - d1.getTime()) / (3600 * 24 * 1000);
	}

	/***************************************** wbf补充 start ************************************************/

	/**
	 * 
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 * 
	 *            给定的日期对象
	 * 
	 * @param amount
	 * 
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * 
	 * @param format
	 * 
	 *            输出格式.
	 * 
	 * @return Date 加上一定天数以后的Date对象.
	 * 
	 */
	public static String getFormatDateAdd(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return getDateString(cal.getTime());
	}

	/**
	 * 
	 * 返回指定日期的前一天。<br>
	 * 
	 * @param sourceDate
	 * 
	 */
	public static String getYestoday(String sourceDate) {
		return getFormatDateAdd(getDate(sourceDate), -1);
	}

	/**
	 * 返回指定日期的后一天。<br>
	 * 
	 * @param sourceDate
	 * 
	 */
	public static String getFormatDateTommorrow(String sourceDate) {
		return getFormatDateAdd(getDate(sourceDate), 1);
	}

	/***************************************** wbf补充 end ************************************************/

	public static void main(String[] args) {
		System.out.println(getWeekStr("2012-11-11"));
	}
}
