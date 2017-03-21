package com.uns.paysys.modules.sys.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

/**
 * 交易类型实体类
 * @author Administrator
 *
 */
public class ActionType extends DataEntity<ActionType> {
	
	private static final long serialVersionUID = 1L;
	
    private String actionId;
    private String actionName;
    private Short flag;//银生账户增减标志
    private Long version;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private String status;//逻辑删除标志
    private Short accountFlag;//当前账户增减标志
    
    
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Short getFlag() {
		return flag;
	}
	public void setFlag(Short flag) {
		this.flag = flag;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Short getAccountFlag() {
		return accountFlag;
	}
	public void setAccountFlag(Short accountFlag) {
		this.accountFlag = accountFlag;
	}

}
