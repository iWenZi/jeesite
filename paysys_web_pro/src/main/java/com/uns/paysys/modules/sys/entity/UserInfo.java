package com.uns.paysys.modules.sys.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.uns.paysys.common.config.Global;
import com.uns.paysys.common.persistence.DataEntity;
import com.uns.paysys.common.supcan.annotation.treelist.cols.SupCol;
import com.uns.paysys.common.utils.Collections3;
import com.uns.paysys.common.utils.excel.annotation.ExcelField;
import com.uns.paysys.common.utils.excel.fieldtype.RoleListType;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserInfo extends DataEntity<UserInfo> {

	private static final long serialVersionUID = 1L;
	private String userId;//用户编号
	private String userName;	// 姓名
	private String loginName;// 登录名
	private String password;// 密码
	private String tel;	// 电话
	private String userType;// 用户类型
	private String flag;
	private String activeCode;
	private String offerPerson;//推荐人
	private String mobile;	// 手机
	private String status;
	private String remark;
	private String sessionId;
	private String email;	// 邮箱
	private Long id;
	private Long version;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	
	private Role role;	// 根据角色查询用户条件
	
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

//	public UserInfo() {
//		super();
//	}
//	
//	public UserInfo(Long id){
//		super(id);
//	}
//
//	public UserInfo(Long id, String loginName){
//		super(id);
//		this.loginName = loginName;
//	}
//
//	public UserInfo(Role role){
//		super();
//		this.role = role;
//	}
//	@SupCol(isUnique="true", isHide="true")
//	@ExcelField(title="ID", type=1, align=2, sort=1)
//	public Long getId() {
//		return id;
//	}

	public void setId(Long id) {
		this.id = id;
	}
	@ExcelField(title="用户编号", type=1, align=2, sort=2)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getOfferPerson() {
		return offerPerson;
	}

	public void setOfferPerson(String offerPerson) {
		this.offerPerson = offerPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	@ExcelField(title="登录名", align=2, sort=30)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JsonIgnore
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return userName;
	}
	
	public void setName(String userName) {
		this.userName = userName;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return tel;
	}

	public void setPhone(String phone) {
		this.tel = phone;
	}

	@Length(min=0, max=200, message="手机长度必须介于 1 和 200 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}
	
	@Length(min=0, max=100, message="用户类型长度必须介于 1 和 100 之间")
	@ExcelField(title="用户类型", align=2, sort=80, dictType="sys_user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@ExcelField(title="创建时间", type=0, align=1, sort=90)
	public Date getCreateDate() {
		return createDate;
	}


//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@ExcelField(title="最后登录日期", type=1, align=1, sort=110)
//	public Date getLoginDate() {
//		return loginDate;
//	}
//
//	public void setLoginDate(Date loginDate) {
//		this.loginDate = loginDate;
//	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	@ExcelField(title="拥有角色", align=1, sort=800, fieldType=RoleListType.class)
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

//	@JsonIgnore
//	public List<Long> getRoleIdList() {
//		List<Long> roleIdList = Lists.newArrayList();
//		for (Role role : roleList) {
//			roleIdList.add(role.getId());
//		}
//		return roleIdList;
//	}
//
//	public void setRoleIdList(List<Long> roleIdList) {
//		roleList = Lists.newArrayList();
//		for (Long roleId : roleIdList) {
//			Role role = new Role();
//			role.setId(roleId);
//			roleList.add(role);
//		}
//	}
	
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}
	
}