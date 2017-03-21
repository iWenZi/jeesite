package com.uns.paysys.modules.merc.form;

import java.sql.Timestamp;

public class AssignForm extends BaseForm{

//	查询字段
	private String merchantName;//推广商名称
	private String auditStatus;//审核状态
	private String accountName;//商戶名稱
	private String email; // 邮箱
	private String accountStatus;// 商户状态
	private String accountType;// 商户类型
	private Timestamp assignTime;// 分配日期
	
	private String merchantStatus;
	private Timestamp createTime;
	
	private String accountId;//商戶id
	
	private String smId;//推广商id
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSmId() {
		return smId;
	}
	public void setSmId(String smId) {
		this.smId = smId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getMerchantStatus() {
		return merchantStatus;
	}
	public void setMerchantStatus(String merchantStatus) {
		this.merchantStatus = merchantStatus;
	}
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Timestamp getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Timestamp assignTime) {
		this.assignTime = assignTime;
	}


	
}
