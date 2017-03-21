package com.uns.paysys.modules.sys.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

public class SystemLog extends DataEntity<SystemLog> {
	
	private static final long serialVersionUID = 1L;
	private Long accountSeq;
	private String accountId;
	private String accountName;
	private String loginIp;
	private Date loginTime;
	private Date logoutTime;
	private Long id;
	private String loginErrReason;
	
	
	public Long getAccountSeq() {
		return accountSeq;
	}
	public void setAccountSeq(Long accountSeq) {
		this.accountSeq = accountSeq;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Date getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginErrReason() {
		return loginErrReason;
	}
	public void setLoginErrReason(String loginErrReason) {
		this.loginErrReason = loginErrReason;
	}
	
}
