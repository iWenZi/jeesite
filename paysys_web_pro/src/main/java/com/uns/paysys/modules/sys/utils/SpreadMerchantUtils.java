package com.uns.paysys.modules.sys.utils;

import java.util.List;

import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.common.utils.SpringContextHolder;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.modules.merc.dao.SpreadMerchantDao;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;

/**
 * 推广商工具类
 * @author Administrator
 *
 */
public class SpreadMerchantUtils {
	
	private static SpreadMerchantDao spreadMerchantDao = SpringContextHolder.getBean(SpreadMerchantDao.class);

	public static final String CACHE_SPREAD_MERC_LIST = "spreadMercList";
	
	/**
	 * 根据条件获取推广商列表
	 * @return
	 */
	public static List<SpreadMerchant> getSpreadMercList(String flag){
		
		List<SpreadMerchant> spreadMercList = spreadMerchantDao.getSpreadMerchant(flag);
		if(!StringUtils.isNotBlank(flag)){//获取所有推广商列表放入缓存
			CacheUtils.put(CACHE_SPREAD_MERC_LIST, spreadMercList);
		}
		return spreadMercList;
	}
	
	@SuppressWarnings("unchecked")
	public static String getSpreadMercNameById(Long id, String defaultValue){
		if (id != null){
			for (SpreadMerchant spreadMerchant : (List<SpreadMerchant>)CacheUtils.get(CACHE_SPREAD_MERC_LIST)){
				if (spreadMerchant.getId().longValue() == Long.valueOf(id.toString()).longValue()){
					return spreadMerchant.getMerchantName();
				}
			}
		}
		return defaultValue;
	}
	

}
