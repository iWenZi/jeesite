package com.uns.paysys.common.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Administrator
 *		数据库源切换类
 */
public class RoutingDataSources extends AbstractRoutingDataSource{ 

	@Override 
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSourceType();
	}

}
