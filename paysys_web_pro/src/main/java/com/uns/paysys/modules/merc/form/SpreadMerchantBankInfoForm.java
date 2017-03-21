package com.uns.paysys.modules.merc.form;

import java.sql.Timestamp;

public class SpreadMerchantBankInfoForm extends BaseForm{

//	查询字段
	private String merchantName;//推广商名称
	private String auditStatus;//审核状态
	
	private String email; // 邮箱
	
	private String legalRepreMobile;// 法定代表人手机号码
	private String merchantStatus;// 商户状态
	
	private Timestamp createTime;// 创建日期

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLegalRepreMobile() {
		return legalRepreMobile;
	}

	public void setLegalRepreMobile(String legalRepreMobile) {
		this.legalRepreMobile = legalRepreMobile;
	}

	public String getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(String merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
