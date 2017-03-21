package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AuditOperation {
	private Long id;

    private BigDecimal auditOperandId;

    private BigDecimal auditRuleId;

    private BigDecimal auditGroupId;

    private BigDecimal previousStatus;

    private BigDecimal postStatus;

    private String attitude;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAuditOperandId() {
        return auditOperandId;
    }

    public void setAuditOperandId(BigDecimal auditOperandId) {
        this.auditOperandId = auditOperandId;
    }

    public BigDecimal getAuditRuleId() {
        return auditRuleId;
    }

    public void setAuditRuleId(BigDecimal auditRuleId) {
        this.auditRuleId = auditRuleId;
    }

    public BigDecimal getAuditGroupId() {
        return auditGroupId;
    }

    public void setAuditGroupId(BigDecimal auditGroupId) {
        this.auditGroupId = auditGroupId;
    }

    public BigDecimal getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(BigDecimal previousStatus) {
        this.previousStatus = previousStatus;
    }

    public BigDecimal getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(BigDecimal postStatus) {
        this.postStatus = postStatus;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude == null ? null : attitude.trim();
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}