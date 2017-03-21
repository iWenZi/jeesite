package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.AccountMapper;
import com.uns.paysys.modules.merc.dao.AssignDao;
import com.uns.paysys.modules.merc.dao.SpreadMerchantDao;
import com.uns.paysys.modules.merc.dao.SpreadmercAccMapper;
import com.uns.paysys.modules.merc.entity.Account;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadmercAcc;
import com.uns.paysys.modules.merc.form.AssignForm;

/**
 * 商户分配Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SpreadmercAccService {

	@Autowired
	private SpreadmercAccMapper spreadmercAccMapper;

	/**
	 * 推广商分配商户
	 * 
	 * @return
	 */
	public int saveAcc(SpreadmercAcc spreadmercAcc) {
		return spreadmercAccMapper.insertSelective(spreadmercAcc);

	}
	
	
	/**
	 * 移除商户
	 * 
	 * @return
	 */
	public int deleteAccount(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		return spreadmercAccMapper.deleteAccount(bid);
	}

	/**
	 * 修改推广商状态
	 * 
	 * @return
	 */
	public int updateAuditStatus(SpreadmercAcc spreadmercAcc) {
		return spreadmercAccMapper.updateAuditStatus(spreadmercAcc);

	}
	
}
