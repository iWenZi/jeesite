package com.uns.paysys.modules.merc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.FeeRatio;

@MyBatisDao
public interface FeeRatioMapper extends CrudDao<FeeRatio> {

    int deleteByPrimaryKey(BigDecimal id);

    int insert(FeeRatio record);

    int insertSelective(FeeRatio record);

    FeeRatio selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(FeeRatio record);

    int updateByPrimaryKey(FeeRatio record);
    
    List<FeeRatio> isSetSameFee(FeeRatio record);
}