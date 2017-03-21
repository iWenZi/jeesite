package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;

import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.FeeRatioHis;
@MyBatisDao
public interface FeeRatioHisMapper {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(FeeRatioHis record);

    int insertSelective(FeeRatio record);

    FeeRatioHis selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(FeeRatioHis record);

    int updateByPrimaryKey(FeeRatioHis record);
}