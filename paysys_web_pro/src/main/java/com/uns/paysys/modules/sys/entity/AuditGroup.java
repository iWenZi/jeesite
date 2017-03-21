package com.uns.paysys.modules.sys.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.uns.paysys.common.persistence.DataEntity;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.modules.sys.utils.StringUtil;

public class AuditGroup extends DataEntity<AuditGroup> {
	private static final long serialVersionUID = 1L;

	private BigDecimal accountId;

    private String name;

    private String remark;

    private BigDecimal used;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private BigDecimal systemGroup;

    private BigDecimal updatable;
    
    private String userIdList;
    
    private String[] userList ;


    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public BigDecimal getSystemGroup() {
        return systemGroup;
    }

    public void setSystemGroup(BigDecimal systemGroup) {
        this.systemGroup = systemGroup;
    }

    public BigDecimal getUpdatable() {
        return updatable;
    }

    public void setUpdatable(BigDecimal updatable) {
        this.updatable = updatable;
    }


	public String[] getUserList() {
		if(!StringUtil.isStrEmpty(userIdList) && userList==null){
			return userIdList.split(",");
		}
		return userList;
	}

	public void setUserList(String[] userList) {
		this.userList = userList;
	}

	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}

    
}