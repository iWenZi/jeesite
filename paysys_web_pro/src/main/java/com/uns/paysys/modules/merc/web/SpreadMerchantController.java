package com.uns.paysys.modules.merc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
import com.uns.paysys.modules.merc.entity.AuditOperation;
import com.uns.paysys.modules.merc.entity.AuditRule;
import com.uns.paysys.modules.merc.entity.FeeRatio;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.SpreadMerchantForm;
import com.uns.paysys.modules.merc.service.AuditHistoryService;
import com.uns.paysys.modules.merc.service.AuditOperandTypeService;
import com.uns.paysys.modules.merc.service.AuditOperationService;
import com.uns.paysys.modules.merc.service.AuditRuleService;
import com.uns.paysys.modules.merc.service.FeeRatioHisService;
import com.uns.paysys.modules.merc.service.FeeRatioService;
import com.uns.paysys.modules.merc.service.SpreadMerchantService;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 推广商费率维护类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/merc/spreadMerchant")
public class SpreadMerchantController extends BaseController {

	@Autowired
	private SpreadMerchantService spreadMerchantService;
	@Autowired
	private FeeRatioService feeRatioService;
	@Autowired
	private FeeRatioHisService feeRatioHisService;
	@Autowired
	private AuditHistoryService auditHistoryService;
	@Autowired
	private AuditOperandTypeService auditOperandTypeService;
	@Autowired
	private AuditRuleService auditRuleService;
	@Autowired
	private AuditOperationService auditOperationService;
	
	
	/**
	 * 所有方法的前置方法，如果id不为空 根据id查询推广商
	 * @param id
	 * @return
	 */
//	@ModelAttribute
//	public SpreadMerchantForm get(@RequestParam(required=false) String id) {
//			return new SpreadMerchantForm();
//	}
	
	
	
	
	/**
	 * 推广商费率列表
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:feeView")
	@RequestMapping(value = "spreadMerchantFeeList")
	public String spreadMerchantFeeList(SpreadMerchantForm form, HttpServletRequest request, HttpServletResponse response, Model model) {
		
			Page<Map<String , Object>> mapPage = spreadMerchantService.findSpreadMerchantFee(new Page<Map<String , Object>>(request, response), form);
			
	        model.addAttribute("mapPage", mapPage);
	        
			return "modules/merc/spreadMerchantFeeList";
		}
	
	/**
	 * 推广商信息列表
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:view")
	@RequestMapping(value = "list")
	public String spreadMerchantList(SpreadMerchant spreadMerchant, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<SpreadMerchant> page = spreadMerchantService.findSpreadMerchant(new Page<SpreadMerchant>(request, response), spreadMerchant);
	        model.addAttribute("page", page);
			return "modules/merc/spreadMerchantList";
		}
	
	/**
	 * 费率新增、查看、修改 页面
	 * @param user
	 * @param model
	 * @return
	 */
//	@RequiresPermissions("merc:spreadMerchant:feeEdit")
	@RequiresPermissions("merc:spreadMerchant:feeView")
	@RequestMapping(value = "form")
	public String form(FeeRatio feeRatio,@RequestParam(required=false) String id, Model model) {
		
		if (StringUtils.isNotBlank(id)){
			feeRatio =  feeRatioService.getFeeRatioById(Long.valueOf(id));
		}
		model.addAttribute("feeRatio", feeRatio);
		return "modules/merc/spreadMerchantFeeForm";
	}
	

