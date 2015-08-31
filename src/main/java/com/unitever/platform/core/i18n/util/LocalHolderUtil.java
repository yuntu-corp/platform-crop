package com.unitever.platform.core.i18n.util;

import java.util.Locale;

public class LocalHolderUtil {

	private static final ThreadLocal<Locale> threadLocal = new ThreadLocal<Locale>();

	public static void setLocal(Locale locale) {
		threadLocal.set(locale);
	}

	public static Locale getLocal() {
		Locale locale = threadLocal.get();
		if (locale == null) {
			locale = new Locale("zh", "CN");
		}
		return locale;
	}
}
