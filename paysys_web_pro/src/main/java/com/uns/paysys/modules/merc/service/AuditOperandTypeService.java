package com.uns.paysys.modules.merc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.paysys.modules.merc.dao.AuditOperandTypeMapper;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
@Service
public class AuditOperandTypeService {
	
	@Autowired
	private AuditOperandTypeMapper auditOperandTypeMapper;
	
	public AuditOperandType getAuditOperandType(String operandClass, String actionFlag){
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("operandClass", operandClass);
		param.put("actionFlag", actionFlag);
		return auditOperandTypeMapper.getAuditOperandType(param);
	}

}
