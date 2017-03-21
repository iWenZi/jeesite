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
public class AccountService {

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * 查询所有审核通过的商户信息
	 * 
	 * @return
	 */
	public List<Account> allAccountList(Account account) {

		return accountMapper.accounts(account);

	}
	
	

	/**
	 * 查询推广商已经分配的商户信息
	 * 
	 * @return
	 */
	public List<Account> allAccountList(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		return accountMapper.findAccountBySmId(bid);

	}
	
	
	
	/**
	 * 查询商户defaultSubSeq
	 * 
	 * @return
	 */
	public Account findDefaultSubSeq(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		return accountMapper.findById(bid);

	}

}
