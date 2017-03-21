package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SpreadmercAcc {
    private Date assignTime;

    private BigDecimal defaultSubSeq;

    private String auditStatus;

    private String spreadmerchantId;

    private String accountId;


    
    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }



	public BigDecimal getDefaultSubSeq() {
		return defaultSubSeq;
	}

	public void setDefaultSubSeq(BigDecimal defaultSubSeq) {
		this.defaultSubSeq = defaultSubSeq;
	}

	public String getSpreadmerchantId() {
		return spreadmerchantId;
	}

	public void setSpreadmerchantId(String spreadmerchantId) {
		this.spreadmerchantId = spreadmerchantId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }
}