package com.uns.paysys.modules.merc.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.paysys.modules.merc.dao.AuditHistoryMapper;
import com.uns.paysys.modules.merc.entity.AuditHistory;
import com.uns.paysys.modules.merc.entity.AuditOperation;

/**
 * 审核历史Service
 * @author Administrator
 *
 */
@Service
public class AuditHistoryService {
	
	@Autowired
	private AuditHistoryMapper auditHistoryMapper;
	
	/**
	 * 查询费率审核历史
	 * @return
	 */
	public List<Map<String, Object>> getAuditHis(Long id, String operandClass, String actionFlag){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("operandClass", operandClass);
		param.put("actionFlag", actionFlag);
		//查询费率审核历史
		return auditHistoryMapper.getAuditHis(param);
	}
	
	/**
	 * 插入审核历史
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void insertHisOperation(AuditOperation operation) {
		
		AuditHistory history = new AuditHistory();
		try {
			PropertyUtils.copyProperties(history, operation);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		history.setSequenceNo(BigDecimal.valueOf(0));//创建时序列为0
		auditHistoryMapper.insertSelective(history);
		
	}
	
	
	

}
