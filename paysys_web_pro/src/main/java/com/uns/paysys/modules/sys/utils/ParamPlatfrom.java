package com.uns.paysys.modules.sys.utils;

import com.uns.inf.acms.client.DynamicConfigLoader;

/**
 * 参数平台调用类，所有引用参数平台数据在此定义
 * @author Administrator
 *
 */
public class ParamPlatfrom {

	  public static final String DOWN_UPLOAD_URL = DynamicConfigLoader.getByEnv("DOWN_UPLOAD_URL");//单个下载文件
	  public static final String DOWNALL_UPLOAD_URL=DynamicConfigLoader.getByEnv("DOWNALL_UPLOAD_URL");//多个下载文件
	  public static final String CREATE_UPLOAD_URL=DynamicConfigLoader.getByEnv("CREATE_UPLOAD_URL");//批量上传文件接口
	  public static final String DELETE_UPLOAD_URL=DynamicConfigLoader.getByEnv("DELETE_UPLOAD_URL");//删除上传文件接口
	  

	  public static final String JS_URL=DynamicConfigLoader.getByEnv("JS_URL");//删除上传文件接口
	  public static final String YY_URL=DynamicConfigLoader.getByEnv("YY_URL");//删除上传文件接口
	  
}
