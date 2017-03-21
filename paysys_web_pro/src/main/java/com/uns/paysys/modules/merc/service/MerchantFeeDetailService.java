package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.common.utils.DateUtils;
import com.uns.paysys.modules.merc.dao.MerchantFeeDetailDao;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetail;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetailSum;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailForm;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailSumForm;

@Service
@Transactional(readOnly = true)	//只能读取 不能修改数据
public class MerchantFeeDetailService extends CrudService<MerchantFeeDetailDao, MerchantFeeDetail>{

	@Autowired
	private MerchantFeeDetailDao merchantFeeDetailDao;
	
	public MerchantFeeDetail getMerchant(Long id){
		return merchantFeeDetailDao.get(id);
	}
	
	/**
	 * 推广商交易查询
	 * @param page
	 * @param merchantFeeDetail
	 * @return
	 */
	public Map<String, Object> findMerchant(Page<Map<String , Object>> page,MerchantFeeDetailForm form){
		// 设置默认时间范围，默认昨天
		if (form.getBeginDate() == null){
			form.setBeginDate(DateUtils.addDays(DateUtils.parseDate(DateUtils.getDate()), -1));
		}
		if (form.getEndDate() == null){
			form.setEndDate(DateUtils.addDays(DateUtils.parseDate(DateUtils.getDate()), 0));
		}

		//查询费用汇总
		Double sumFee = getFee(form);
		// 设置分页参数,查询分页数据
		form.setMapPage(page);
		List<Map<String, Object>>  list = merchantFeeDetailDao.findMerchantFeeList(form);
		page.setList(list);
		
		//定义返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("sumFee", sumFee);
		return map;
	}
	
	/**
	 * 推广商交易汇总
	 * @param page
	 * @param merchantFeeDetail
	 * @return
	 */
	public Map<String , Object> getSumMerchant(Page<Map<String , Object>> page,MerchantFeeDetailSumForm form){
		// 设置默认时间范围，默认当前月
		if (form.getBeginDate() == null){
			form.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (form.getEndDate() == null){
			form.setEndDate(DateUtils.addMonths(form.getBeginDate(), 1));
		}
		
		Double sumFee = getFeeSum(form);
		
		// 设置分页参数，查询分页数据
		form.setMapPage(page);
		List<Map<String, Object>> list = merchantFeeDetailDao.getSumMerchantFee(form);
		page.setList(list);
		
		//封装返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page",page);
		map.put("sumFee", sumFee);
		return map;
	}

	/**
	 * 根据id查询推广商交易明细
	 * @param id
	 * @return
	 */
	public Map getViewMerchantById(Long id){
		BigDecimal bd = BigDecimal.valueOf(id);
		return merchantFeeDetailDao.viewMerchantFee(bd);
	}
	
	/**
	 * 导出推广商交易数据
	 * @param page
	 * @return
	 */
	public Page<MerchantFeeDetail> exportMerchant(Page<MerchantFeeDetail> page,MerchantFeeDetail merchantFeeDetail){
		// 设置分页参数
//		merchantFeeDetail.setPage(page);
		page.setList(merchantFeeDetailDao.export(merchantFeeDetail));
		return page;
	}
	
	/**
	 * 导出推广商汇总交易数据
	 * @param page
	 * @param merchantFeeDetail
	 * @return
	 */
	public Page<MerchantFeeDetailSum> exportMerchantSum(Page<MerchantFeeDetailSum> page,MerchantFeeDetailSum merchantFeeDetailSum){
		// 设置分页参数
		merchantFeeDetailSum.setPage(page);
		List<MerchantFeeDetailSum> list = merchantFeeDetailDao.exportSum(merchantFeeDetailSum);
		page.setList(list);
		return page;
	}
	
	/**
	 * jsp页面显示推广费总额
	 * @return
	 */
	public Double getFee(MerchantFeeDetailForm form){
		return merchantFeeDetailDao.getMerchantFeeSum(form);
	}
	
	/**
	 * jsp页面显示汇总推广费总额
	 * @param form
	 * @return
	 */
	public Double getFeeSum(MerchantFeeDetailSumForm form){
		return merchantFeeDetailDao.getMerchantSumFee(form);
	}
	
}
