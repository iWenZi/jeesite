package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SpreadmerchantHis {
    private String operator;

    private Date operationtime;

    private String remark;

    private Long id;

    private Date createtime;

    private String createuser;

    private BigDecimal spreadMerchantId;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public BigDecimal getSpreadMerchantId() {
        return spreadMerchantId;
    }

    public void setSpreadMerchantId(BigDecimal spreadMerchantId) {
        this.spreadMerchantId = spreadMerchantId;
    }
}