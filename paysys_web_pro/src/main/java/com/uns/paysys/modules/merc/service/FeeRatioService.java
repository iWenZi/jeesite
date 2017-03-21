package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.FeeRatioMapper;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 费率Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class FeeRatioService extends CrudService<FeeRatioMapper, FeeRatio> {
	
	@Autowired
	private FeeRatioMapper feeRatioMapper;

	
	@Transactional(readOnly = false)
	public void save(FeeRatio feeRatio) {
		User user = UserUtils.getUser();
		feeRatio.setCreateUser(user.getId().toString());
		feeRatio.setCreateTime(new Date());
		feeRatio.setUpdateUser(user.getId().toString());
		feeRatio.setUpdateTime(new Date());
		feeRatioMapper.insertSelective(feeRatio);
	}
	
	
	@Transactional(readOnly = false)
	public void update(FeeRatio feeRatio) {
		User user = UserUtils.getUser();
		feeRatio.setUpdateUser(user.getId().toString());
		feeRatio.setUpdateTime(new Date());
		feeRatioMapper.updateByPrimaryKeySelective(feeRatio);
	}

	/**
	 * 查询该推广商是否已设置相同交易类型费率
	 * @param feeRatio
	 * @return
	 */
	public boolean isSetSameFee(FeeRatio feeRatio) {
		
		List<FeeRatio> list = feeRatioMapper.isSetSameFee(feeRatio);
		if(list != null && list.size() > 0)
			return true;
		return false;
	}
	

	
	/**
	 * 根据主键id查询费率
	 * @param id
	 * @return
	 */
	public FeeRatio getFeeRatioById(Long id){
		
		BigDecimal bid = BigDecimal.valueOf(id);
		return feeRatioMapper.selectByPrimaryKey(bid);
	}
	
	/**
	 * 根据主键id删除费率
	 * @param id
	 * @return
	 */
	public void deleteSpreadMerchantFee(FeeRatio feeRatio){
		
		BigDecimal bid = BigDecimal.valueOf(feeRatio.getId());
		feeRatioMapper.deleteByPrimaryKey(bid);
	}
	

}
