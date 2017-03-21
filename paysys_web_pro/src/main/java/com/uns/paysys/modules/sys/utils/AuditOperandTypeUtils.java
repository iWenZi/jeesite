package com.uns.paysys.modules.sys.utils;

import java.math.BigDecimal;
import java.util.List;

import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.common.utils.SpringContextHolder;
import com.uns.paysys.modules.merc.dao.AuditOperandTypeMapper;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
import com.uns.paysys.modules.sys.dao.AuditGroupMapper;
import com.uns.paysys.modules.sys.entity.AuditGroup;

public class AuditOperandTypeUtils {

	private static AuditOperandTypeMapper auditOperandTypeMapper = SpringContextHolder.getBean(AuditOperandTypeMapper.class);
	private static AuditGroupMapper auditGroupMapper = SpringContextHolder.getBean(AuditGroupMapper.class);

	public static final String CACHE_AUDIT_OPERAND_TYPE = "cacheAuditOperandType";
	public static final String CACHE_AUDIT_GROUP_LIST = "cacheAuditGroupList";
	
	
	/**
	 * 获取所有审核对象List，set到缓存
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<AuditOperandType> getAuditOperandTypeList(){
		List<AuditOperandType> auditOperandTypeList = (List<AuditOperandType>) CacheUtils.get(CACHE_AUDIT_OPERAND_TYPE);
		if (auditOperandTypeList==null){
			auditOperandTypeList = (List<AuditOperandType>) auditOperandTypeMapper.findAllList(new AuditOperandType());
			CacheUtils.put(CACHE_AUDIT_OPERAND_TYPE, auditOperandTypeList);
		}

		return auditOperandTypeList;
	}
	
	/**根据审核对象id 获取审核对象名称
	 * @param id
	 * @param defaultValue
	 * @return
	 */
	public static String getAuditOperandTypeName(Object id, String defaultValue){
		if (id != null){
			for (AuditOperandType auditOperandType : getAuditOperandTypeList()){
				if (auditOperandType.getId().longValue() == Long.valueOf(id.toString()).longValue()){
					return auditOperandType.getName();
				}
			}
		}
		return defaultValue;
	}

	
	/**获取所有审核组列表
	 * @param id
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  List<AuditGroup> getAuditGroupList(){
		List<AuditGroup> auditGroupList = (List<AuditGroup>) CacheUtils.get(CACHE_AUDIT_GROUP_LIST);
		if (auditGroupList==null){
			auditGroupList = (List<AuditGroup>) auditGroupMapper.findList(new AuditGroup());
			CacheUtils.put(CACHE_AUDIT_GROUP_LIST, auditGroupList);
		}
		return auditGroupList;
	}
	
	
	/**
	 * 根据审核组id 获取审核组名称
	 * @param id
	 * @param defaultValue
	 * @return
	 */
	public static String getAuditGroupName(BigDecimal id, String defaultValue){
		if (id != null){
			return auditGroupMapper.selectByPrimaryKey(id).getName();
		}
		return defaultValue;
	}


}
