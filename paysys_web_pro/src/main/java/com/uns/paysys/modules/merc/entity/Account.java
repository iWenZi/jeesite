package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    private String accountId;

    private String email;

    private String name;

    private String loginPassword;

    private String payPassword;

    private String question;

    private String answer;

    private String idType;

    private String idCode;

    private String accountType;

    private String addr;

    private String tel;

    private BigDecimal provSeq;

    private BigDecimal citySeq;

    private String zipCode;

    private String flag;

    private String activeCode;

    private String offerPerson;

    private String mobile;

    private String status;

    private String remark;

    private BigDecimal defaultSubSeq;

    private String gender;

    private Date birthday;

    private String key;

    private String informMobile;

    private String mobStatus;

    private String mobActiveCode;

    private String mobLoginPass;

    private String mobPayPass;

    private String auditStatus;

    private String chainUrl;

    private String chainRemark;

    private BigDecimal id;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String updateUserSystem;

    private String ivrStatus;

    private String ivrLoginPassword;

    private String ivrPayPassword;

    private BigDecimal introduceAccountId;

    private String activateStatus;

    private BigDecimal totalScores;

    private BigDecimal sumamount;

    private String authenticationStatus;

    private String encryptcode;

    private BigDecimal tranAmount;

    private String authenticationName;

    private String authenticationIdCode;

    private String emStatus;

    private String resetPwdCode;

    private String companyType;

    private Object authenticationReject;

    private Date updatePwdTime;

    private BigDecimal dynamicKey;

    private Date idExpiredDate;

    private String occupation;

    private String nationality;

    private String leftMessage;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword == null ? null : payPassword.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode == null ? null : idCode.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public BigDecimal getProvSeq() {
        return provSeq;
    }

    public void setProvSeq(BigDecimal provSeq) {
        this.provSeq = provSeq;
    }

    public BigDecimal getCitySeq() {
        return citySeq;
    }

    public void setCitySeq(BigDecimal citySeq) {
        this.citySeq = citySeq;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode == null ? null : activeCode.trim();
    }

    public String getOfferPerson() {
        return offerPerson;
    }

    public void setOfferPerson(String offerPerson) {
        this.offerPerson = offerPerson == null ? null : offerPerson.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getDefaultSubSeq() {
        return defaultSubSeq;
    }

    public void setDefaultSubSeq(BigDecimal defaultSubSeq) {
        this.defaultSubSeq = defaultSubSeq;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getInformMobile() {
        return informMobile;
    }

    public void setInformMobile(String informMobile) {
        this.informMobile = informMobile == null ? null : informMobile.trim();
    }

    public String getMobStatus() {
        return mobStatus;
    }

    public void setMobStatus(String mobStatus) {
        this.mobStatus = mobStatus == null ? null : mobStatus.trim();
    }

    public String getMobActiveCode() {
        return mobActiveCode;
    }

    public void setMobActiveCode(String mobActiveCode) {
        this.mobActiveCode = mobActiveCode == null ? null : mobActiveCode.trim();
    }

    public String getMobLoginPass() {
        return mobLoginPass;
    }

    public void setMobLoginPass(String mobLoginPass) {
        this.mobLoginPass = mobLoginPass == null ? null : mobLoginPass.trim();
    }

    public String getMobPayPass() {
        return mobPayPass;
    }

    public void setMobPayPass(String mobPayPass) {
        this.mobPayPass = mobPayPass == null ? null : mobPayPass.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getChainUrl() {
        return chainUrl;
    }

    public void setChainUrl(String chainUrl) {
        this.chainUrl = chainUrl == null ? null : chainUrl.trim();
    }

    public String getChainRemark() {
        return chainRemark;
    }

    public void setChainRemark(String chainRemark) {
        this.chainRemark = chainRemark == null ? null : chainRemark.trim();
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public String getUpdateUserSystem() {
        return updateUserSystem;
    }

    public void setUpdateUserSystem(String updateUserSystem) {
        this.updateUserSystem = updateUserSystem == null ? null : updateUserSystem.trim();
    }

    public String getIvrStatus() {
        return ivrStatus;
    }

    public void setIvrStatus(String ivrStatus) {
        this.ivrStatus = ivrStatus == null ? null : ivrStatus.trim();
    }

    public String getIvrLoginPassword() {
        return ivrLoginPassword;
    }

    public void setIvrLoginPassword(String ivrLoginPassword) {
        this.ivrLoginPassword = ivrLoginPassword == null ? null : ivrLoginPassword.trim();
    }

    public String getIvrPayPassword() {
        return ivrPayPassword;
    }

    public void setIvrPayPassword(String ivrPayPassword) {
        this.ivrPayPassword = ivrPayPassword == null ? null : ivrPayPassword.trim();
    }

    public BigDecimal getIntroduceAccountId() {
        return introduceAccountId;
    }

    public void setIntroduceAccountId(BigDecimal introduceAccountId) {
        this.introduceAccountId = introduceAccountId;
    }

    public String getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(String activateStatus) {
        this.activateStatus = activateStatus == null ? null : activateStatus.trim();
    }

    public BigDecimal getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(BigDecimal totalScores) {
        this.totalScores = totalScores;
    }

    public BigDecimal getSumamount() {
        return sumamount;
    }

    public void setSumamount(BigDecimal sumamount) {
        this.sumamount = sumamount;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(String authenticationStatus) {
        this.authenticationStatus = authenticationStatus == null ? null : authenticationStatus.trim();
    }

    public String getEncryptcode() {
        return encryptcode;
    }

    public void setEncryptcode(String encryptcode) {
        this.encryptcode = encryptcode == null ? null : encryptcode.trim();
    }

    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(BigDecimal tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getAuthenticationName() {
        return authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName == null ? null : authenticationName.trim();
    }

    public String getAuthenticationIdCode() {
        return authenticationIdCode;
    }

    public void setAuthenticationIdCode(String authenticationIdCode) {
        this.authenticationIdCode = authenticationIdCode == null ? null : authenticationIdCode.trim();
    }

    public String getEmStatus() {
        return emStatus;
    }

    public void setEmStatus(String emStatus) {
        this.emStatus = emStatus == null ? null : emStatus.trim();
    }

    public String getResetPwdCode() {
        return resetPwdCode;
    }

    public void setResetPwdCode(String resetPwdCode) {
        this.resetPwdCode = resetPwdCode == null ? null : resetPwdCode.trim();
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public Object getAuthenticationReject() {
        return authenticationReject;
    }

    public void setAuthenticationReject(Object authenticationReject) {
        this.authenticationReject = authenticationReject;
    }

    public Date getUpdatePwdTime() {
        return updatePwdTime;
    }

    public void setUpdatePwdTime(Date updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    public BigDecimal getDynamicKey() {
        return dynamicKey;
    }

    public void setDynamicKey(BigDecimal dynamicKey) {
        this.dynamicKey = dynamicKey;
    }

    public Date getIdExpiredDate() {
        return idExpiredDate;
    }

    public void setIdExpiredDate(Date idExpiredDate) {
        this.idExpiredDate = idExpiredDate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(String leftMessage) {
        this.leftMessage = leftMessage == null ? null : leftMessage.trim();
    }
}