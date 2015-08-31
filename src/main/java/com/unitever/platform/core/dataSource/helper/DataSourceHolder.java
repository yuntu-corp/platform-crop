package com.unitever.platform.core.dataSource.helper;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.util.MapUtil;
import com.unitever.platform.util.PropertyUtil;

/**
 * DataSourceHolder
 * 
 * @author tianyl
 * 
 */
public class DataSourceHolder {

	private static final ThreadLocal<String> curKeyHolder = new ThreadLocal<String>();

	private static final ThreadLocal<Boolean> curReadOnlyHolder = new ThreadLocal<Boolean>();

	private static final int READONLY_MAX_COUNT = 100;

	/**
	 * 所有数据源(包括只读)
	 */
	private static final List<DataSourceWrap> ALL_DATASOURCE = Collections.synchronizedList(new ArrayList<DataSourceWrap>());

	/**
	 * 只读（readonly）的DataSource
	 */
	private static final Map<String, List<DataSourceWrap>> READONLY_DATASOURCE_MAP = Collections.synchronizedMap(new HashMap<String, List<DataSourceWrap>>());

	/**
	 * 所有只读
	 */
	private static final List<DataSourceWrap> READONLY_DATASOURCE = Collections.synchronizedList(new ArrayList<DataSourceWrap>());

	/**
	 * 非readonly的DataSource
	 */
	private static final Map<String, DataSourceWrap> NORMAL_DATASOURCE_MAP = Collections.synchronizedMap(new HashMap<String, DataSourceWrap>());

	/**
	 * 所有非readonly的datasource
	 */
	private static final List<DataSourceWrap> NORMAL_DATASOURCE = Collections.synchronizedList(new ArrayList<DataSourceWrap>());

	public static void init() throws PropertyVetoException {
		Properties prop = PropertyUtil.getProperties("/conf/jdbc.properties");
		int dataSourceCount = Integer.valueOf(prop.getProperty("dataSource.count", "1"));
		String defAcquireIncrement = prop.getProperty("jdbc.acquireIncrement");
		String defAcquireRetryAttempts = prop.getProperty("jdbc.acquireRetryAttempts");
		String defBreakAfterAcquireFailure = prop.getProperty("jdbc.breakAfterAcquireFailure");
		String defCheckoutTimeout = prop.getProperty("jdbc.checkoutTimeout");
		String defIdleConnectionTestPeriod = prop.getProperty("jdbc.idleConnectionTestPeriod");
		String defMaxIdleTime = prop.getProperty("jdbc.maxIdleTime");
		String defMaxPoolSize = prop.getProperty("jdbc.maxPoolSize");
		String defMinPoolSize = prop.getProperty("jdbc.minPoolSize");
		String defPassword = prop.getProperty("jdbc.password");
		String defTestConnectionOnCheckout = prop.getProperty("jdbc.testConnectionOnCheckout");
		String defUrl = prop.getProperty("jdbc.url");
		String defUserName = prop.getProperty("jdbc.username");
		String driverClass = prop.getProperty("jdbc.driverClassName");
		for (int i = 0; i < dataSourceCount; i++) {
			int num = i + 1;
			DataSourceWrap dataSource = new DataSourceWrap();
			dataSource.setDriverClass(driverClass);
			dataSource.setAcquireIncrement(prop.getProperty("jdbc.acquireIncrement_" + num, defAcquireIncrement));
			dataSource.setAcquireRetryAttempts(prop.getProperty("jdbc.acquireRetryAttempts_" + num, defAcquireRetryAttempts));
			dataSource.setBreakAfterAcquireFailure(prop.getProperty("jdbc.breakAfterAcquireFailure_" + num, defBreakAfterAcquireFailure));
			dataSource.setCheckoutTimeout(prop.getProperty("jdbc.checkoutTimeout_" + num, defCheckoutTimeout));
			dataSource.setIdleConnectionTestPeriod(prop.getProperty("jdbc.idleConnectionTestPeriod_" + num, defIdleConnectionTestPeriod));
			dataSource.setMaxIdleTime(prop.getProperty("jdbc.maxIdleTime_" + num, defMaxIdleTime));
			dataSource.setMaxPoolSize(prop.getProperty("jdbc.maxPoolSize_" + num, defMaxPoolSize));
			dataSource.setMinPoolSize(prop.getProperty("jdbc.minPoolSize_" + num, defMinPoolSize));
			dataSource.setPassword(prop.getProperty("jdbc.password_" + num, defPassword));
			dataSource.setTestConnectionOnCheckout(prop.getProperty("jdbc.testConnectionOnCheckout_" + num, defTestConnectionOnCheckout));
			dataSource.setUrl(prop.getProperty("jdbc.url_" + num, defUrl));
			dataSource.setUserName(prop.getProperty("jdbc.username_" + num, defUserName));
			String key = prop.getProperty("dataSource_" + num + ".key");
			String value = prop.getProperty("dataSource_" + num + ".value");
			String kind = prop.getProperty("dataSource_" + num + ".kind");
			dataSource.setKey(key);
			dataSource.setValue(value);
			dataSource.setKind(kind);

			dataSource.buildDataSource();

			ALL_DATASOURCE.add(dataSource);
			NORMAL_DATASOURCE_MAP.put(key, dataSource);
			NORMAL_DATASOURCE.add(dataSource);

			for (int j = 0; j < READONLY_MAX_COUNT; j++) {
				int readOnlyNum = j + 1;
				String suffixStr = num + "_readOnly_" + readOnlyNum;
				String readOnlyUrl = prop.getProperty("jdbc.url_" + suffixStr);
				if (StringUtils.isNotBlank(readOnlyUrl)) {
					DataSourceWrap readOnlyDataSource = new DataSourceWrap();
					readOnlyDataSource.setDriverClass(driverClass);
					readOnlyDataSource.setKey(key);
					readOnlyDataSource.setValue(value);
					readOnlyDataSource.setKind(kind);

					readOnlyDataSource.setAcquireIncrement(prop.getProperty("jdbc.acquireIncrement_" + suffixStr, dataSource.getAcquireIncrement()));
					readOnlyDataSource.setAcquireRetryAttempts(prop.getProperty("jdbc.acquireRetryAttempts_" + suffixStr, dataSource.getAcquireRetryAttempts()));
					readOnlyDataSource.setBreakAfterAcquireFailure(prop.getProperty("jdbc.breakAfterAcquireFailure_" + suffixStr, dataSource.getBreakAfterAcquireFailure()));
					readOnlyDataSource.setCheckoutTimeout(prop.getProperty("jdbc.checkoutTimeout_" + suffixStr, dataSource.getCheckoutTimeout()));
					readOnlyDataSource.setIdleConnectionTestPeriod(prop.getProperty("jdbc.idleConnectionTestPeriod_" + suffixStr, dataSource.getIdleConnectionTestPeriod()));
					readOnlyDataSource.setMaxIdleTime(prop.getProperty("jdbc.maxIdleTime_" + suffixStr, dataSource.getMaxIdleTime()));
					readOnlyDataSource.setMaxPoolSize(prop.getProperty("jdbc.maxPoolSize_" + suffixStr, dataSource.getMaxPoolSize()));
					readOnlyDataSource.setMinPoolSize(prop.getProperty("jdbc.minPoolSize_" + suffixStr, dataSource.getMinPoolSize()));
					readOnlyDataSource.setPassword(prop.getProperty("jdbc.password_" + suffixStr, dataSource.getPassword()));
					readOnlyDataSource.setTestConnectionOnCheckout(prop.getProperty("jdbc.testConnectionOnCheckout_" + suffixStr, dataSource.getTestConnectionOnCheckout()));
					readOnlyDataSource.setUrl(readOnlyUrl);
					readOnlyDataSource.setUserName(prop.getProperty("jdbc.username_" + suffixStr, dataSource.getUserName()));

					readOnlyDataSource.buildDataSource();

					MapUtil.getListInMap(READONLY_DATASOURCE_MAP, key).add(readOnlyDataSource);
					ALL_DATASOURCE.add(readOnlyDataSource);
					READONLY_DATASOURCE.add(readOnlyDataSource);
				}
			}
		}
	}

