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
import com.uns.paysys.modules.merc.dao.BankInfoDao;
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
public class BankInfoService extends CrudService<BankInfoDao, BankInfo> {

	@Autowired
	private BankInfoDao bankInfoDao;

	/**
	 * 查询所有银行
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<BankInfo> findAllBank() {
		return bankInfoDao.findAllBank();

	}

}
