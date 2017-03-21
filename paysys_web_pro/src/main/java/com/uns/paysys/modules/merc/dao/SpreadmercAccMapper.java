package com.uns.paysys.modules.merc.dao;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.SpreadmercAcc;
import com.uns.paysys.modules.merc.form.AssignForm;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface SpreadmercAccMapper {



    int insert(SpreadmercAcc record);

    int insertSelective(SpreadmercAcc record);



    int updateByPrimaryKeySelective(SpreadmercAcc record);

    int updateByPrimaryKey(SpreadmercAcc record);
    
    
    int deleteAccount(BigDecimal smId);
    
    int updateAuditStatus(SpreadmercAcc record);
}