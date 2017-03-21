package com.uns.paysys.modules.merc.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

/**
 * 交易类型实体类
 * 
 * @author Administrator
 *
 */
public class BankInfo extends DataEntity<BankInfo> {

	private static final long serialVersionUID = 1L;
	private String bankId;
	private String bankName;
	private String bankDesc;
	private String bankAddress;
	private String netBankUrl;
	private String validFlag;
	private String remark;
	private Long Id;
	private Long version;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	private String bankCode;
	private String telBankUrl;
	private String telBankNo;
	private String mobileBankUrl;
	private Long supported;
	private Long exceptional;
	private Long bankGroup;
	private Long pri;
	private String linkCode;
	private String drawBank;
	private String settlementTime;
	private String cardType;
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankDesc() {
		return bankDesc;
	}
	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getNetBankUrl() {
		return netBankUrl;
	}
	public void setNetBankUrl(String netBankUrl) {
		this.netBankUrl = netBankUrl;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getTelBankUrl() {
		return telBankUrl;
	}
	public void setTelBankUrl(String telBankUrl) {
		this.telBankUrl = telBankUrl;
	}
	public String getTelBankNo() {
		return telBankNo;
	}
	public void setTelBankNo(String telBankNo) {
		this.telBankNo = telBankNo;
	}
	public String getMobileBankUrl() {
		return mobileBankUrl;
	}
	public void setMobileBankUrl(String mobileBankUrl) {
		this.mobileBankUrl = mobileBankUrl;
	}
	public Long getSupported() {
		return supported;
	}
	public void setSupported(Long supported) {
		this.supported = supported;
	}
	public Long getExceptional() {
		return exceptional;
	}
	public void setExceptional(Long exceptional) {
		this.exceptional = exceptional;
	}
	public Long getBankGroup() {
		return bankGroup;
	}
	public void setBankGroup(Long bankGroup) {
		this.bankGroup = bankGroup;
	}
	public Long getPri() {
		return pri;
	}
	public void setPri(Long pri) {
		this.pri = pri;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	public String getDrawBank() {
		return drawBank;
	}
	public void setDrawBank(String drawBank) {
		this.drawBank = drawBank;
	}
	public String getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(String settlementTime) {
		this.settlementTime = settlementTime;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}
