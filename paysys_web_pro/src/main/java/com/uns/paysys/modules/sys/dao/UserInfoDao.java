package com.uns.paysys.modules.sys.dao;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.entity.UserInfo;

/**
 * 用户DAO接口
 * 
 * 
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public UserInfo getByLoginName(UserInfo user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(UserInfo user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(UserInfo user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(UserInfo user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(UserInfo user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(UserInfo user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(UserInfo user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateByPrimaryKeySelective(UserInfo user);

}
