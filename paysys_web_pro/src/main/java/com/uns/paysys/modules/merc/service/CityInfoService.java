package com.uns.paysys.modules.merc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.CityInfoDao;
import com.uns.paysys.modules.merc.entity.CityInfo;
/**
 * 省市Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class CityInfoService extends CrudService<CityInfoDao, CityInfo> {

	@Autowired
	private CityInfoDao cityInfoDao;


	/**
	 * 查询所有省份
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<CityInfo> findAllProvince() {
		return cityInfoDao.findAllProvince();

	}

	/**
	 * 查询省的下属市
	 * 
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<CityInfo> provinceAllCity(Long level) {
		return cityInfoDao.provinceAllCity(level);
	}

}
