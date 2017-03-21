package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.FeeRatioHisMapper;
import com.uns.paysys.modules.merc.dao.FeeRatioMapper;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.FeeRatioHis;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 费率Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class FeeRatioHisService extends CrudService<FeeRatioMapper, FeeRatio> {
	
	@Autowired
	private FeeRatioHisMapper feeRatioHisMapper;
	

	@Transactional(readOnly = false)
	public void saveFeeHis(FeeRatio feeRatio) {
		
		User user = UserUtils.getUser();
		feeRatio.setUpdateUser(user.getId().toString());
		feeRatio.setUpdateTime(new Date());
		feeRatioHisMapper.insertSelective(feeRatio);
	}

	
	/**
	 * 根据主键id查询费率
	 * @param id
	 * @return
	 */
	public FeeRatioHis getFeeRatioById(Long id){
		
		BigDecimal bid = BigDecimal.valueOf(id);
		return feeRatioHisMapper.selectByPrimaryKey(bid);
	}



}
