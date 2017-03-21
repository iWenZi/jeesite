package com.uns.paysys.modules.merc.dao;

import java.util.List;

import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.CityInfo;

@MyBatisDao
public interface CityInfoDao extends CrudDao<CityInfo> {

	public List<CityInfo> findAllProvince();

	public List<CityInfo> provinceAllCity(Long level);
}
