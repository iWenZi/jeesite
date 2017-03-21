package com.uns.paysys.modules.sys.dao;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.sys.entity.ActionType;

/**
 * 交易类型DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface ActionTypeDao extends CrudDao<ActionType> {
	

}
