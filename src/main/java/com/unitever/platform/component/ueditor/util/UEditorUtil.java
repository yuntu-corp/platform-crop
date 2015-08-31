package com.unitever.platform.component.ueditor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UEditorUtil {

	/**
	 * 获取指定内容中的<img>标签中的src
	 * 
	 * @param content
	 * @return
	 */
	public static List<String> getImgSrc(String content) {
		List<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile("<img.*?/>");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String img = matcher.group();
			Pattern p1 = Pattern.compile("src=\".*?\"");
			Matcher m1 = p1.matcher(img);
			if (m1.find()) {
				String src = m1.group();
				src = src.replaceFirst("src=\"", "");
				src = src.substring(0, src.length() - 1);
				result.add(src);
			}
		}
		return result;
	}

	/**
	 * 去掉UEditor内容中的html，失去格式
	 * 
	 * @param content
	 * @return
	 */
	public static String getContentWithoutHtml(String content) {
		return content == null ? null : content.replaceAll("<.*?>", "");
	}
}
