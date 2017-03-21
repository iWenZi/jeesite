package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AuditOperation;
@MyBatisDao
public interface AuditOperationMapper {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditOperation record);

    int insertSelective(AuditOperation record);

    AuditOperation selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditOperation record);

    int updateByPrimaryKey(AuditOperation record);
    
    AuditOperation getAuditOperation(Map<String, Object> map);
    
    List<Long> checkAuditPrivilege(Map<String, Object> param);
}