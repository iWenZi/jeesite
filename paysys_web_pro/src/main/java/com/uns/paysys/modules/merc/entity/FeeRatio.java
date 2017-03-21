package com.uns.paysys.modules.merc.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.uns.paysys.common.persistence.DataEntity;

public class FeeRatio extends DataEntity<FeeRatio> {
	
	private static final long serialVersionUID = 1L;

	private BigDecimal actionSeq;

    private String relatingType;

    private String relatingId;

    private String finVersion;

    private String subType;

    private BigDecimal fixFee;

    private BigDecimal ratio;

    private BigDecimal minFee;

    private BigDecimal maxFee;

    private String flag;

    private Date beginDate;

    private Date endDate;

    private String feeMethod;

    private BigDecimal version;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private BigDecimal stairsSeq;
    
    private String auditStatus;



    @NotNull(message="交易类型不能为空")
    public BigDecimal getActionSeq() {
        return actionSeq;
    }

    public void setActionSeq(BigDecimal actionSeq) {
        this.actionSeq = actionSeq;
    }
    @NotEmpty(message="相关类型不能为空")
    public String getRelatingType() {
        return relatingType;
    }
    public void setRelatingType(String relatingType) {
        this.relatingType = relatingType == null ? null : relatingType.trim();
    }
    
    @NotEmpty
    public String getRelatingId() {
        return relatingId;
    }

    public void setRelatingId(String relatingId) {
        this.relatingId = relatingId == null ? null : relatingId.trim();
    }

    public String getFinVersion() {
        return finVersion;
    }

    public void setFinVersion(String finVersion) {
        this.finVersion = finVersion == null ? null : finVersion.trim();
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    public BigDecimal getFixFee() {
        return fixFee;
    }

    public void setFixFee(BigDecimal fixFee) {
        this.fixFee = fixFee;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getMinFee() {
        return minFee;
    }

    public void setMinFee(BigDecimal minFee) {
        this.minFee = minFee;
    }

    public BigDecimal getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(BigDecimal maxFee) {
        this.maxFee = maxFee;
    }
    @NotEmpty
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @NotEmpty
    public String getFeeMethod() {
        return feeMethod;
    }

    public void setFeeMethod(String feeMethod) {
        this.feeMethod = feeMethod == null ? null : feeMethod.trim();
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

    public BigDecimal getStairsSeq() {
        return stairsSeq;
    }

    public void setStairsSeq(BigDecimal stairsSeq) {
        this.stairsSeq = stairsSeq;
    }

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
    
}