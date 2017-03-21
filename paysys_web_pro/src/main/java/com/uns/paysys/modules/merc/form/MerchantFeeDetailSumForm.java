package com.uns.paysys.modules.merc.form;

import java.util.Date;

public class MerchantFeeDetailSumForm extends BaseForm{
	//	查询字段
	private String merchantName;//推广商名称
	private String merchantId;//推广商编号
	private Long actionSeq;//交易类型id
	private Date beginDate;
	private Date endDate;
	
	public String getMerchantName() {
		return merchantName==null ? null : merchantName.trim();
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public Long getActionSeq() {
		return actionSeq;
	}
	public void setActionSeq(Long actionSeq) {
		this.actionSeq = actionSeq;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	
	
}

