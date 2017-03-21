package com.uns.paysys.modules.merc.dao;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.AccountData;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface AccountDataMapper {


    int deleteByPrimaryKey(BigDecimal id);

    int insert(AccountData record);

    int insertSelective(AccountData record);


    AccountData selectByPrimaryKey(BigDecimal id);

    List<AccountData> selectBySmSeq(BigDecimal id);

    int updateByPrimaryKeySelective(AccountData record);

    int updateByPrimaryKey(AccountData record);
    
    int  updateFileState(String fileKey);
    
}