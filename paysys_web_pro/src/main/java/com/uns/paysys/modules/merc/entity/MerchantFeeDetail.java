package com.uns.paysys.modules.merc.entity;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.uns.paysys.common.persistence.DataEntity;
import com.uns.paysys.common.utils.excel.annotation.ExcelField;
import com.uns.paysys.modules.sys.utils.ActionTypeUtils;
import com.uns.paysys.modules.sys.utils.DictUtils;
import com.uns.paysys.modules.sys.utils.SpreadMerchantUtils;

public class MerchantFeeDetail extends DataEntity<MerchantFeeDetail>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036257625736446506L;
	
	private String merchantName;//推广商名称
	private Long merchantId; //推广商编号
	private String transId;	//交易编号
	private String orderId;//订单编号
	private String accountType;
	private String creditName;
	private String debitName;
	
	private String actionSeq;	//交易类型id
	private String transStatus;
	private Date createTime;
	
	private Double amount;	//交易金额
	private String reverseFlag;	//是否冲正
	
	private String spreadFeeMethod;
	private Double spreadFee;
	private String accountFeeMethod;
	private Double accountFee;
	private Double fee;
	private String remark;
	
	
	private String actionName;//交易类型
	private Date beginDate;
	private Date endDate;
	
	


	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	@ExcelField(title="推广商名称", align=2, sort=1)
	public String getMerchantName() {
		return SpreadMerchantUtils.getSpreadMercNameById(merchantId, "未知推广商");
	}
	
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	@ExcelField(title="交易编号", align=2, sort=2)
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	
	@ExcelField(title="订单编号", align=2, sort=3)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@ExcelField(title="收款方名称", align=2, sort=5)
	public String getCreditName() {
		return creditName;
	}
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	
	@ExcelField(title="付款方名称", align=2, sort=6)
	public String getDebitName() {
		return debitName;
	}
	public void setDebitName(String debitName) {
		this.debitName = debitName;
	}
	
	@ExcelField(title="交易类型", align=2, sort=7)
	public String getActionName() {
		return ActionTypeUtils.getActionName(actionSeq, "未知交易类型");
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@ExcelField(title="交易金额", align=2, sort=10)
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@ExcelField(title="是否冲正", align=2, sort=11)
	public String getReverseFlag() {
		return reverseFlag==null?"否":"是";
	}
	public void setReverseFlag(String reverseFlag) {
		this.reverseFlag = reverseFlag;
	}


	public String getActionSeq() {
		return actionSeq;
	}
	public void setActionSeq(String actionSeq) {
		this.actionSeq = actionSeq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@ExcelField(title="商户名称", align=2, sort=4)
	public String getAccountType() {
		return accountType=="B"?this.creditName:this.debitName;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@ExcelField(title="交易状态", align=2, sort=8)
	public String getTransStatus() {
		return DictUtils.getDictLabel(transStatus, "trans_status", "未知状态");
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	@ExcelField(title="交易时间", align=2, sort=9)
	public String getCreateTime() {
		return DateFormatUtils.format(createTime, "yyyy-MM-dd HH:mm:ss");
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String getSpreadFeeMethod() {
		return spreadFeeMethod;
	}
	public void setSpreadFeeMethod(String spreadFeeMethod) {
		this.spreadFeeMethod = spreadFeeMethod;
	}
	@ExcelField(title="推广商费率", align=2, sort=11)
	public String getSpreadFee() {
		if(spreadFeeMethod.equals("1")){
			return spreadFee+" /笔";
		}else if(spreadFeeMethod.equals("2")){
			return spreadFee+" %";
		}else{
			return "阶梯费率";
		}
	}
	
	public void setSpreadFee(Double spreadFee) {
		this.spreadFee = spreadFee;
	}
	public String getAccountFeeMethod() {
		return accountFeeMethod;
	}
	public void setAccountFeeMethod(String accountFeeMethod) {
		this.accountFeeMethod = accountFeeMethod;
	}
	@ExcelField(title="商户费率", align=2, sort=12)
	public String getAccountFee() {
		if(accountFeeMethod.equals("1")){
			return accountFee+" /笔";
		}else if(accountFeeMethod.equals("2")){
			return accountFee+" %";
		}else{
			return "阶梯费率";
		}
	}
	public void setAccountFee(Double accountFee) {
		this.accountFee = accountFee;
	}
	@ExcelField(title="推广费", align=2, sort=13)
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	@ExcelField(title="备注", align=2, sort=14)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
