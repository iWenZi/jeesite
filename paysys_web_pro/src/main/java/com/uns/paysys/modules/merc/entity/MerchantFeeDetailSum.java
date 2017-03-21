package com.uns.paysys.modules.merc.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;
import com.uns.paysys.common.utils.excel.annotation.ExcelField;
import com.uns.paysys.modules.sys.utils.SpreadMerchantUtils;

public class MerchantFeeDetailSum extends DataEntity<MerchantFeeDetailSum>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String merchantId; //推广商编号
	private String merchantName;
	private String transDate;
	private Double sumAmount;
	private Long count;
	private Double sumFee;
	
	private Date beginDate;
	private Date endDate;
	private Long actionSeq;
	
	
	
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
	public Long getActionSeq() {
		return actionSeq;
	}
	public void setActionSeq(Long actionSeq) {
		this.actionSeq = actionSeq;
	}
	@ExcelField(title="分润金额", type=1, align=2, sort=6)
	public Double getSumFee() {
		return sumFee;
	}
	public void setSumFee(Double sumFee) {
		this.sumFee = sumFee;
	}
	@ExcelField(title="交易笔数", type=1, align=2, sort=5)
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@ExcelField(title="交易总额", type=1, align=2, sort=4)
	public Double getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(Double sumAmount) {
		this.sumAmount = sumAmount;
	}
	@ExcelField(title="交易日期", type=1, align=2, sort=3)
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	@ExcelField(title="推广商名称", type=1, align=2, sort=2)
	public String getMerchantName() {
		return SpreadMerchantUtils.getSpreadMercNameById(Long.valueOf(merchantId), "未知推广商");
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
	
	
}
