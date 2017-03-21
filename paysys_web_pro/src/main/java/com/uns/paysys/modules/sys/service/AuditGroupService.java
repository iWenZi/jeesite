package com.uns.paysys.modules.sys.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.common.utils.CacheUtils;
import com.uns.paysys.modules.sys.dao.AuditGroupMapper;
import com.uns.paysys.modules.sys.entity.AuditGroup;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 审核组Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class AuditGroupService extends CrudService<AuditGroupMapper, AuditGroup> {
	
	@Autowired
	private AuditGroupMapper auditGroupMapper;
	
	
	/**
	 * 审核组删除
	 * @param id
	 */
	public void delete(Long id){
		//删除组和人员映射关系
		auditGroupMapper.deleteGroupUser(id);
		//删除审核组
		auditGroupMapper.deleteByPrimaryKey(BigDecimal.valueOf(id));
		//清空缓存
		CacheUtils.remove("cacheAuditGroupList");
	}
	
	
	
	/**
	 * 保存审核组
	 * @param auditGroup
	 */
	public void saveAuditGroup(AuditGroup auditGroup){
		String userId = UserUtils.getUser().getId().toString();
		if(auditGroup.getId() != null){//如果是修改
			AuditGroup orgAuditGroup = auditGroupMapper.selectByPrimaryKey(new BigDecimal(auditGroup.getId()));
			orgAuditGroup.setName(auditGroup.getName());
			orgAuditGroup.setRemark(auditGroup.getRemark());
			orgAuditGroup.setUpdateUser(userId);
			orgAuditGroup.setUpdateTime(new Date());
			//更新审核组
			auditGroupMapper.updateByPrimaryKeySelective(orgAuditGroup);
			//删除组和人员映射关系
			auditGroupMapper.deleteGroupUser(auditGroup.getId());
		}else {//新增
			auditGroup.setCreateUser(userId);
			auditGroup.setUsed(new BigDecimal(0));
			auditGroup.setCreateTime(new Date());
			auditGroup.setSystemGroup(new BigDecimal(1));		// 后台审核组
			auditGroup.setUpdatable(new BigDecimal(1));			// 是否可编辑
			auditGroup.setUpdateUser(userId);
			auditGroup.setUpdateTime(new Date());
			//保存审核组
			auditGroupMapper.insertSelective(auditGroup);
		}
		//保存审核组  审核人员 映射关系
		auditGroupMapper.saveGroupUser(auditGroup);
		//清空缓存
		CacheUtils.remove("cacheAuditGroupList");
	}
	
	
	
}
