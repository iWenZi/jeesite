package com.uns.paysys.modules.merc.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

/**
 * 交易类型实体类
 * 
 * @author Administrator
 *
 */
public class SpreadMerchantBankInfo extends DataEntity<SpreadMerchantBankInfo> {

	private static final long serialVersionUID = 1L;

	private String accountName;
	private String accountAlias;
	private Long provSeq;
	private Long citySeq;
	private Long bankSeq;
	private Long spreadMerchantId;
	private String openAccountBankName;
	private String cardNo;
	private String cardType;
	private Long id;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public Long getProvSeq() {
		return provSeq;
	}

	public void setProvSeq(Long provSeq) {
		this.provSeq = provSeq;
	}

	public Long getCitySeq() {
		return citySeq;
	}

	public void setCitySeq(Long citySeq) {
		this.citySeq = citySeq;
	}

	public Long getBankSeq() {
		return bankSeq;
	}

	public void setBankSeq(Long bankSeq) {
		this.bankSeq = bankSeq;
	}

	public Long getSpreadMerchantId() {
		return spreadMerchantId;
	}

	public void setSpreadMerchantId(Long spreadMerchantId) {
		this.spreadMerchantId = spreadMerchantId;
	}

	public String getOpenAccountBankName() {
		return openAccountBankName;
	}

	public void setOpenAccountBankName(String openAccountBankName) {
		this.openAccountBankName = openAccountBankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


}
