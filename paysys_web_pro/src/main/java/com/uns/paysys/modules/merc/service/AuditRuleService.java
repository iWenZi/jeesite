package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.AuditRuleMapper;
import com.uns.paysys.modules.merc.dao.AuditStepMapper;
import com.uns.paysys.modules.merc.entity.AuditRule;
import com.uns.paysys.modules.merc.entity.AuditStep;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 审核规则Service
 * @author Administrator
 *
 */
@Service
public class AuditRuleService  extends CrudService<AuditRuleMapper, AuditRule> {
	
	@Autowired
	private AuditRuleMapper auditRuleMapper;
	@Autowired
	private AuditStepMapper auditStepMapper;
	
	
	/**
	 * 根据审核对象Id 获取审核规则
	 * @param auditOperandTypeId
	 * @return
	 */
	public AuditRule getAuditRule(Long auditOperandTypeId){
		
		return auditRuleMapper.getAuditRule(auditOperandTypeId);
	}
	
	/**
	 * 保存审核组
	 * @param auditRule
	 */
	public void saveAuditRule(AuditRule auditRule){
		String userId = UserUtils.getUser().getId().toString();
		
		if(auditRule.getId() != null){//修改
			//更新审核规则
			auditRule.setUpdateUser(userId);
			auditRule.setUpdateTime(new Date());
			auditRuleMapper.updateByPrimaryKeySelective(auditRule);
			//删除审核步骤
			auditStepMapper.deleteByAuditRuleId(auditRule.getId());
		}else{//新增
			//保存审核规则
			auditRule.setUsed(new BigDecimal(0));//未使用
			auditRule.setCreateUser(userId);
			auditRule.setCreateTime(new Date());
			auditRule.setUpdateUser(userId);
			auditRule.setUpdateTime(new Date());
			auditRuleMapper.insertSelective(auditRule);
			
		}
		//保存审核步骤
		for(int i=0; i<auditRule.getAuditGroupId().length; i++){
			AuditStep step = new AuditStep();
			step.setAuditRuleId(new BigDecimal(auditRule.getId()));
			step.setAuditGroupId(new BigDecimal(auditRule.getAuditGroupId()[i]));
			step.setName(auditRule.getAuditStepName()[i]);
			step.setAuditLevel((i+1)+"");
			step.setCreateUser(userId);
			step.setCreateTime(new Date());
			step.setUpdateUser(userId);
			step.setUpdateTime(new Date());
			auditStepMapper.insertSelective(step);
		}
		
	}
	
	
	/**校验该审核对象是否存在有效的审核规则
	 * @param auditOperandTypeId
	 * @return
	 */
	public boolean checkExist(BigDecimal auditOperandTypeId){
		
		List<Long> list = auditRuleMapper.checkExist(auditOperandTypeId);
		if(list != null && list.size() > 0)
			return true;
		return false;
	}
	
	/**
	 * 根据审核规则id 删除审核规则
	 * @param auditRuleId
	 */
	public void delete(Long auditRuleId){
		
		//删除审核步骤
		auditStepMapper.deleteByAuditRuleId(auditRuleId);
		//删除审核规则
		auditRuleMapper.deleteByPrimaryKey(new BigDecimal(auditRuleId));
	}

}
