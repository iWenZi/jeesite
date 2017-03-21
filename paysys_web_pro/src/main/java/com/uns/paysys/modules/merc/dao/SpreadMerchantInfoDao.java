package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.SpreadMerchantBankInfoForm;

@MyBatisDao
public interface SpreadMerchantInfoDao extends CrudDao<SpreadMerchant> {

	
	public List<Map<String , Object>> findList(SpreadMerchantBankInfoForm form);

	public	SpreadMerchant selectByPrimaryKey(BigDecimal id);
	
	public void saveSpreadMerchant(SpreadMerchant merchant) ;
	
	int updateByPrimaryKeySelective(SpreadMerchant record);
	
	
	public	SpreadMerchant getSpreadMerchantByEmail(String email);
	
	public	SpreadMerchant getSpreadMerchantByMerchantName(String merchantName);
	
	public	SpreadMerchant getSpreadMerchantByLicenceNo(String licenceNo);
	
	public	SpreadMerchant getSpreadMerchantByOrganizeNo(String organizeNo);
	
	public	SpreadMerchant getSpreadMerchantByTaxId(String taxId);
	
	public	SpreadMerchant getSpreadMerchantByOrganizeTrustNo(String organizeTrustNo);
	
	public	SpreadMerchant getSpreadMerchantByAccountLicenceNo(String accountLicenceNo);
}
