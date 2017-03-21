package com.uns.paysys.modules.sys.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.merc.entity.AuditRule;
import com.uns.paysys.modules.merc.service.AuditRuleService;
import com.uns.paysys.modules.sys.entity.AuditGroup;
/**
 * 审核规则控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/sys/auditRule")
public class AuditRuleController extends BaseController {
	
	@Autowired
	private AuditRuleService auditRuleService;
	
	@ModelAttribute
	public AuditRule get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return auditRuleService.get(Long.valueOf(id));
		}else{
			return new AuditRule();
		}
		
	}
	
	
	/**
	 * 查看审核规则列表
	 * @param auditGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditRule:view")
	@RequestMapping(value = "auditRuleList")
	public String auditRuleList(AuditRule auditRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		
        Page<AuditRule> page = auditRuleService.findPage(new Page<AuditRule>(request, response), auditRule); 
        model.addAttribute("page", page);
		return "modules/sys/auditRuleList";
		
	}



	/**
	 * 新增、查看 方法
	 * @param auditGroup
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditRule:view")
	@RequestMapping(value = "form")
	public String form(AuditRule auditRule, Model model) {
		
		model.addAttribute("auditRule", auditRule);

		return "modules/sys/auditRuleForm";
	}
	



	/**
	 * 转到修改页面
	 * @param auditGroup
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditRule:edit")
	@RequestMapping(value = "modifyForm")
	public String modifyForm(AuditRule auditRule, Model model) {
		
		model.addAttribute("auditRule", auditRule);

		return "modules/sys/auditRuleModifyForm";
	}
	
	

	
	/**
	 * 审核组新增保存、修改保存方法
	 * @param auditGroup
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:auditRule:edit")
	@RequestMapping(value = "save")
	public String save(AuditRule auditRule, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

		if(auditRule.getId() == null && auditRuleService.checkExist(auditRule.getAuditOperandTypeId())){//校验是否已设置审核规则
			addMessage(model, "该审核对象已设置审核规则，保存审核规则'" + auditRule.getName() + "'失败");
			return form(auditRule, model);
		}

		if (!beanValidator(model, auditRule)){
			return form(auditRule, model);
		}

		auditRuleService.saveAuditRule(auditRule);
		addMessage(redirectAttributes, "保存审核组'" + auditRule.getName() + "'成功");
		
		return "redirect:" + adminPath + "/sys/auditRule/auditRuleList?repage";
	}


	/**
	 * 删除审核组
	 * @param auditGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditRule:edit")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(required=true) Long id, RedirectAttributes redirectAttributes) {
		
		auditRuleService.delete(id);
		addMessage(redirectAttributes, "审核规则删除成功");
		return "redirect:" + adminPath + "/sys/auditRule/auditRuleList?repage";
	}

}
