package com.uns.paysys.common.utils;

@SuppressWarnings({"unchecked","rawtypes"})
public class DynamicDataSourceHolder {
	
//	获得和设置上下文环境 主要负责改变上下文数据源的名称 
	private static final ThreadLocal contextHolder = new ThreadLocal();

	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return (String) contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
	
}
