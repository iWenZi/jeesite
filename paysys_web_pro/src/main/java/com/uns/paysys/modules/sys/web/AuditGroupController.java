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

import com.uns.paysys.common.config.Global;
import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.sys.entity.AuditGroup;
import com.uns.paysys.modules.sys.entity.Role;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.service.AuditGroupService;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 审核组控制类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="${adminPath}/sys/auditGroup")
public class AuditGroupController extends BaseController {
	
	@Autowired
	private AuditGroupService auditGroupService;

	@ModelAttribute
	public AuditGroup get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return auditGroupService.get(Long.valueOf(id));
		}else{
			return new AuditGroup();
		}
	}


	/**
	 * 查看审核组列表
	 * @param auditGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditGroup:view")
	@RequestMapping(value = "auditGroupList")
	public String auditGroupList(AuditGroup auditGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		
        Page<AuditGroup> page = auditGroupService.findPage(new Page<AuditGroup>(request, response), auditGroup); 
        model.addAttribute("page", page);
		return "modules/sys/auditGroupList";
		
	}
	

	/**
	 * 新增、修改、查看 方法
	 * @param auditGroup
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditGroup:view")
	@RequestMapping(value = "form")
	public String form(AuditGroup auditGroup, Model model) {
		
		model.addAttribute("auditGroup", auditGroup);

		return "modules/sys/auditGroupForm";
	}
	
	

	
	/**
	 * 审核组新增保存、修改保存方法
	 * @param auditGroup
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:auditGroup:edit")
	@RequestMapping(value = "save")
	public String save(AuditGroup auditGroup, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {

		if (!beanValidator(model, auditGroup)){
			return form(auditGroup, model);
		}

		auditGroupService.saveAuditGroup(auditGroup);
		addMessage(redirectAttributes, "保存审核组'" + auditGroup.getName() + "'成功");
		
		return "redirect:" + adminPath + "/sys/auditGroup/auditGroupList?repage";
	}


	/**
	 * 删除审核组
	 * @param auditGroup
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:auditGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(@RequestParam(required=true) Long id, RedirectAttributes redirectAttributes) {
		
		auditGroupService.delete(id);
		addMessage(redirectAttributes, "审核组删除成功");
		return "redirect:" + adminPath + "/sys/auditGroup/auditGroupList?repage";
	}


}
