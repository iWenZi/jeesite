package com.uns.paysys.modules.merc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.modules.merc.dao.AuditOperationMapper;
import com.uns.paysys.modules.merc.dao.AuditStepMapper;
import com.uns.paysys.modules.merc.entity.AuditOperation;
import com.uns.paysys.modules.merc.entity.AuditStep;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.sys.utils.UserUtils;
@Service
public class AuditOperationService {

	@Autowired
	private AuditOperationMapper auditOperationMapper;
	@Autowired
	private AuditStepMapper auditStepMapper;
	@Autowired
	private AuditHistoryService auditHistoryService;
	@Autowired
	private FeeRatioService feeRatioService;
	@Autowired
	private SpreadMerchantInfoService spreadMerchantInfoService;


	/**创建审核操作记录
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public AuditOperation createAudit(Long auditOperandId, Long auditRuleId,  String reason) throws Exception{
		
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", auditOperandId);
		map.put("auditRuleId", auditRuleId);
		//费率可能是审核拒绝后再次发起申请
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation == null){//如果未发起过审核则插入记录
			//创建申请
			operation = new AuditOperation();
			operation.setCreateUser(UserUtils.getUser().getId().toString());//操作人ID
			operation.setCreateTime(new Date());
			// 新建时，审核前、后状态均为-1
			operation.setPreviousStatus(BigDecimal.valueOf(-1));		// 前状态
			operation.setPostStatus(BigDecimal.valueOf(-1));			// 后状态
			operation.setAuditOperandId(BigDecimal.valueOf(auditOperandId));		// 审核对象ID
			operation.setAuditRuleId(BigDecimal.valueOf(auditRuleId));		// 审核规则
			operation.setAuditGroupId(null);					// 清空审核组
			operation.setUpdateUser(UserUtils.getUser().getId().toString());		// 审核操作人IDs
			operation.setUpdateTime(new Date());
			operation.setAttitude("创建审核");
			auditOperationMapper.insertSelective(operation);
		}else{//否则修改记录
			operation.setCreateUser(UserUtils.getUser().getId().toString());//操作人ID
			operation.setCreateTime(new Date());
			// 新建时，审核前、后状态均为-1
			operation.setPreviousStatus(BigDecimal.valueOf(-1));		// 前状态
			operation.setPostStatus(BigDecimal.valueOf(-1));			// 后状态
			operation.setAuditOperandId(BigDecimal.valueOf(auditOperandId));		// 审核对象ID
			operation.setAuditRuleId(BigDecimal.valueOf(auditRuleId));		// 审核规则
			operation.setAuditGroupId(null);					// 清空审核组
			operation.setUpdateUser(UserUtils.getUser().getId().toString());		// 审核操作人IDs
			operation.setUpdateTime(new Date());
			operation.setAttitude("创建审核");
			auditOperationMapper.updateByPrimaryKeySelective(operation);
		}

		return operation;
	}
	
	/**
	 *初始化审核操作
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public AuditOperation initAudit(Long auditOperandId, Long auditRuleId, String reason) throws Exception{
		
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", auditOperandId);
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态
		BigDecimal postStatus = getPostStatus(stepList,operation.getPostStatus());
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		
		operation.setPreviousStatus(operation.getPostStatus());		// 前状态
		operation.setPostStatus(postStatus);			// 后状态
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setUpdateUser(UserUtils.getUser().getId().toString());		// 审核操作人ID
		operation.setUpdateTime(new Date());
		operation.setAttitude(reason);
		auditOperationMapper.updateByPrimaryKeySelective(operation);
		return operation;
	}
	

	/**
	 * 审核同意
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void agree(Long auditRuleId, FeeRatio fee, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", fee.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态
		BigDecimal postStatus = getPostStatus(stepList,operation.getPostStatus());
		//终审通过：更新费率表，审核操作表，审核历史表
		if(postStatus.compareTo(BigDecimal.valueOf(99))==0){
			fee.setAuditStatus(DictConstUtil.AUDIT_STATUS_2);
		}else{//非终审：更新费率表，审核操作表，审核历史表
			fee.setAuditStatus(DictConstUtil.AUDIT_STATUS_1);
		}
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新费率
		feeRatioService.update(fee);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	

	/**
	 * 审核拒绝
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void reject(Long auditRuleId, FeeRatio fee, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", fee.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态，拒绝后状态为0
		BigDecimal postStatus = new BigDecimal(0);
		
		//审核拒绝：更新费率表，审核操作表，审核历史表
		fee.setAuditStatus(DictConstUtil.AUDIT_STATUS_3);
		
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新费率
		feeRatioService.update(fee);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	
	
	/**
	 * 获取该状态的后续审核状态
	 * @param list
	 * @param previousStatus
	 * @return
	 * @throws Exception
	 */
	private BigDecimal getPostStatus(List<AuditStep> list, BigDecimal previousStatus) throws Exception{
		if(previousStatus.compareTo(BigDecimal.valueOf(-1))==0 && list.size()>0){
			//如果为初始状态，下一状态为一级审核
			return BigDecimal.valueOf(Long.valueOf(list.get(0).getAuditLevel()));
		}
		//循环遍历取出下一审核状态
		boolean flag = false;
		for(int i=0;i<list.size();i++){
			if(flag){
				return BigDecimal.valueOf(Long.valueOf(list.get(i).getAuditLevel()));
			}
			if(list.get(i).getAuditLevel().equals(previousStatus.toString())){
				flag = true;
			}
		}
		if(!flag){
			throw new Exception("未找到对应审核步骤");
		}
		return BigDecimal.valueOf(99);
	}
	