	/**
	 * 新增保存、修改保存
	 * @param user
	 * @param request
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequiresPermissions("merc:spreadMerchant:feeEdit")
	@RequestMapping(value = "saveSpreadMerchantFee")
	public String saveSpreadMerchantFee(FeeRatio feeRatio, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws NumberFormatException, Exception {
		
		if(feeRatio.getAuditStatus().equals(DictConstUtil.AUDIT_STATUS_0) || feeRatio.getAuditStatus().equals(DictConstUtil.AUDIT_STATUS_1)){
			addMessage(model, "修改失败，费率审核中无法修改");
			return form(feeRatio, null, model);
		}
		
		//相关类型为4：推广商；审核状态默认为0：待审核；收付款方默认为N：无
		feeRatio.setRelatingType(DictConstUtil.RELATIN_TYPE_4);
		feeRatio.setAuditStatus(DictConstUtil.AUDIT_STATUS_0);
		feeRatio.setFlag(DictConstUtil.FLAG_N);
		
		//校验该推广商是否已设置同交易类型费率
		if (feeRatio.getId() == null && feeRatioService.isSetSameFee(feeRatio)){
			addMessage(model, "保存费率失败，该推广商已设置同种交易类型费率!");
			return form(feeRatio, null, model);
		}
		
		//数据校验
		if (!beanValidator(model, feeRatio)){
			return form(feeRatio, null, model);
		}
		
		if(feeRatio.getId() != null){//修改费率时保存费率历史，修改费率（先删除再新增）
			feeRatioHisService.saveFeeHis(feeRatioService.getFeeRatioById(feeRatio.getId()));
			feeRatioService.deleteSpreadMerchantFee(feeRatio);
			feeRatioService.save(feeRatio);
		}else{//保存费率
			feeRatioService.save(feeRatio);
		}

		//获取审核操作对象
		AuditOperandType type = auditOperandTypeService.getAuditOperandType(DictConstUtil.OPREAND_CLASS_FEE_RATIO, DictConstUtil.ACTION_FLAG_1);
		//获取该对象的审核规则
		AuditRule rule = auditRuleService.getAuditRule(type.getId());
		
		//创建审核
		AuditOperation operationCreate= auditOperationService.createAudit(feeRatio.getId(), Long.valueOf(rule.getId().toString()), "创建记录");
		auditHistoryService.insertHisOperation(operationCreate);//保存历史
		
		//提交审核
		AuditOperation operationInit= auditOperationService.initAudit(feeRatio.getId(), Long.valueOf(rule.getId().toString()), "提交审核");
		auditHistoryService.insertHisOperation(operationInit);//保存历史
		
		addMessage(redirectAttributes, "保存费率成功!");
		return "redirect:" + adminPath + "/merc/spreadMerchant/spreadMerchantFeeList?repage";
	}
	
	
	/**
	 * 费率删除
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:feeEdit")
	@RequestMapping(value = "deleteSpreadMerchantFee")
	public String deleteSpreadMerchantFee(@RequestParam(required=true) String id, RedirectAttributes redirectAttributes) {
		
		FeeRatio feeRatio = feeRatioService.getFeeRatioById(Long.valueOf(id));
			
		if (feeRatio == null){
			addMessage(redirectAttributes, "删除失败，费率不存在 无法删除！");
		}else if (DictConstUtil.AUDIT_STATUS_0.equals(feeRatio.getAuditStatus()) || DictConstUtil.AUDIT_STATUS_1.equals(feeRatio.getAuditStatus())){
			addMessage(redirectAttributes, "删除失败，费率审核中 无法删除！");
		}else{
			feeRatioService.deleteSpreadMerchantFee(feeRatio);
			//保存历史费率
			feeRatioHisService.saveFeeHis(feeRatio);
			addMessage(redirectAttributes, "费率删除成功！");
		}
		return "redirect:" + adminPath + "/merc/spreadMerchant/spreadMerchantFeeList?repage";
	}
	

	/**
	 * 推广商历史费率列表
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:feeView")
	@RequestMapping(value = "spreadMerchantHisFeeList")
	public String spreadMerchantHisFeeList(SpreadMerchantForm form, HttpServletRequest request, HttpServletResponse response, Model model) {
		
			Page<Map<String , Object>> mapPage = spreadMerchantService.findSpreadMerchantHisFee(new Page<Map<String , Object>>(request, response), form);
			
	        model.addAttribute("mapPage", mapPage);
	        
			return "modules/merc/spreadMerchantHisFeeList";
		}

	
	/**
	 * 推广商费率审核详情
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:feeAduit")
	@RequestMapping(value = "toAudit")
	public String toAudit(@RequestParam(required=false) String id, Model model) {
		FeeRatio feeRatio = null;
		if (StringUtils.isNotBlank(id)){
			feeRatio =  feeRatioService.getFeeRatioById(Long.valueOf(id));
		}
		
		//审核历史
		List<Map<String, Object>> auditHisList = auditHistoryService.getAuditHis(Long.valueOf(id), DictConstUtil.FEE_RATIO , DictConstUtil.ACTION_FLAG_1);
		model.addAttribute("auditHisList", auditHisList);
		
		model.addAttribute("feeRatio", feeRatio);
		return "modules/merc/spreadMerchantFeeAudit";
	}


	
	/**
	 * 推广商费率审核同意
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("merc:spreadMerchant:feeAduit")
	@RequestMapping(value = "agree")
	public String agree(@RequestParam(required=true) String id, @RequestParam(required=true) String remarks, RedirectAttributes redirectAttributes) throws Exception {
		
		//获取审核费率对象
		FeeRatio feeRatio = feeRatioService.getFeeRatioById(Long.valueOf(id));
		if (feeRatio == null){
			addMessage(redirectAttributes, "审核失败，费率不存在 无法审核！");
		}else if (DictConstUtil.AUDIT_STATUS_2.equals(feeRatio.getAuditStatus()) || DictConstUtil.AUDIT_STATUS_3.equals(feeRatio.getAuditStatus())){
			addMessage(redirectAttributes, "审核失败，费率已审核！");
		}else{
			//获取审核操作对象
			AuditOperandType type = auditOperandTypeService.getAuditOperandType(DictConstUtil.OPREAND_CLASS_FEE_RATIO, DictConstUtil.ACTION_FLAG_1);
			//获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if(auditOperationService.checkAuditPrivilege(feeRatio.getId(), rule.getId(), UserUtils.getUser().getId())){//判断当前用户是否有下一审核步骤的权限
				auditOperationService.agree(rule.getId(), feeRatio, remarks);
				addMessage(redirectAttributes, "审核同意成功！");
			}else{
				addMessage(redirectAttributes, "审核失败，你没有下一审核步骤的权限！");
			}
			
			
		}
		return "redirect:" + adminPath + "/merc/spreadMerchant/spreadMerchantFeeList?repage";
	}
	
	/**
	 * 推广商费率审核拒绝
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */
	@RequiresPermissions("merc:spreadMerchant:feeAduit")
	@RequestMapping(value = "reject")
	public String reject(@RequestParam(required=true) String id, @RequestParam(required=true) String remarks, RedirectAttributes redirectAttributes) throws Exception {
		
		//获取审核费率对象
		FeeRatio feeRatio = feeRatioService.getFeeRatioById(Long.valueOf(id));
		if (feeRatio == null){
			addMessage(redirectAttributes, "审核失败，费率不存在 无法审核！");
		}else if (DictConstUtil.AUDIT_STATUS_2.equals(feeRatio.getAuditStatus()) || DictConstUtil.AUDIT_STATUS_3.equals(feeRatio.getAuditStatus())){
			addMessage(redirectAttributes, "审核失败，费率已审核！");
		}else{
			//获取审核操作对象
			AuditOperandType type = auditOperandTypeService.getAuditOperandType(DictConstUtil.OPREAND_CLASS_FEE_RATIO, DictConstUtil.ACTION_FLAG_1);
			//获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if(auditOperationService.checkAuditPrivilege(feeRatio.getId(), rule.getId(), UserUtils.getUser().getId())){//判断当前用户是否有下一审核步骤的权限
				auditOperationService.reject(rule.getId(), feeRatio, remarks);
				addMessage(redirectAttributes, "审核拒绝成功！");
			}else{
				addMessage(redirectAttributes, "审核失败，你没有下一审核步骤的权限！");
			}
		}
		return "redirect:" + adminPath + "/merc/spreadMerchant/spreadMerchantFeeList?repage";
	}
	


}
