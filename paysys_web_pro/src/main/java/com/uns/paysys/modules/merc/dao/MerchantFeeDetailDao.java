package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetail;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetailSum;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailForm;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailSumForm;

@MyBatisDao
public interface MerchantFeeDetailDao extends CrudDao<MerchantFeeDetail>{
	
	/**
	 * 查询推广商分润列表
	 * @param merchantFeeDetail
	 * @return
	 */
	public List<Map<String , Object>> findMerchantFeeList(MerchantFeeDetailForm form);
	
	/**
	 * 推广商分润汇总
	 * @param merchantFeeDetail
	 * @return
	 */
	public List<Map<String , Object>> getSumMerchantFee(MerchantFeeDetailSumForm form);
	
	/**
	 * 推广商交易明细查看
	 * @param form
	 * @return
	 */
	Map viewMerchantFee(BigDecimal id);
	
	/**
	 * 导出
	 * @param mrchantFeeDetail
	 * @return
	 */
	public List<MerchantFeeDetail> export(MerchantFeeDetail mrchantFeeDetail);
	
	
	/**
	 * 导出分润汇总
	 * @param merchantFeeDetailSum
	 * @return
	 */
	public List<MerchantFeeDetailSum> exportSum(MerchantFeeDetailSum merchantFeeDetailSum);
	
	//显示推广费总额
	public Double getMerchantFeeSum(MerchantFeeDetailForm form);
	
	
	public Double getMerchantSumFee(MerchantFeeDetailSumForm form);
}
