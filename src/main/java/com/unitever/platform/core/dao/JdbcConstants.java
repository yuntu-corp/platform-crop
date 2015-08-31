package com.unitever.platform.core.dao;

import com.unitever.platform.core.config.ConfigurableConstants;


public class JdbcConstants extends ConfigurableConstants{

	/**
	 * 查询超时时间
	 */
	public static final int QUERY_TIMEOUT = Integer.valueOf(getProperty("jdbc.queryTimeout", "300"));
}
