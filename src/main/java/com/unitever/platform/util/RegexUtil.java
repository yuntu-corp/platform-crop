package com.unitever.platform.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.weaver.patterns.ParserException;

import com.unitever.platform.core.exception.BusinessException;

/**
 * 
 * 
 * @author Sheny on 2011-10-24
 */
public class RegexUtil {
	/**
	 * 正整数
	 */
	public static final String REGEXP_POSITIVE_INTEGER = "^[0-9]*[1-9][0-9]*$";
	/**
	 * 非负整数
	 */
	public static final String REGEXP_NON_NEGATIVE_INTEGER = "^\\d+$";
	/**
	 * 非负浮点数
	 */
	public static final String REGEXP_NON_NEGATIVE_FLOAT = "^\\d+(\\.\\d+)?$";

	/**
	 * email地址
	 */
	public static final String REGEXP_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	/**
	 * 返回找到的第一个匹配的字符串，没有找到就返回null。
	 * 
	 * @param pattern
	 * @param input
	 * @return
	 * @throws ParserException
	 */
	public static String find(Pattern pattern, String input) {
		return find(pattern, input, 0);
	}

	/**
	 * 返回所有匹配的字符串，没有找到就返回长度为0的数组。
	 * 
	 * @param pattern
	 * @param input
	 * @return
	 * @throws ParserException
	 */
	public static String[] findAllResult(Pattern pattern, String input) throws ParserException {
		try {
			List<String> results = new ArrayList<String>();
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				results.add(matcher.group());
			}
			return results.toArray(new String[results.size()]);
		} catch (IllegalStateException e) {
			throw new BusinessException(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 返回所有匹配的group，如果没有的话，就返回长度为0的String数组。注意，字符串数组顺序跟group的顺序一致，但是从0开始的
	 * 
	 * @param pattern
	 * @param input
	 * @return
	 * @throws ParserException
	 */
	public static String[] findAll(Pattern pattern, String input) throws ParserException {
		try {
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				int groupMax = matcher.groupCount();
				String[] matchGroup = new String[groupMax];
				for (int i = 1; i <= groupMax; i++) {
					matchGroup[i - 1] = matcher.group(i);
				}
				return matchGroup;
			}
			return new String[0];
		} catch (IllegalStateException e) {
			throw new BusinessException(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 返回找到的第一个匹配的字符串中的groupId对应的字符串，没有找到就返回null。用法可以见main函数。提醒：groupId是从1开始计数的
	 * 
	 * @param pattern
	 * @param input
	 * @param groupId
	 * @return
	 * @throws ParserException
	 */
	public static String find(Pattern pattern, String input, int groupId) throws ParserException {
		try {
			Matcher matcher = pattern.matcher(input);
			if (matcher.find()) {
				return matcher.group(groupId);
			}
			return null;
		} catch (IllegalStateException e) {
			throw new BusinessException(e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public static int simpleCharacter(String input) {
		int count = 0;
		String regEx = "[\\u4e00-\\u9fa5]";
		// System.out.println(regEx);
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(input);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		// 以a开头以d结尾的最短字符串
		Pattern pattern = Pattern.compile("a(.*?)(\\d+)d", Pattern.CASE_INSENSITIVE);
		String input = "dsfabcd123d";
		// 输出abcd123d
		System.out.println(RegexUtil.find(pattern, input));
		// 输出123
		System.out.println(RegexUtil.find(pattern, input, 2));
		// 输出abcd123d
		String[] groups = RegexUtil.findAll(pattern, input);
		System.out.println(groups[0] + " " + groups[1]);
		input = "ab1dab1d";
		String[] results = RegexUtil.findAllResult(pattern, input);
		System.out.println(results[0] + " " + results[1]);
	}

}
