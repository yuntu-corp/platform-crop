package com.unitever.platform.core.config;

public class SystemConstants extends ConfigurableConstants {

	static {
		init("/conf/system.properties");
	}

	public static final String I18N_ENABLECACHE = getProperty("i18n.enableCache", "false");
}
