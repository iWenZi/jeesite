package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.BankInfo;
import com.uns.paysys.modules.merc.entity.CityInfo;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadMerchantBankInfo;
import com.uns.paysys.modules.merc.form.SpreadMerchantBankInfoForm;
import com.uns.paysys.modules.merc.form.SpreadMerchantForm;

@MyBatisDao
public interface SpreadMerchantBankInfoDao extends CrudDao<SpreadMerchantBankInfo> {

	
	public int inertBank(SpreadMerchantBankInfo spreadMerchantBankInfo);
	
	public int deleteBankInfo(BigDecimal id);
	
	public	SpreadMerchantBankInfo selectBankByPrimaryKey(BigDecimal id);
	
	int updateByPrimaryKeySelective(SpreadMerchantBankInfo record);
}
