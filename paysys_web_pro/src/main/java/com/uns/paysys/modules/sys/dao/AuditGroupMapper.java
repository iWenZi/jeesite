package com.uns.paysys.modules.sys.dao;

import java.math.BigDecimal;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.sys.entity.AuditGroup;


@MyBatisDao
public interface AuditGroupMapper  extends CrudDao<AuditGroup> {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(AuditGroup record);

    int insertSelective(AuditGroup record);

    AuditGroup selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(AuditGroup record);

    int updateByPrimaryKey(AuditGroup record);
    
    void deleteGroupUser(Long id);
    
    int saveGroupUser(AuditGroup auditGroup);
}