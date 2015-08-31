package com.unitever.platform.core.dataSource;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.unitever.platform.core.dataSource.helper.DataSourceHolder;

/**
 * 支持多个数据源，支持读写分离
 * 
 * @author tianyl
 * 
 */
public class MultipleReadWriteDataSource extends AbstractRoutingDataSource {

	private static final Logger log = LoggerFactory.getLogger(MultipleReadWriteDataSource.class);

	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}

	@Override
	protected DataSource determineTargetDataSource() {
		DataSource dataSource = null;

		try {
			dataSource = DataSourceHolder.getCurDataSource();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			log.error("Cannot determine target DataSource", e);
		}
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource");
		}
		return dataSource;
	}

	public MultipleReadWriteDataSource() throws PropertyVetoException {
		DataSourceHolder.init();
	}

	@Override
	public void afterPropertiesSet() {

	}
}
