package com.uns.paysys.modules.sys.utils;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.common.utils.SpringContextHolder;
import com.uns.paysys.modules.sys.dao.ActionTypeDao;
import com.uns.paysys.modules.sys.entity.ActionType;
import com.uns.paysys.modules.sys.entity.Dict;

/**
 * 交易类型工具类
 * @author Administrator
 *
 */
public class ActionTypeUtils {
	
	private static ActionTypeDao actionTypeDao = SpringContextHolder.getBean(ActionTypeDao.class);
	
	public static final String CACHE_ACTION_TYPE_LIST = "actionTypeList";
	public static final String CACHE_ACTION_TYPE_LIST_TIME = "actionTypeListTime";
	
	
	/**
	 * 获取所有交易类型List，set到缓存
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ActionType> getActionTypeList(){
		List<ActionType> actionTypeList = (List<ActionType>) CacheUtils.get(CACHE_ACTION_TYPE_LIST);
		if (actionTypeList==null){
			actionTypeList = (List<ActionType>) actionTypeDao.findAllList(new ActionType());
			CacheUtils.put(CACHE_ACTION_TYPE_LIST, actionTypeList);
			CacheUtils.put(CACHE_ACTION_TYPE_LIST_TIME, new Date().getTime());//保存第一次刷新时间
		}else{
			Long lastTime = CacheUtils.get(CACHE_ACTION_TYPE_LIST_TIME) == null ? null : (Long) CacheUtils.get(CACHE_ACTION_TYPE_LIST_TIME) ;
			if(lastTime != null && (new Date().getTime() - lastTime) > 24*60*60*1000){//actionTypeList 隔天刷新一次
				actionTypeList = (List<ActionType>) actionTypeDao.findAllList(new ActionType());
				CacheUtils.put(CACHE_ACTION_TYPE_LIST, actionTypeList);
				CacheUtils.put(CACHE_ACTION_TYPE_LIST_TIME, new Date().getTime());
			}
		}
		return actionTypeList;
	}
	
	@SuppressWarnings("unchecked")
	public static String getActionName(Object id, String defaultValue){
		if (id != null){
			for (ActionType actionType : (List<ActionType>)CacheUtils.get(CACHE_ACTION_TYPE_LIST)){
				if (actionType.getId().longValue() == Long.valueOf(id.toString()).longValue()){
					return actionType.getActionName();
				}
			}
		}
		return defaultValue;
	}
	

}
