package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

public class AuditOperandType  extends DataEntity<AuditOperandType>  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String operandClass;

    private String actionFlag;

    private String name;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private BigDecimal systemType;

    private BigDecimal ruleUpdatable;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperandClass() {
        return operandClass;
    }

    public void setOperandClass(String operandClass) {
        this.operandClass = operandClass == null ? null : operandClass.trim();
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag == null ? null : actionFlag.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public BigDecimal getSystemType() {
        return systemType;
    }

    public void setSystemType(BigDecimal systemType) {
        this.systemType = systemType;
    }

    public BigDecimal getRuleUpdatable() {
        return ruleUpdatable;
    }

    public void setRuleUpdatable(BigDecimal ruleUpdatable) {
        this.ruleUpdatable = ruleUpdatable;
    }
}