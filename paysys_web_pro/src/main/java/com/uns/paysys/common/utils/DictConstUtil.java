package com.uns.paysys.common.utils;

/**
 * @author Administrator
 *
 */
public class DictConstUtil {
	

	/**
	 * fee_ratio 费率表 relating_type相关类型 字段含义
	 * 1：账户
	 * 2：账户类型
	 * 3：银生自己
	 * 4：推广商
	 */
	public static final String RELATIN_TYPE_1 = "1";
	public static final String RELATIN_TYPE_2 = "2";
	public static final String RELATIN_TYPE_3 = "3";
	public static final String RELATIN_TYPE_4 = "4";
	
	
	/**
	 * fee_ratio 费率表 audit_status审核状态 字段含义
	 * 0：待审核
	 * 1：审核中
	 * 2：审核通过
	 * 3：审核拒绝
	 */
	public static final String AUDIT_STATUS_0 = "0";
	public static final String AUDIT_STATUS_1 = "1";
	public static final String AUDIT_STATUS_2 = "2";
	public static final String AUDIT_STATUS_3 = "3";
	
	
	/**
	 * fee_ratio 费率表 flag收/付款方标志 字段含义
	 * C：收款方
	 * D：付款方
	 * N：无
	 */
	public static final String FLAG_C = "C";
	public static final String FLAG_D = "D";
	public static final String FLAG_N = "N";
	
	
	/**
	 * spread_merchant_info 推广商表 audit_status审核状态 字段含义
	 * 1：待审核
	 * 2：审核通过
	 * 3：审核失败
	 */
	public static final String MERCHANT_INFO_AUDIT_STATUS_1 = "1";
	public static final String MERCHANT_INFO_AUDIT_STATUS_2 = "2";
	public static final String MERCHANT_INFO_AUDIT_STATUS_3 = "3";
	
	/**
	 * audit_operand_type 表operand_class
	 * 
	 */
	public static final String FEE_RATIO = "FEE_RATIO";//费率审核对象表名
	public static final String OPREAND_CLASS_FEE_RATIO= "FEE_RATIO";
	public static final String ACTION_FLAG_1 = "1";

	public static final String SPREAD_MERCHANT_INFO = "SPREAD_MERCHANT_INFO";//推广商审核对象表名
	public static final String OPREAND_CLASS_SPREAD_MERCHANT_INFO= "SPREAD_MERCHANT_INFO";
	
	public static final String OPREAND_CLASS_SPREAD_MERCHANT_INFO_ASSIGN= "SPREAD_MERCHANT_INFO_ASSIGN";
	
	/**
	 * 推广商状态
	 * 1:有效 
	 * 2:无效 
	 * 3:冻结 
	 * 4:锁定
	 */
	public static final String MERCHANT_STATUS_1 = "1";
	public static final String MERCHANT_STATUS_2 = "2";
	public static final String MERCHANT_STATUS_3 = "3";
	public static final String MERCHANT_STATUS_4 = "4";
	
	

	public static final String LOGIN_FLAG_0 = "0";
	public static final String LOGIN_FLAG_1 = "1";
	
	
	public static final String USERID_SESSIONID_MAP = "USERID_SESSIONID_MAP";
	
	
	
	
}
