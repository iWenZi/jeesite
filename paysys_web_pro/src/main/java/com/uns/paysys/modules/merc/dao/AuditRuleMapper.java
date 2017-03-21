package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AuditRule;
@MyBatisDao
public interface AuditRuleMapper extends CrudDao<AuditRule> {


    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditRule record);

    int insertSelective(AuditRule record);

    AuditRule selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditRule record);

    int updateByPrimaryKey(AuditRule record);
    
    AuditRule getAuditRule(Long auditOperandTypeId);
    
    List<Long> checkExist(BigDecimal auditOperandTypeId);
}