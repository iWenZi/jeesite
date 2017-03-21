package com.uns.paysys.modules.merc.dao;

import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.SpreadMerchantForm;
@MyBatisDao
public interface SpreadMerchantDao extends CrudDao<SpreadMerchant> {
	


	/**
	 * 推广商费率列表
	 * @param form
	 * @return
	 */
	public List<Map<String , Object>> findFeeList(SpreadMerchantForm form);
	
	/**推广商费率历史列表
	 * @param form
	 * @return
	 */
	public List<Map<String , Object>> findHisFeeList(SpreadMerchantForm form);
	
	/**获取推广商id name 映射 List
	 * @param flag
	 * @return
	 */
	public List<SpreadMerchant> getSpreadMerchant(String flag);
	

	/**查询费率审核历史
	 * @param id
	 * @return
	 */
	public List<Map<String , Object>> getAuditHis(Long id, String operandClass, String actionFlag);
	
}
