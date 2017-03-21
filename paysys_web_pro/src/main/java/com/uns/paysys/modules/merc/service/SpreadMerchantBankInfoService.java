package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.modules.merc.dao.SpreadMerchantBankInfoDao;
import com.uns.paysys.modules.merc.dao.SpreadMerchantInfoDao;
import com.uns.paysys.modules.merc.entity.BankInfo;
import com.uns.paysys.modules.merc.entity.CityInfo;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadMerchantBankInfo;
import com.uns.paysys.modules.merc.form.SpreadMerchantBankInfoForm;
import com.uns.paysys.modules.sys.entity.Dict;
import com.uns.paysys.modules.sys.utils.DictUtils;

/**
 * 推广商Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SpreadMerchantBankInfoService extends CrudService<SpreadMerchantBankInfoDao, SpreadMerchantBankInfo> {

	@Autowired
	private SpreadMerchantBankInfoDao spreadMerchantBankInfoDao;

	

	/**
	 * 新增银行
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public int inertBank(SpreadMerchantBankInfo spreadMerchantBankInfo) {
		return spreadMerchantBankInfoDao.inertBank(spreadMerchantBankInfo);

	}
	
	
	/**
	 * 根据主键id查询推广商银行信息
	 * @param id
	 * @return
	 */
	public SpreadMerchantBankInfo getSpreadMerchantBankInfoById(Long id){
		BigDecimal bid = BigDecimal.valueOf(id);
		return spreadMerchantBankInfoDao.selectBankByPrimaryKey(bid);
	}
	

	
	/**
	 * 删除银行信息
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteBankInfo(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		spreadMerchantBankInfoDao.deleteBankInfo(bid);
	}
	
	
	/**
	 * 修改推广商银行
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateSpreadMerchantBankInfo(SpreadMerchantBankInfo spreadMerchantBankInfo) {
		 return spreadMerchantBankInfoDao.updateByPrimaryKeySelective(spreadMerchantBankInfo);
	}
	
	
}
