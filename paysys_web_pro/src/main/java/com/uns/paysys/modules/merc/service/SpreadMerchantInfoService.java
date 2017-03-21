package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.modules.merc.dao.SpreadMerchantInfoDao;
import com.uns.paysys.modules.merc.entity.BankInfo;
import com.uns.paysys.modules.merc.entity.CityInfo;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadMerchantBankInfo;
import com.uns.paysys.modules.merc.form.SpreadMerchantBankInfoForm;
import com.uns.paysys.modules.sys.entity.Dict;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.DictUtils;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 推广商Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SpreadMerchantInfoService extends CrudService<SpreadMerchantInfoDao, SpreadMerchant> {

	@Autowired
	private SpreadMerchantInfoDao spreadMerchantInfoDao;

	/*
	 * public SpreadMerchant getSpreadMerchant(Long id) { return
	 * spreadMerchantInfoDao.get(id); }
	 * 
	 * public SpreadMerchant findSpreadMerchant(Long id) { return
	 * spreadMerchantInfoDao.get(id); }
	 */
	/**
	 * 分页查询推广商列表
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public Page<SpreadMerchant> findSpreadMerchant(Page<SpreadMerchant> page, SpreadMerchant spreadMerchant) {
		// 设置分页参数
		spreadMerchant.setPage(page);
		// 执行分页查询
		page.setList(spreadMerchantInfoDao.findList(spreadMerchant));
		return page;
	}

	/**
	 * 查询推广商插入
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public void saveSpreadMerchant(SpreadMerchant spreadMerchant) {
		spreadMerchantInfoDao.saveSpreadMerchant(spreadMerchant);
	}

	
	
	/**
	 * 根据主键id查询推广商
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantById(Long id){
		BigDecimal bid = BigDecimal.valueOf(id);
		return spreadMerchantInfoDao.selectByPrimaryKey(bid);
	}
	
	
	/**
	 * 删除推广商
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public void deleteSpreadMerchant(SpreadMerchant spreadMerchant) {
		 spreadMerchantInfoDao.delete(spreadMerchant);
	}
	
	/**
	 * 修改推广商
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateSpreadMerchant(SpreadMerchant spreadMerchant) {
		 return spreadMerchantInfoDao.updateByPrimaryKeySelective(spreadMerchant);
	}
	
	
	/**
	 * 审核推广商
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public void update(SpreadMerchant spreadMerchant) {
		User user = UserUtils.getUser();
		spreadMerchant.setUpdateUser(user.getId().toString());
		Date date = new Date();
		Timestamp nousedate = new Timestamp(date.getTime());
		spreadMerchant.setUpdateTime(nousedate);
		spreadMerchantInfoDao.updateByPrimaryKeySelective(spreadMerchant);
	}
	
	
	/**
	 * 推广商email是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByEmail(String email){
		return spreadMerchantInfoDao.getSpreadMerchantByEmail(email);
	}
	
	/**
	 * 推广商名称是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByMerchantName(String email){
		return spreadMerchantInfoDao.getSpreadMerchantByMerchantName(email);
	}
	
	
	/**
	 * 推广商营业执照号或统一社会信用代码是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByLicenceNo(String licenceNo){
		return spreadMerchantInfoDao.getSpreadMerchantByLicenceNo(licenceNo);
	}
	
	
	
	/**
	 * 推广商组织机构代码证代码是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByOrganizeNo(String organizeNo){
		return spreadMerchantInfoDao.getSpreadMerchantByOrganizeNo(organizeNo);
	}
	
	
	
	/**
	 * 推广商组税务登记证税字号是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByTaxId(String taxId){
		return spreadMerchantInfoDao.getSpreadMerchantByTaxId(taxId);
	}
	
	
	/**
	 * 推广商机构信用代码证代码是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByOrganizeTrustNo(String organizeTrustNo){
		return spreadMerchantInfoDao.getSpreadMerchantByOrganizeTrustNo(organizeTrustNo);
	}
	
	/**
	 * 推广商机开户许可证核准号是否重复
	 * @param id
	 * @return
	 */
	public SpreadMerchant getSpreadMerchantByAccountLicenceNo(String accountLicenceNo){
		return spreadMerchantInfoDao.getSpreadMerchantByAccountLicenceNo(accountLicenceNo);
	}
	
}