	/**
	 * 设置当前选择的datasource key
	 * 
	 * @param key
	 */
	public static void setCurDataSourceKey(String key) {
		curKeyHolder.set(key);
	}

	/**
	 * 获取当前选择的datasource key
	 * 
	 * @return
	 */
	public static String getCurDataSourceKey() {
		return curKeyHolder.get();
	}

	/**
	 * 清空当前选择的datasource key
	 */
	public static void clearCurKey() {
		curKeyHolder.remove();
	}

	public static void markReadOnly() {
		curReadOnlyHolder.set(true);
	}

	public static void markWrite() {
		curReadOnlyHolder.set(false);
	}

	public static void clearReadOnly() {
		curReadOnlyHolder.remove();
	}

	public static boolean isWrite() {
		return curReadOnlyHolder.get() != null && !curReadOnlyHolder.get();
	}

	public static DataSource getCurDataSource() throws PropertyVetoException {
		DataSourceWrap wrap = null;
		String key = getCurDataSourceKey();
		// 未选择数据源
		if (StringUtils.isBlank(key)) {
			if (isWrite()) {
				wrap = NORMAL_DATASOURCE.get(0);
			} else {// 只读
				wrap = READONLY_DATASOURCE.size() > 0 ? READONLY_DATASOURCE.get(0) : null;
			}
		} else {
			if (isWrite()) {
				wrap = NORMAL_DATASOURCE_MAP.get(key);
			} else {
				// TODO 做负载，需要保证一次请求使用同一个datasource
				List<DataSourceWrap> list = READONLY_DATASOURCE_MAP.get(key);
				wrap = (list != null && list.size() > 0) ? list.get(0) : null;
			}
		}

		if (wrap == null) {
			wrap = ALL_DATASOURCE.get(0);
		}
		return wrap.buildDataSource();
	}
}