	private BigDecimal getAuditGroupId(List<AuditStep> stepList , BigDecimal intPreStatus){
		for(int i=0;i<stepList.size();i++){
			if(stepList.get(i).getAuditLevel().equals(intPreStatus.toString())){
				return stepList.get(i).getAuditGroupId();
			}
		}
		return null;
	}
	
	
	
	/**
	 * 推广商审核同意
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void agreeSpreadMerchant(Long auditRuleId, SpreadMerchant spread, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", spread.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态
		BigDecimal postStatus = getPostStatus(stepList,operation.getPostStatus());
		//终审通过：更新推广商表，审核操作表，审核历史表
		if(postStatus.compareTo(BigDecimal.valueOf(99))==0){
			spread.setAuditStatus(DictConstUtil.AUDIT_STATUS_2);
		}else{//非终审：更新推广商表，审核操作表，审核历史表
			spread.setAuditStatus(DictConstUtil.AUDIT_STATUS_1);
		}
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新推广商
		spreadMerchantInfoService.update(spread);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	/**
	 * 推广商审核拒绝
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void rejectSpreadMerchant(Long auditRuleId, SpreadMerchant spread, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", spread.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态，拒绝后状态为0
		BigDecimal postStatus = new BigDecimal(0);
		
		//审核拒绝：审核操作表，审核历史表
		spread.setAuditStatus(DictConstUtil.AUDIT_STATUS_3);
		
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新推广商
				spreadMerchantInfoService.update(spread);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	

	/**
	 * 推广商分配审核同意
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void assignAgree(Long auditRuleId, SpreadMerchant spread, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", spread.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态
		BigDecimal postStatus = getPostStatus(stepList,operation.getPostStatus());
		//终审通过：审核操作表，审核历史表
		if(postStatus.compareTo(BigDecimal.valueOf(99))==0){
			spread.setAssignStatus(DictConstUtil.AUDIT_STATUS_2);
		}else{//非终审：更新推广商分配，审核操作表，审核历史表
			spread.setAssignStatus(DictConstUtil.AUDIT_STATUS_1);
		}
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新推广商
		spreadMerchantInfoService.update(spread);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	/**
	 * 推广商分配审核拒绝
	 * @param auditOperandId
	 * @param auditRuleId
	 * @param reason
	 * @return
	 * @throws Exception
	 */
	public void assignReject(Long auditRuleId, SpreadMerchant spread, String reason) throws Exception{
		//查询审核操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("auditOperandId", spread.getId());
		map.put("auditRuleId", auditRuleId);
		AuditOperation operation = auditOperationMapper.getAuditOperation(map);
		if(operation != null && operation.getPostStatus().compareTo(BigDecimal.valueOf(99))==0){
			throw new Exception("终审已通过无需再审核");
		}
		List<AuditStep> stepList = auditStepMapper.getAuditStep(auditRuleId);
		//获取该次审核后状态，拒绝后状态为0
		BigDecimal postStatus = new BigDecimal(0);
		
		//审核拒绝：审核操作表，审核历史表
		spread.setAssignStatus(DictConstUtil.AUDIT_STATUS_3);
		
		// 取得当前操作的审核组id
		BigDecimal auditGroupId = getAuditGroupId(stepList,postStatus);
		operation.setAuditGroupId(auditGroupId);		// 当前操作审核组
		operation.setPreviousStatus(operation.getPostStatus());
		operation.setPostStatus(postStatus);
		operation.setAttitude(reason);
		operation.setUpdateUser(UserUtils.getUser().getId().toString());
		operation.setUpdateTime(new Date());
		//保存审核历史
		auditHistoryService.insertHisOperation(operation);
		//更新推广商
				spreadMerchantInfoService.update(spread);
		//更新审核操作
		auditOperationMapper.updateByPrimaryKeySelective(operation);
	}
	
	public boolean checkAuditPrivilege(Long auditOperandId, Long auditRuleId, Long userId){
		
		Map<String ,Object> param = new HashMap<String ,Object>();
		param.put("auditOperandId", auditOperandId);
		param.put("auditRuleId", auditRuleId);
		param.put("userId", userId);
		List<Long> list = auditOperationMapper.checkAuditPrivilege(param);
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}
	
}
