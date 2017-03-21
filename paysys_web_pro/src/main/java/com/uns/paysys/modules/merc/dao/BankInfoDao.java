package com.uns.paysys.modules.merc.dao;

import java.util.List;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.BankInfo;

@MyBatisDao
public interface BankInfoDao extends CrudDao<BankInfo> {

	public List<BankInfo> findAllBank();
	
}
