package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
@MyBatisDao
public interface AuditOperandTypeMapper extends CrudDao<AuditOperandType>{

    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditOperandType record);

    int insertSelective(AuditOperandType record);

    AuditOperandType selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditOperandType record);

    int updateByPrimaryKey(AuditOperandType record);
    
    AuditOperandType getAuditOperandType(Map<String, Object> param);
}