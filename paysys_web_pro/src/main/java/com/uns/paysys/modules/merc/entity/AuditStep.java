package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AuditStep {
	
	private Long id;

    private BigDecimal auditRuleId;

    private BigDecimal auditGroupId;

    private String name;

    private String auditLevel;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAuditLevel() {
        return auditLevel;
    }

    public void setAuditLevel(String auditLevel) {
        this.auditLevel = auditLevel == null ? null : auditLevel.trim();
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