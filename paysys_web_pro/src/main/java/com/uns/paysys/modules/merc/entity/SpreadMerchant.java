package com.uns.paysys.modules.merc.entity;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.uns.paysys.common.persistence.DataEntity;

public class SpreadMerchant extends DataEntity<SpreadMerchant> {
	private static final long serialVersionUID = 1L;

	private Long actionSeq;
	private Long feeMethod;
	private String email; // 邮箱
	private String merchantName;// 推广商名称
	private String licenceNo;// 营业执照号或统一社会信用代码
	private Date licenceNoDate;// 经营期限
	private String organizeNo;// 组织机构代码证代码
	private String taxId;// 税务登记证税字号
	private String organizeTrustNo;// 机构信用代码证代码
	private Date organizeTrustNoDate;// 机构信用代码证有效期
	private String accountLicenceNo;// 开户许可证核准号
	private String salesMan;// 销售人员
	private String capitalAmount;// 注册资本
	private Date foundedDate;// 成立日期
	private String legalRepreName;// 法定代表人
	private String legalRepreMobile;// 法定代表人手机号码
	private String legalRepreIdNo;// 法定代表人身份证号码
	private Date legalRepreIdDate;// 法定代表人身份证有效期
	private String regiAddr;// 注册地址
	private String linkAddr;// 联系地址
	private String operator;// 经办人
	private String operatorTel;// 经办人联系电话
	private String spreadMerchantType;// 推广商户类型
	private String dealRange;// 经营范围
	private String delFlag;// 删除标志
	private String merchantStatus;// 商户状态
	private String auditStatus;// 审核状态
	private String createUser;// 创建人
	private Date createTime;// 创建日期
	private String updateUser;// 修改人
	private Date updateTime;// 修改日期
	private String assignStatus;// 分配状态
	// 银行信息
	private String accountName;
	private String accountAlias;
	private Long provSeq;
	private Long citySeq;
	private Long bankSeq;
	private String openAccountBankName;
	private String cardNo;
	private String cardType;
	
	
	//修改备注
	private String remark;
	
	 private MultipartFile [] multipartFiles;
	 
	 private MultipartFile [] files;
	 
	 
		public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		public Long getFeeMethod() {
			return feeMethod;
		}

		public void setFeeMethod(Long feeMethod) {
			this.feeMethod = feeMethod;
		}

		public Long getActionSeq() {
			return actionSeq;
		}

		public void setActionSeq(Long actionSeq) {
			this.actionSeq = actionSeq;
		}


		public String getAssignStatus() {
		return assignStatus;
	}

	public void setAssignStatus(String assignStatus) {
		this.assignStatus = assignStatus;
	}

		public SpreadMerchant(){
			multipartFiles = new MultipartFile[100];
	    }
	    
	    public MultipartFile getFile(int i) {
	    	return multipartFiles[i];
	    }


	    public void setFile( int i,MultipartFile multipartFiles) {
	    	this.multipartFiles[i] = multipartFiles;
	    }
	
    public MultipartFile[] getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(MultipartFile[] multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	private File[] file;
    

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}

	public Date getLicenceNoDate() {
		return licenceNoDate;
	}

	public void setLicenceNoDate(Date licenceNoDate) {
		this.licenceNoDate = licenceNoDate;
	}

	public String getOrganizeNo() {
		return organizeNo;
	}

	public void setOrganizeNo(String organizeNo) {
		this.organizeNo = organizeNo;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getOrganizeTrustNo() {
		return organizeTrustNo;
	}

	public void setOrganizeTrustNo(String organizeTrustNo) {
		this.organizeTrustNo = organizeTrustNo;
	}

	public Date getOrganizeTrustNoDate() {
		return organizeTrustNoDate;
	}

	public void setOrganizeTrustNoDate(Date organizeTrustNoDate) {
		this.organizeTrustNoDate = organizeTrustNoDate;
	}

	public String getAccountLicenceNo() {
		return accountLicenceNo;
	}

	public void setAccountLicenceNo(String accountLicenceNo) {
		this.accountLicenceNo = accountLicenceNo;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getCapitalAmount() {
		return capitalAmount;
	}

	public void setCapitalAmount(String capitalAmount) {
		this.capitalAmount = capitalAmount;
	}

	public Date getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(Date foundedDate) {
		this.foundedDate = foundedDate;
	}

	public String getLegalRepreName() {
		return legalRepreName;
	}

	public void setLegalRepreName(String legalRepreName) {
		this.legalRepreName = legalRepreName;
	}

	public String getLegalRepreMobile() {
		return legalRepreMobile;
	}

	public void setLegalRepreMobile(String legalRepreMobile) {
		this.legalRepreMobile = legalRepreMobile;
	}

	public String getLegalRepreIdNo() {
		return legalRepreIdNo;
	}

	public void setLegalRepreIdNo(String legalRepreIdNo) {
		this.legalRepreIdNo = legalRepreIdNo;
	}

	public Date getLegalRepreIdDate() {
		return legalRepreIdDate;
	}

	public void setLegalRepreIdDate(Date legalRepreIdDate) {
		this.legalRepreIdDate = legalRepreIdDate;
	}

	public String getRegiAddr() {
		return regiAddr;
	}

	public void setRegiAddr(String regiAddr) {
		this.regiAddr = regiAddr;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorTel() {
		return operatorTel;
	}

	public void setOperatorTel(String operatorTel) {
		this.operatorTel = operatorTel;
	}

	public String getSpreadMerchantType() {
		return spreadMerchantType;
	}

	public void setSpreadMerchantType(String spreadMerchantType) {
		this.spreadMerchantType = spreadMerchantType;
	}

	public String getDealRange() {
		return dealRange;
	}

	public void setDealRange(String dealRange) {
		this.dealRange = dealRange;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getMerchantStatus() {
		return merchantStatus;
	}

	public void setMerchantStatus(String merchantStatus) {
		this.merchantStatus = merchantStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountAlias() {
		return accountAlias;
	}

	public void setAccountAlias(String accountAlias) {
		this.accountAlias = accountAlias;
	}

	public Long getProvSeq() {
		return provSeq;
	}

	public void setProvSeq(Long provSeq) {
		this.provSeq = provSeq;
	}

	public Long getCitySeq() {
		return citySeq;
	}

	public void setCitySeq(Long citySeq) {
		this.citySeq = citySeq;
	}

	public Long getBankSeq() {
		return bankSeq;
	}

	public void setBankSeq(Long bankSeq) {
		this.bankSeq = bankSeq;
	}

	public String getOpenAccountBankName() {
		return openAccountBankName;
	}

	public void setOpenAccountBankName(String openAccountBankName) {
		this.openAccountBankName = openAccountBankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
