package com.unitever.platform.core.dataSource.helper;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

public class DataSourceWrap {

	private String driverClass;

	private String url;

	private String userName;

	private String password;

	private String minPoolSize;

	private String maxPoolSize;

	private String maxIdleTime;

	private String acquireIncrement;

	private String idleConnectionTestPeriod;

	private String acquireRetryAttempts;

	private String breakAfterAcquireFailure;

	private String testConnectionOnCheckout;

	private String checkoutTimeout;

	private String key;

	private String value;

	private String kind;// 用于分组

	private DataSource dataSource;

	public DataSourceWrap() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(String minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public String getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(String maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public String getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(String maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(String acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public String getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(String idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}

	public String getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}

	public void setAcquireRetryAttempts(String acquireRetryAttempts) {
		this.acquireRetryAttempts = acquireRetryAttempts;
	}

	public String getBreakAfterAcquireFailure() {
		return breakAfterAcquireFailure;
	}

	public void setBreakAfterAcquireFailure(String breakAfterAcquireFailure) {
		this.breakAfterAcquireFailure = breakAfterAcquireFailure;
	}

	public String getTestConnectionOnCheckout() {
		return testConnectionOnCheckout;
	}

	public void setTestConnectionOnCheckout(String testConnectionOnCheckout) {
		this.testConnectionOnCheckout = testConnectionOnCheckout;
	}

	public String getCheckoutTimeout() {
		return checkoutTimeout;
	}

	public void setCheckoutTimeout(String checkoutTimeout) {
		this.checkoutTimeout = checkoutTimeout;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public DataSource buildDataSource() throws PropertyVetoException {
		if (dataSource == null) {
//			ComboPooledDataSource ds = new ComboPooledDataSource();
//			ds.setDriverClass(driverClass);
//			ds.setJdbcUrl(url);
//			ds.setUser(userName);
//			ds.setPassword(password);
//			ds.setMinPoolSize(Integer.valueOf(minPoolSize));
//			ds.setMaxPoolSize(Integer.valueOf(maxPoolSize));
//			ds.setMaxIdleTime(Integer.valueOf(maxIdleTime));
//			ds.setAcquireIncrement(Integer.valueOf(acquireIncrement));
//			ds.setIdleConnectionTestPeriod(Integer.valueOf(idleConnectionTestPeriod));
//			ds.setAcquireRetryAttempts(Integer.valueOf(acquireRetryAttempts));
//			ds.setBreakAfterAcquireFailure(Boolean.valueOf(breakAfterAcquireFailure));
//			ds.setTestConnectionOnCheckout(Boolean.valueOf(testConnectionOnCheckout));
//			// TODO
//			ds.setCheckoutTimeout(5000);
//			dataSource = ds;
		}
		return dataSource;
	}
}
