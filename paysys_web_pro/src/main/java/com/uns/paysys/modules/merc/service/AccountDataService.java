package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.AccountDataMapper;
import com.uns.paysys.modules.merc.dao.AssignDao;
import com.uns.paysys.modules.merc.dao.SpreadMerchantDao;
import com.uns.paysys.modules.merc.entity.AccountData;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.AssignForm;

/**
 * 推广商文件上传Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class AccountDataService {

	@Autowired
	private AccountDataMapper accountDataMapper;

	/**
	 * 推广商文件上传
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public int insertSelective(AccountData record) {
		return accountDataMapper.insertSelective(record);
	}

	public List<AccountData> getUrlFileName(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		return (List<AccountData>) accountDataMapper.selectBySmSeq(bid);
	}

	public int delUploadFile(String fileKey) {
		return accountDataMapper.updateFileState(fileKey);

	}
}
