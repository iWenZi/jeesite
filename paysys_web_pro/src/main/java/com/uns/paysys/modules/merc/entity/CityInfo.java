package com.uns.paysys.modules.merc.entity;

import java.util.Date;

import com.uns.paysys.common.persistence.DataEntity;

/**
 * 交易类型实体类
 * 
 * @author Administrator
 *
 */
public class CityInfo extends DataEntity<CityInfo> {

	private static final long serialVersionUID = 1L;


	private String cityId;
	private String cityName;
	private String supcityId;
	private String insideId;
	private Long cityLevel;
	private String validFlag;
	private String remark;
	private Long listSeq;
	private Long Id;
	private Long version;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getSupcityId() {
		return supcityId;
	}
	public void setSupcityId(String supcityId) {
		this.supcityId = supcityId;
	}
	public String getInsideId() {
		return insideId;
	}
	public void setInsideId(String insideId) {
		this.insideId = insideId;
	}
	public Long getCityLevel() {
		return cityLevel;
	}
	public void setCityLevel(Long cityLevel) {
		this.cityLevel = cityLevel;
	}
	public String getValidFlag() {
		return validFlag;
	}
	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getListSeq() {
		return listSeq;
	}
	public void setListSeq(Long listSeq) {
		this.listSeq = listSeq;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
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


}
