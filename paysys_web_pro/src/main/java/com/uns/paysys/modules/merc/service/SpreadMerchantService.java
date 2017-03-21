package com.uns.paysys.modules.merc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.modules.merc.dao.SpreadMerchantDao;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.SpreadMerchantForm;
import com.uns.paysys.modules.sys.utils.DictUtils;


/**
 * 推广商Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class SpreadMerchantService extends CrudService<SpreadMerchantDao, SpreadMerchant> {
	
	@Autowired
	private SpreadMerchantDao spreadMerchantDao;
	
	public SpreadMerchant getSpreadMerchant(Long id) {
		return spreadMerchantDao.get(id);
	}
	
	public SpreadMerchant findSpreadMerchant(Long id) {
		return spreadMerchantDao.get(id);
	}
	
	/**
	 * 分页查询推广商列表
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public Page<SpreadMerchant> findSpreadMerchant(Page<SpreadMerchant> page, SpreadMerchant spreadMerchant) {
		// 设置分页参数
		spreadMerchant.setPage(page);
		// 执行分页查询
		page.setList(spreadMerchantDao.findList(spreadMerchant));
		return page;
	}

	
	/**
	 * 查询推广商费率列表
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public Page<Map<String , Object>> findSpreadMerchantFee(Page<Map<String , Object>> page, SpreadMerchantForm form) {
		// 设置分页参数
		form.setMapPage(page);
		// 执行分页查询
		page.setList(spreadMerchantDao.findFeeList(form));
		return page;
	}
	
	/**
	 * 查询推广商费率列表
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public Page<Map<String , Object>> findSpreadMerchantHisFee(Page<Map<String , Object>> page, SpreadMerchantForm form) {
		// 设置分页参数
		form.setMapPage(page);
		// 执行分页查询
		page.setList(spreadMerchantDao.findHisFeeList(form));
		return page;
	}


	/**
	 * 查询费率审核历史
	 * @return
	 */
	public List<Map<String, Object>> getAuditHis(Long id){
		
		//查询费率审核历史
		return spreadMerchantDao.getAuditHis(id, DictConstUtil.FEE_RATIO , DictConstUtil.ACTION_FLAG_1);
		
	}
	
	
	

}
