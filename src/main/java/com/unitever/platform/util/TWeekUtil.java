package com.unitever.platform.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 教学周帮助类
 * 
 * @author shent
 * 
 */
public class TWeekUtil {

	/**
	 * 计算以某天开始的第几周星期几是几月几日
	 * 
	 * @param startDate
	 *            开始日期
	 * @param weekNum
	 *            第几周
	 * @param dayOfWeek
	 *            星期几 周日传7
	 * @return
	 */
	public static String getDateWithTWeek(String startDate, Integer weekNum, Integer dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(DateUtil.getDate(startDate));
		c.add(Calendar.DATE, (weekNum - 1) * 7);
		c.set(Calendar.DAY_OF_WEEK, (dayOfWeek + 1) % 7);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		return sf2.format(c.getTime());
	}

	/**
	 * 获取某天是以哪天为开始的第几周星期几
	 * 
	 * @param startDate
	 *            开始日期
	 * @param searchDate
	 *            要查询的日期
	 * @return array[0] 第几周， 如果开始日期大于要查询的日期，则返回负数周 array[1] 星期几， 如果开始日期大于要查询的日期，则正常返回星期几
	 */
	public static Integer[] getTWeekWithDate(String startDate, String searchDate) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(DateUtil.getDate(startDate));
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		long nd = 1000 * 24 * 60 * 60 * 7;
		Integer[] tweek = new Integer[2];
		long spaceTime = DateUtil.getDate(searchDate).getTime() - c.getTime().getTime();
		Integer weekNum = Integer.parseInt(String.valueOf(spaceTime / nd));
		c.setTime(DateUtil.getDate(searchDate));
		if (spaceTime < 0) {
			tweek[0] = weekNum - 1;
		} else {
			tweek[0] = weekNum + 1;
		}
		Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0)
			tweek[1] = 7;
		else
			tweek[1] = dayOfWeek;
		return tweek;
	}

	/**
	 * 获取日期范围内(绝对值)有多少周
	 * 
	 * @desc 例如：2012-11-06(周二)到2012-11-19(周一)范围内，有3周
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getWeekNumWithDateScope(String startDate, String endDate) {
		int result = 0;
		Date sdate = DateUtil.getDate(startDate);
		long LEN = Math.abs(DateUtil.getIntervalDays(startDate, endDate));// 间隔多少天
		// if (LEN < 0) return 0;
		int dayOfWeek = 0;
		for (int i = 0; i <= LEN; i++) {
			// 遍历开始日期后的每一天
			String dayAfterStartDate = DateUtil.getFormatDateAdd(sdate, i);
			dayOfWeek = DateUtil.getDayOfWeek(dayAfterStartDate);// 判断日期是周几，周日至周六分别是0到6
			if (dayOfWeek == 0) { // 到周日就算一周
				result++;
				long rest = LEN - i;
				if (rest > 0 && rest < 7) {// 到周日后还有剩余天数且不足7天，再加一周
					result++;
					break;
				}
			}
		}
		return result == 0 ? 1 : result;// 不足7天的算一周
	}

	/**
	 * 获取日期范围内的哪些天
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return array[] 日期的集合
	 */
	@SuppressWarnings("boxing")
	public static String[] getDaysWithDateScope(String startDate, String endDate) {
		if (DateUtil.compareDay(startDate, endDate)) {
			throw new RuntimeException("开始日期必须小于于结束日期");
		}
		if (startDate.equals(endDate)) {
			return new String[] { startDate };
		}
		Date sdate = DateUtil.getDate(startDate);
		Long LEN = DateUtil.getIntervalDays(startDate, endDate);// 用来计算两日期之间总共有多少天
		String[] dateResult = new String[LEN.intValue() + 1];
		dateResult[0] = startDate;
		for (int i = 1; i < LEN + 1; i++) {
			dateResult[i] = DateUtil.getFormatDateAdd(sdate, i);
		}
		return dateResult;
	}

	/**
	 * 获取日期范围内的第几周有哪些天
	 * 
	 * @desc 例如：2012-09-05(周三)到2013-01-18(周五)范围内，第1周有5天(周三到周日)，第2周有7天(周一到周日)
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param weekNum
	 *            第几周
	 * @return array[] 日期的集合
	 */
	@SuppressWarnings("boxing")
	public static String[] getDaysWithWeekNum(String startDate, String endDate, int weekNum) {
		List<String> list = Arrays.asList(getDaysWithDateScope(startDate, endDate));
		List<String> days = new ArrayList<String>();
		for (int i = 1; i <= 7; i++) {
			String dateWithTWeek = getDateWithTWeek(startDate, weekNum, i);
			if (list.contains(dateWithTWeek)) {
				days.add(dateWithTWeek);
			}
		}
		return days.toArray(new String[days.size()]);
	}

}
