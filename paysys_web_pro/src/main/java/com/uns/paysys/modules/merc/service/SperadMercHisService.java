package com.uns.paysys.modules.merc.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.modules.merc.dao.AuditHistoryMapper;
import com.uns.paysys.modules.merc.dao.SpreadmerchantHisMapper;
import com.uns.paysys.modules.merc.entity.AuditHistory;
import com.uns.paysys.modules.merc.entity.AuditOperation;
import com.uns.paysys.modules.merc.entity.SpreadmerchantHis;

@Service
public class SperadMercHisService {
	@Autowired
	private SpreadmerchantHisMapper spreadmerchantHisMapper;
	
	public void saveSpreadMerchantHis(SpreadmerchantHis spreadmerchantHis) {
		spreadmerchantHisMapper.insertSelective(spreadmerchantHis);
	}

	public List<SpreadmerchantHis> selectByPrimaryKey(Long id) {
		BigDecimal bid = BigDecimal.valueOf(id);
		return spreadmerchantHisMapper.selectByPrimaryKey(bid);
	}
}
