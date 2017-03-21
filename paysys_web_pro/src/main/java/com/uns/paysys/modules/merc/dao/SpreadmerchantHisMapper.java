package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.SpreadmerchantHis;
@MyBatisDao
public interface SpreadmerchantHisMapper {


    int insert(SpreadmerchantHis record);

    int insertSelective(SpreadmerchantHis record);

    List<SpreadmerchantHis> selectByPrimaryKey(BigDecimal id);

}