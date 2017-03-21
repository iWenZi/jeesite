package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AuditHistory;
@MyBatisDao
public interface AuditHistoryMapper {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditHistory record);

    int insertSelective(AuditHistory record);

    AuditHistory selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditHistory record);

    int updateByPrimaryKey(AuditHistory record);
    
    //获取审核历史
    List<Map<String, Object>> getAuditHis(Map<String, Object> map);
    
    
}