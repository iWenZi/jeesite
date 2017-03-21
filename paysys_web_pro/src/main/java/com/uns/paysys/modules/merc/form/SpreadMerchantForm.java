package com.uns.paysys.modules.merc.form;

public class SpreadMerchantForm extends BaseForm{

//	查询字段
	private String merchantName;//推广商名称
	private Long actionSeq;//交易类型id
	private Long feeMethod;//收费方式
	private String auditStatus;//审核状态
	
	
	public String getMerchantName() {
		return merchantName==null ? null : merchantName.trim();
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
	
	public Long getFeeMethod() {
		return feeMethod;
	}
	
	public void setFeeMethod(Long feeMethod) {
		this.feeMethod = feeMethod;
	}
	
	public Long getActionSeq() {
		return actionSeq;
	}
	
	public void setActionSeq(Long actionSeq) {
		this.actionSeq = actionSeq;
	}

}
