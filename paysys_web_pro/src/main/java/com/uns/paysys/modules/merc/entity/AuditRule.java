package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.uns.paysys.common.persistence.DataEntity;

public class AuditRule extends DataEntity<AuditRule> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private BigDecimal accountId;

    private BigDecimal auditOperandTypeId;

    private String name;

    private String valid;

    private BigDecimal used;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
    
    
    private List<AuditStep> stepList = Lists.newArrayList(); // 拥有审核步骤列表


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    @NotNull
    public BigDecimal getAuditOperandTypeId() {
        return auditOperandTypeId;
    }

    public void setAuditOperandTypeId(BigDecimal auditOperandTypeId) {
        this.auditOperandTypeId = auditOperandTypeId;
    }

    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public BigDecimal getUsed() {
        return used;
    }

    public void setUsed(BigDecimal used) {
        this.used = used;
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

	public List<AuditStep> getStepList() {
		return stepList;
	}

	public void setStepList(List<AuditStep> stepList) {
		this.stepList = stepList;
	}

	
	private String[] auditStepName;
	private Long[] auditGroupId;


	public String[] getAuditStepName() {//去掉第一个
		if(auditStepName ==null || auditStepName.length<1) 
			return auditStepName;
		String[] smallStepName  = new String[auditStepName.length-1];
		for(int i=1;i<auditStepName.length;i++){
			smallStepName[i-1] = auditStepName[i];
		}
		return smallStepName;
	}

	public void setAuditStepName(String[] auditStepName) {
		this.auditStepName = auditStepName;
	}

	public Long[] getAuditGroupId() {//去掉第一个
		if(auditGroupId ==null || auditGroupId.length<1) 
			return auditGroupId;
		Long[] smallAuditGroupId  = new Long[auditGroupId.length-1];
		for(int i=1;i<auditGroupId.length;i++){
			smallAuditGroupId[i-1] = auditGroupId[i];
		}
		return smallAuditGroupId;
	}

	public void setAuditGroupId(Long[] auditGroupId) {
		this.auditGroupId = auditGroupId;
	}
	
	
    
}