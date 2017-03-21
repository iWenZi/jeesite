package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AuditStep;
@MyBatisDao
public interface AuditStepMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditStep record);

    int insertSelective(AuditStep record);

    AuditStep selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditStep record);

    int updateByPrimaryKey(AuditStep record);
    
    List<AuditStep> getAuditStep(Long ruleId);
    
    int deleteByAuditRuleId(Long auditRuleId);
}