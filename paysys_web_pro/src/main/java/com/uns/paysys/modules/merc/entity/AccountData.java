package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AccountData {
    private Long id;

    private BigDecimal accountSeq;

    private String fileName;

    private String dataFlag;

    private String appcode;

    private String filekey;

    private String ret;

    private Date createDate;

    private String createUser;

    private Date updateDate;

    private String updateUser;

    private String fileType;



    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAccountSeq() {
        return accountSeq;
    }

    public void setAccountSeq(BigDecimal accountSeq) {
        this.accountSeq = accountSeq;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag == null ? null : dataFlag.trim();
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode == null ? null : appcode.trim();
    }

    public String getFilekey() {
        return filekey;
    }

    public void setFilekey(String filekey) {
        this.filekey = filekey == null ? null : filekey.trim();
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret == null ? null : ret.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }
}