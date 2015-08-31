package com.unitever.platform.core.i18n.util;

import java.util.Locale;

public class I18nUtil {

	/**
	 * 获取国际化信息
	 * 
	 * @param code
	 *            国际化信息中的key
	 * @param args
	 *            参数
	 * @return
	 */
	public static String getI18nString(String code, String... args) {
		Locale locale = LocalHolderUtil.getLocal();
		String result = I18nResourceBundleMessage.getMessage(code, locale, args);
		return result;
	}

}
