package com.uns.paysys.modules.merc.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.Collections3;
import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.merc.entity.Account;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
import com.uns.paysys.modules.merc.entity.AuditOperation;
import com.uns.paysys.modules.merc.entity.AuditRule;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadmercAcc;
import com.uns.paysys.modules.merc.form.AssignForm;
import com.uns.paysys.modules.merc.service.AccountService;
import com.uns.paysys.modules.merc.service.AssignService;
import com.uns.paysys.modules.merc.service.AuditHistoryService;
import com.uns.paysys.modules.merc.service.AuditOperandTypeService;
import com.uns.paysys.modules.merc.service.AuditOperationService;
import com.uns.paysys.modules.merc.service.AuditRuleService;
import com.uns.paysys.modules.merc.service.SpreadMerchantInfoService;
import com.uns.paysys.modules.merc.service.SpreadmercAccService;
import com.uns.paysys.modules.sys.utils.StringUtil;
import com.uns.paysys.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

/**
 * 推广商分配类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/merc/assign")
public class AssignController extends BaseController {

	@Autowired
	private AssignService assignService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SpreadmercAccService spreadmercAccService;
	@Autowired
	private AuditOperandTypeService auditOperandTypeService;
	@Autowired
	private AuditRuleService auditRuleService;
	@Autowired
	private AuditOperationService auditOperationService;
	@Autowired
	private AuditHistoryService auditHistoryService;
	@Autowired
	private SpreadMerchantInfoService spreadMerchantInfoService;

	/**
	 * 推广商分配列表
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping({ "assignList", "" })
	public String assignList(AssignForm assignForm, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<Map<String, Object>> mapPage = assignService
				.findAssignList(new Page<Map<String, Object>>(request, response), assignForm);
		 List list= mapPage.getList();
		for(HashMap<String, Object> a:(List<HashMap<String, Object>>)list){
			a.put("ENCRYPTEMAIL", StringUtil.subEmail((String)a.get("EMAIL")));
		}
		model.addAttribute("mapPage", mapPage);
		return "modules/assign/assignList2";
	}

	/**
	 * 推广商分配 -- 打开推广商分配对话框
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "accounttoassign")
	public String AccountToAssign(String id, Model model,Account account) {

		model.addAttribute("userList", accountService.allAccountList(Long.valueOf(id)));
		model.addAttribute("allUserList", accountService.allAccountList(account));

		model.addAttribute("selectIds",
				Collections3.extractToString(accountService.allAccountList(Long.valueOf(id)), "ID", ","));
		return "modules/assign/selectAccountToAssign";
	}
	
	
	@RequestMapping(value = "accounttoassignAjax")
	@ResponseBody
	public void accounttoassignAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("merchantName");
		Account account=new Account();
		account.setName(name);
		List<Account> aa=accountService.allAccountList(account);
		response.getWriter().print(com.alibaba.fastjson.JSONObject.toJSON(aa));
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	

	/**
	 * 推广商分配
	 * 
	 * @param 
	 * @param idsArr
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequiresPermissions("merc:spreadMerchant:assign")
	@RequestMapping(value = "assignAccount")
	public String assignAccount(SpreadMerchant spreadMerchant, String[] idsArr, RedirectAttributes redirectAttributes)
			throws NumberFormatException, Exception {

		spreadmercAccService.deleteAccount(Long.valueOf(String.valueOf(spreadMerchant.getId())));
		spreadMerchant.setAssignStatus(DictConstUtil.AUDIT_STATUS_0);
		spreadMerchantInfoService.updateSpreadMerchant(spreadMerchant);
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			SpreadmercAcc acc = new SpreadmercAcc();
			acc.setAccountId(idsArr[i]);// 商户ID
			acc.setSpreadmerchantId(String.valueOf(spreadMerchant.getId()));// 推广商ID
			acc.setAuditStatus(DictConstUtil.AUDIT_STATUS_0);// 状态
			acc.setAssignTime(new Date());
			Account account = accountService.findDefaultSubSeq(Long.valueOf(idsArr[i]));// 查询商户DefaultSubSeq
			acc.setDefaultSubSeq(account.getDefaultSubSeq());
			int count = spreadmercAccService.saveAcc(acc);
			if (count > 0) {
				newNum++;
			}
		}

		// 获取审核操作对象
		AuditOperandType type = auditOperandTypeService.getAuditOperandType(
				DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO_ASSIGN, DictConstUtil.ACTION_FLAG_1);
		// 获取该对象的审核规则
		AuditRule rule = auditRuleService.getAuditRule(type.getId());

		// 创建审核
		AuditOperation operationCreate = auditOperationService.createAudit(spreadMerchant.getId(),
				Long.valueOf(rule.getId().toString()), "创建记录");
		auditHistoryService.insertHisOperation(operationCreate);// 保存历史

		// 提交审核
		AuditOperation operationInit = auditOperationService.initAudit(spreadMerchant.getId(),
				Long.valueOf(rule.getId().toString()), "提交审核");
		auditHistoryService.insertHisOperation(operationInit);// 保存历史
		addMessage(redirectAttributes, "已成功分配 " + newNum + " 个商户");
		return "redirect:" + adminPath + "/merc/assign/assignList?repage";
	}

	/**
	 * 推广商分配审核
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("merc:spreadMerchant:assignAudit")
	@RequestMapping(value = "assignAudit")
	public String assignAudit(SpreadMerchant spreadMerchant, HttpServletRequest request, HttpServletResponse response,
			Model model, @RequestParam(required = false) String id, RedirectAttributes redirectAttributes,
			@RequestParam(required = true) String remarks) throws Exception {
		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));

		if (DictConstUtil.AUDIT_STATUS_2.equals(spreadMerchant.getAssignStatus())
				|| DictConstUtil.AUDIT_STATUS_3.equals(spreadMerchant.getAssignStatus())) {
			addMessage(redirectAttributes, "审核失败,推广商分配已审核！");
		} else {
			// 获取审核操作对象
			AuditOperandType type = auditOperandTypeService.getAuditOperandType(
					DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO_ASSIGN, DictConstUtil.ACTION_FLAG_1);
			// 获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if (auditOperationService.checkAuditPrivilege(spreadMerchant.getId(), rule.getId(),
					UserUtils.getUser().getId())) {// 判断当前用户是否有下一审核步骤的权限

				auditOperationService.assignAgree(rule.getId(), spreadMerchant, remarks);
				if(DictConstUtil.AUDIT_STATUS_2.equals(spreadMerchant.getAssignStatus()))
						{
					SpreadmercAcc acc=new SpreadmercAcc();
					acc.setSpreadmerchantId(String.valueOf(spreadMerchant.getId()));
					acc.setAuditStatus(DictConstUtil.AUDIT_STATUS_2);
					spreadmercAccService.updateAuditStatus(acc);
						}
				addMessage(redirectAttributes, "审核同意成功");
			} else {
				addMessage(redirectAttributes, "审核失败,你没有下一审核步骤的权限");
			}
		}

		return "redirect:" + adminPath + "/merc/assign/assignList?repage";
	}

	/**
	 * 推广商分配审核拒绝
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("merc:spreadMerchant:assignAudit")
	@RequestMapping(value = "assignReject")
	public String assignReject(SpreadMerchant spreadMerchant, @RequestParam(required = true) String id,
			@RequestParam(required = true) String remarks, RedirectAttributes redirectAttributes) throws Exception {

		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));

		if (DictConstUtil.AUDIT_STATUS_2.equals(spreadMerchant.getAssignStatus())
				|| DictConstUtil.AUDIT_STATUS_3.equals(spreadMerchant.getAssignStatus())) {
			addMessage(redirectAttributes, "审核失败,推广商分配已审核");
		} else {
			// 获取审核操作对象
			AuditOperandType type = auditOperandTypeService.getAuditOperandType(
					DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO_ASSIGN, DictConstUtil.ACTION_FLAG_1);
			// 获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if (auditOperationService.checkAuditPrivilege(spreadMerchant.getId(), rule.getId(),
					UserUtils.getUser().getId())) {// 判断当前用户是否有下一审核步骤的权限
				auditOperationService.assignReject(rule.getId(), spreadMerchant, remarks);
				addMessage(redirectAttributes, "审核拒绝成功");
			} else {
				addMessage(redirectAttributes, "审核失败,你没有下一审核步骤的权限");
			}
		}
		return "redirect:" + adminPath + "/merc/assign/assignList?repage";

	}

}