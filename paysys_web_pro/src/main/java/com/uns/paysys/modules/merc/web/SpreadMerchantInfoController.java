package com.uns.paysys.modules.merc.web;

import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.merc.entity.Account;
import com.uns.paysys.modules.merc.entity.AccountData;
import com.uns.paysys.modules.merc.entity.AuditOperandType;
import com.uns.paysys.modules.merc.entity.AuditOperation;
import com.uns.paysys.modules.merc.entity.AuditRule;
import com.uns.paysys.modules.merc.entity.BankInfo;
import com.uns.paysys.modules.merc.entity.CityInfo;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.entity.SpreadMerchantBankInfo;
import com.uns.paysys.modules.merc.entity.SpreadmerchantHis;
import com.uns.paysys.modules.merc.service.AccountDataService;
import com.uns.paysys.modules.merc.service.AccountService;
import com.uns.paysys.modules.merc.service.AuditHistoryService;
import com.uns.paysys.modules.merc.service.AuditOperandTypeService;
import com.uns.paysys.modules.merc.service.AuditOperationService;
import com.uns.paysys.modules.merc.service.AuditRuleService;
import com.uns.paysys.modules.merc.service.BankInfoService;
import com.uns.paysys.modules.merc.service.CityInfoService;
import com.uns.paysys.modules.merc.service.SperadMercHisService;
import com.uns.paysys.modules.merc.service.SpreadMerchantBankInfoService;
import com.uns.paysys.modules.merc.service.SpreadMerchantInfoService;
import com.uns.paysys.modules.sys.utils.AESEncrypt;
import com.uns.paysys.modules.sys.utils.HttpClientUtils;
import com.uns.paysys.modules.sys.utils.ParamPlatfrom;
import com.uns.paysys.modules.sys.utils.StringUtil;
import com.uns.paysys.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

/**
 * 推广商类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/merc/spreadMerchantBankInfo")
public class SpreadMerchantInfoController extends BaseController {

	@Autowired
	private SpreadMerchantInfoService spreadMerchantInfoService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private BankInfoService bankInfoService;
	@Autowired
	private SpreadMerchantBankInfoService spreadMerchantBankInfoService;
	@Autowired
	private SperadMercHisService spreadMercHisService;
	@Autowired
	private AccountDataService accountDataService;
	@Autowired
	private AuditOperandTypeService auditOperandTypeService;
	@Autowired
	private AuditRuleService auditRuleService;
	@Autowired
	private AuditOperationService auditOperationService;
	@Autowired
	private AuditHistoryService auditHistoryService;
	@Autowired
	private AccountService accountService;
	/**
	 * 所有方法的前置方法，如果id不为空 根据id查询推广商
	 * 
	 * @param id
	 * @return
	 */
	/*
	 * @ModelAttribute("spreadMerchant") public SpreadMerchant
	 * get(@RequestParam(required=false) String id) { if
	 * (StringUtils.isNotBlank(id)){ return
	 * spreadMerchantInfoService.getSpreadMerchant(Long.valueOf(id)); }else{
	 * return new SpreadMerchant(); } }
	 */

	/**
	 * 推广商信息列表
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping({ "spreadMerchantList", "" })
	public String spreadMerchantList(SpreadMerchant spreadMerchant, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SpreadMerchant> page = spreadMerchantInfoService
				.findSpreadMerchant(new Page<SpreadMerchant>(request, response), spreadMerchant);
		List list=page.getList();
		for(HashMap<String, Object> a:(List<HashMap<String, Object>>)list){
			a.put("ENCRYPTEMAIL", StringUtil.subEmail((String)a.get("EMAIL")));
			a.put("ENCRYPTPHONE", StringUtil.subTel((String)a.get("LEGAL_REPRE_MOBILE")));
			a.put("ENCRYPTCARDNO", StringUtil.subBankCard(AESEncrypt.decrypt((String)a.get("CARD_NO"))));
		}
		model.addAttribute("page", page);
		return "modules/promoter/promoterList2";
	}

	/**
	 * 跳转推广商页面
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toSpreadMerchant")
	public String toSpreadMerchant(SpreadMerchant spreadMerchant, HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam(required = false) String id) {
		SpreadMerchantBankInfo bankInfo = new SpreadMerchantBankInfo();
		if (StringUtils.isNotBlank(id)) {
			spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));// 根据id查询推广商
			bankInfo = spreadMerchantBankInfoService.getSpreadMerchantBankInfoById(Long.valueOf(id));// 根据id查询推广商银行信息
			spreadMerchant.setAccountName(bankInfo.getAccountName());
			spreadMerchant.setAccountAlias(bankInfo.getAccountAlias());
			spreadMerchant.setProvSeq(bankInfo.getProvSeq());
			spreadMerchant.setCitySeq(bankInfo.getCitySeq());
			spreadMerchant.setBankSeq(bankInfo.getBankSeq());
			spreadMerchant.setOpenAccountBankName(bankInfo.getOpenAccountBankName());
			spreadMerchant.setCardNo(AESEncrypt.decrypt(bankInfo.getCardNo()));
			spreadMerchant.setCardType(bankInfo.getCardType());
			List<CityInfo> allCity = cityInfoService.provinceAllCity(bankInfo.getProvSeq());// 查询省份下所属城市
			List<SpreadmerchantHis> spreadmerchantHis = spreadMercHisService.selectByPrimaryKey(Long.valueOf(id));//
			request.setAttribute("spreadmerchantHis", spreadmerchantHis);// 修改历史
			request.setAttribute("allCity", allCity);
		}
		List<CityInfo> allProvince = cityInfoService.findAllProvince();// 查询所有城市
		request.setAttribute("allProvince", allProvince);
		List<BankInfo> allBank = bankInfoService.findAllBank();// 查询所有银行
		request.setAttribute("allBank", allBank);

		model.addAttribute("spreadMerchant", spreadMerchant);
		return "modules/promoter/promoterAdd2";
	}

	/**
	 * 查询省的下属市
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "selectProvinceAllCity")
	public void selectProvinceAllCity(HttpServletRequest request, HttpServletResponse response, Long level) {
		List<CityInfo> allCity = cityInfoService.provinceAllCity(level);// 异步联动查询城市
		request.setAttribute("allProvince", allCity);
		try {
			String city = JSONArray.toJSONString(allCity);// jsong转换String
			response.getWriter().print(city);
			response.getWriter().flush();
			response.getWriter().close();// 传送到页面
		} catch (Exception e) {
			e.printStackTrace();
		}

	}




	/**
	 * 新增推广商、修改推广商
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "spreadMerchantFrom")
	public String spreadMerchantFrom(@RequestParam("files") MultipartFile[] files,
			SpreadMerchant spreadMerchant, HttpServletRequest request, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		spreadMerchant.setCreateUser(String.valueOf(UserUtils.getUser().getId()));// 创建人
		spreadMerchant.setUpdateUser(String.valueOf(UserUtils.getUser().getId()));// 修改人
		spreadMerchant.setCreateTime(new Date());// 创建时间
		spreadMerchant.setAuditStatus(DictConstUtil.AUDIT_STATUS_0);// 审核状态 待审核
		spreadMerchant.setDelFlag(DictConstUtil.AUDIT_STATUS_0);// 删除状态 未删除
		spreadMerchant.setMerchantStatus(DictConstUtil.AUDIT_STATUS_1);// 推广商状态 有效
		spreadMerchant.setUpdateTime(new Date());
		if (spreadMerchant.getId() != null) {// 修改推廣商 保存修改历史，修改银行
			updateSpreadMerchant(spreadMerchant);// 调用修改推广商方法
			boolean flag = false;
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if (!"".equals(files[i].getOriginalFilename()) && null != files[i].getOriginalFilename())
					flag = true;
				break;
			}
			if (flag) {
				saveAccountDate(spreadMerchant, files, request);// 调用多文件上传
			}
			addMessage(redirectAttributes, "已成功修改推广商！");
		} else {
			saveSpreadMerchant(spreadMerchant);// 调用推广商新增方法
			saveAccountDate(spreadMerchant, files, request);// 调用多文件上传
															// （上传成功后保存数据库记录）方法
			addMessage(redirectAttributes, "已成功插入推广商！");
		}

		// 获取审核操作对象
		AuditOperandType type = auditOperandTypeService
				.getAuditOperandType(DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO, DictConstUtil.ACTION_FLAG_1);
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
		return "redirect:" + adminPath + "/merc/spreadMerchantBankInfo/spreadMerchantList";
	}


	/**
	 * 新增推广商
	 * 
	 * @param spreadMerchant
	 */
	public void saveSpreadMerchant(SpreadMerchant spreadMerchant) {
		spreadMerchantInfoService.saveSpreadMerchant(spreadMerchant);
		SpreadMerchantBankInfo spreadMerchantBankInfo = new SpreadMerchantBankInfo();
		spreadMerchantBankInfo.setAccountName(spreadMerchant.getAccountName());// 银行名称
		spreadMerchantBankInfo.setAccountAlias(spreadMerchant.getAccountAlias());// 别名
		spreadMerchantBankInfo.setBankSeq(spreadMerchant.getBankSeq());// 所属银行
		spreadMerchantBankInfo.setCitySeq(spreadMerchant.getCitySeq());// 开户行城市
		spreadMerchantBankInfo.setProvSeq(spreadMerchant.getProvSeq());// 开户行省份
		spreadMerchantBankInfo.setCardNo(AESEncrypt.encrypt(spreadMerchant.getCardNo()));// 卡号
		spreadMerchantBankInfo.setCardType(spreadMerchant.getCardType());// 银行卡类别
		spreadMerchantBankInfo.setOpenAccountBankName(spreadMerchant.getOpenAccountBankName());// 开户行名称
		spreadMerchantBankInfo.setCreateUser(String.valueOf(UserUtils.getUser().getId()));// 创建人
		spreadMerchantBankInfo.setCreateTime(new Date());
		// 创建时间
		spreadMerchantBankInfo.setUpdateUser(String.valueOf(UserUtils.getUser().getId()));// 修改人
		spreadMerchantBankInfo.setUpdateTime(new Date());
		// 修改司机
		spreadMerchantBankInfo.setSpreadMerchantId(spreadMerchant.getId());
		spreadMerchantBankInfoService.inertBank(spreadMerchantBankInfo);
	}

	/**
	 * 修改推广商
	 * 
	 * @param spreadMerchant
	 */
	public void updateSpreadMerchant(SpreadMerchant spreadMerchant) {
		spreadMerchantInfoService.updateSpreadMerchant(spreadMerchant);
		SpreadMerchantBankInfo spreadMerchantBankInfo = new SpreadMerchantBankInfo();
		spreadMerchantBankInfo.setAccountName(spreadMerchant.getAccountName());// 银行名称
		spreadMerchantBankInfo.setAccountAlias(spreadMerchant.getAccountAlias());// 别名
		spreadMerchantBankInfo.setBankSeq(spreadMerchant.getBankSeq());// 所属银行
		spreadMerchantBankInfo.setCitySeq(spreadMerchant.getCitySeq());// 开户行城市
		spreadMerchantBankInfo.setProvSeq(spreadMerchant.getProvSeq());// 开户行省份
		spreadMerchantBankInfo.setCardNo(AESEncrypt.encrypt(spreadMerchant.getCardNo()));// 卡号
		spreadMerchantBankInfo.setCardType(spreadMerchant.getCardType());// 银行卡类别
		spreadMerchantBankInfo.setOpenAccountBankName(spreadMerchant.getOpenAccountBankName());// 开户行名称
		spreadMerchantBankInfo.setCreateUser(String.valueOf(UserUtils.getUser().getId()));// 创建人
		spreadMerchantBankInfo.setCreateTime(new Date());// 创建时间
		spreadMerchantBankInfo.setUpdateUser(String.valueOf(UserUtils.getUser().getId()));// 修改人
		spreadMerchantBankInfo.setUpdateTime(new Date());// 修改时间
		spreadMerchantBankInfo.setSpreadMerchantId(spreadMerchant.getId());
		spreadMerchantBankInfoService.updateSpreadMerchantBankInfo(spreadMerchantBankInfo);// 修改推广商银行信息
		SpreadmerchantHis his = new SpreadmerchantHis();// 修改历史
		his.setOperationtime(new Date());
		his.setCreatetime(new Date());
		his.setCreateuser(String.valueOf(UserUtils.getUser().getId()));
		his.setOperator(String.valueOf(UserUtils.getUser().getId()));
		BigDecimal bid = BigDecimal.valueOf(spreadMerchant.getId());
		his.setRemark(spreadMerchant.getRemark());
		his.setSpreadMerchantId(bid);
		spreadMercHisService.saveSpreadMerchantHis(his);// 保存修改历史
	}
	/**
	 * 多文件上传 （上传成功后保存数据库记录）
	 * 
	 * @param spreadMerchant
	 */
	public void saveAccountDate(SpreadMerchant spreadMerchant, @RequestParam("files") MultipartFile[] files,
			HttpServletRequest request) {

		List<String> url = null;
		Long accountSeq = new Long(spreadMerchant.getId());
		try {
			url = uploadFile(files, request, accountSeq);

		} catch (Exception e1) {
			request.setAttribute("messageZip", "系统错误");
			e1.printStackTrace();
		}

		// 多文件上传 （上传成功后保存数据库记录）
		for (int i = 0; i < url.size(); i++) {
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(url.get(i));
			for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject o = (JSONObject) jsonArray.get(j);
				String fileName = o.getString("fileName");
				int pos = fileName.lastIndexOf(".");
				String fileType = fileName.substring(pos);
				AccountData accountData = new AccountData();
				BigDecimal bid = BigDecimal.valueOf(accountSeq);
				accountData.setAccountSeq(bid);
				accountData.setDataFlag(DictConstUtil.AUDIT_STATUS_1);//状态
				accountData.setRet(o.getString("ret"));
				accountData.setFilekey(o.getString("fileId"));
				accountData.setFileName(o.getString("fileName"));
				accountData.setCreateUser(String.valueOf(UserUtils.getUser().getId()));
				accountData.setUpdateUser(String.valueOf(UserUtils.getUser().getId()));
				accountData.setFileType(fileType);
				accountDataService.insertSelective(accountData);
				break;
			}
		}
	}
	
	/**
	 * 删除推广商
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:infoDelete")
	@RequestMapping(value = "deleteSpreadMerchant")
	public String deleteSpreadMerchant(@RequestParam(required = true) String id,
			RedirectAttributes redirectAttributes) {

		SpreadMerchant merchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));

		if (DictConstUtil.AUDIT_STATUS_2.equals(merchant.getAuditStatus())
				|| DictConstUtil.AUDIT_STATUS_1.equals(merchant.getAuditStatus())) {
			addMessage(redirectAttributes, "推广商审核成功或推广商审核中，无法删除！");
		} else {
			merchant.setUpdateTime(new Date());
			merchant.setUpdateUser(String.valueOf(UserUtils.getUser().getId()));
			merchant.setDelFlag(DictConstUtil.AUDIT_STATUS_1);// 删除状态 已删除
			spreadMerchantInfoService.updateSpreadMerchant(merchant);
			addMessage(redirectAttributes, "删除推广商成功");
		}
		return "redirect:" + adminPath + "/merc/spreadMerchantBankInfo/spreadMerchantList?repage";
	}

	/**
	 * 跳转推广商审核页面
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:infoAudit")
	@RequestMapping(value = "toSpreadMerchantAudit")
	public String toSpreadMerchantAudit(SpreadMerchant spreadMerchant, HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam(required = false) String id) {
		SpreadMerchantBankInfo bankInfo = new SpreadMerchantBankInfo();
		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));
		bankInfo = spreadMerchantBankInfoService.getSpreadMerchantBankInfoById(Long.valueOf(id));
		spreadMerchant.setAccountName(bankInfo.getAccountName());
		spreadMerchant.setAccountAlias(bankInfo.getAccountAlias());
		spreadMerchant.setProvSeq(bankInfo.getProvSeq());
		spreadMerchant.setCitySeq(bankInfo.getCitySeq());
		spreadMerchant.setBankSeq(bankInfo.getBankSeq());
		spreadMerchant.setOpenAccountBankName(bankInfo.getOpenAccountBankName());
		spreadMerchant.setCardNo(AESEncrypt.decrypt(bankInfo.getCardNo()));
		spreadMerchant.setCardType(bankInfo.getCardType());
		List<CityInfo> allCity = cityInfoService.provinceAllCity(bankInfo.getProvSeq());
		List<SpreadmerchantHis> spreadmerchantHis = spreadMercHisService.selectByPrimaryKey(Long.valueOf(id));
		request.setAttribute("spreadmerchantHis", spreadmerchantHis);
		request.setAttribute("allCity", allCity);
		List<CityInfo> allProvince = cityInfoService.findAllProvince();// 查询所有城市
		request.setAttribute("allProvince", allProvince);
		List<BankInfo> allBank = bankInfoService.findAllBank();// 查询所有银行
		request.setAttribute("allBank", allBank);
		List<AccountData> ad = accountDataService.getUrlFileName(Long.valueOf(id));
		// 审核历史
		List<Map<String, Object>> auditHisList = auditHistoryService.getAuditHis(Long.valueOf(id),
				DictConstUtil.SPREAD_MERCHANT_INFO, DictConstUtil.ACTION_FLAG_1);
		model.addAttribute("auditHisList", auditHisList);
		model.addAttribute("ad", ad);
		model.addAttribute("spreadMerchant", spreadMerchant);
		return "modules/promoter/promoterAudit2";
	}

	/**
	 * 推广商审核
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("merc:spreadMerchant:infoAudit")
	@RequestMapping(value = "spreadMerchantAudit")
	public String spreadMerchantAudit(SpreadMerchant spreadMerchant, HttpServletRequest request, HttpServletResponse response,
			Model model, @RequestParam(required = false) String id, RedirectAttributes redirectAttributes,
			@RequestParam(required = true) String remarks) throws Exception {
		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));

		if (DictConstUtil.AUDIT_STATUS_2.equals(spreadMerchant.getAuditStatus())
				|| DictConstUtil.AUDIT_STATUS_3.equals(spreadMerchant.getAuditStatus())) {
			addMessage(redirectAttributes, "审核失败，推广商已审核");
		} else {
			// 获取审核操作对象
			AuditOperandType type = auditOperandTypeService
					.getAuditOperandType(DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO, DictConstUtil.ACTION_FLAG_1);
			// 获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if (auditOperationService.checkAuditPrivilege(spreadMerchant.getId(), rule.getId(),
					UserUtils.getUser().getId())) {// 判断当前用户是否有下一审核步骤的权限
				auditOperationService.agreeSpreadMerchant(rule.getId(), spreadMerchant, remarks);
				addMessage(redirectAttributes, "审核同意成功！");
			} else {
				addMessage(redirectAttributes, "审核失败！你没有下一审核步骤的权限！");
			}

		}

		return "redirect:" + adminPath + "/merc/spreadMerchantBankInfo/spreadMerchantList?repage";
	}

	/**
	 * 推广商审核拒绝
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("merc:spreadMerchant:infoAudit")
	@RequestMapping(value = "rejectSpreadMerchant")
	public String rejectSpreadMerchant(SpreadMerchant spreadMerchant, @RequestParam(required = true) String id,
			@RequestParam(required = true) String remarks, RedirectAttributes redirectAttributes) throws Exception {

		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));

		if (DictConstUtil.AUDIT_STATUS_2.equals(spreadMerchant.getAuditStatus())
				|| DictConstUtil.AUDIT_STATUS_3.equals(spreadMerchant.getAuditStatus())) {
			addMessage(redirectAttributes, "审核失败！推广商已审核");
		} else {
			// 获取审核操作对象
			AuditOperandType type = auditOperandTypeService
					.getAuditOperandType(DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO, DictConstUtil.ACTION_FLAG_1);
			// 获取该对象的审核规则
			AuditRule rule = auditRuleService.getAuditRule(type.getId());
			if (auditOperationService.checkAuditPrivilege(spreadMerchant.getId(), rule.getId(),
					UserUtils.getUser().getId())) {// 判断当前用户是否有下一审核步骤的权限
				auditOperationService.rejectSpreadMerchant(rule.getId(), spreadMerchant, remarks);
				addMessage(redirectAttributes, "审核拒绝成功！");
			} else {
				addMessage(redirectAttributes, "审核失败！你没有下一审核步骤的权限！");
			}
		}
		return "redirect:" + adminPath + "/merc/spreadMerchantBankInfo/spreadMerchantList?repage";
	}

	/**
	 * 跳转推广商查看页面
	 * 
	 * @param spreadMerchant
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:infoView")
	@RequestMapping(value = "spreadMerchantView")
	public String spreadMerchantView(SpreadMerchant spreadMerchant, HttpServletRequest request,
			HttpServletResponse response, Model model, @RequestParam(required = false) String id) {
		SpreadMerchantBankInfo bankInfo = new SpreadMerchantBankInfo();
		spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));
		bankInfo = spreadMerchantBankInfoService.getSpreadMerchantBankInfoById(Long.valueOf(id));
		spreadMerchant.setAccountName(bankInfo.getAccountName());
		spreadMerchant.setAccountAlias(bankInfo.getAccountAlias());
		spreadMerchant.setProvSeq(bankInfo.getProvSeq());
		spreadMerchant.setCitySeq(bankInfo.getCitySeq());
		spreadMerchant.setBankSeq(bankInfo.getBankSeq());
		spreadMerchant.setOpenAccountBankName(bankInfo.getOpenAccountBankName());
		spreadMerchant.setCardNo(AESEncrypt.decrypt(bankInfo.getCardNo()));
		spreadMerchant.setCardType(bankInfo.getCardType());
		List<CityInfo> allCity = cityInfoService.provinceAllCity(bankInfo.getProvSeq());
		List<SpreadmerchantHis> spreadmerchantHis = spreadMercHisService.selectByPrimaryKey(Long.valueOf(id));
		request.setAttribute("spreadmerchantHis", spreadmerchantHis);
		request.setAttribute("allCity", allCity);
		List<CityInfo> allProvince = cityInfoService.findAllProvince();// 查询所有城市
		request.setAttribute("allProvince", allProvince);
		List<BankInfo> allBank = bankInfoService.findAllBank();// 查询所有银行
		request.setAttribute("allBank", allBank);
		List<AccountData> ad = accountDataService.getUrlFileName(Long.valueOf(id));
		// 审核历史
		List<Map<String, Object>> auditHisList = auditHistoryService.getAuditHis(Long.valueOf(id),
				DictConstUtil.SPREAD_MERCHANT_INFO, DictConstUtil.ACTION_FLAG_1);
		model.addAttribute("auditHisList", auditHisList);
		model.addAttribute("ad", ad);
		model.addAttribute("spreadMerchant", spreadMerchant);
		return "modules/promoter/promoterView2";
	}

	private List<String> uploadFile(@RequestParam("files") MultipartFile[] files, HttpServletRequest request,
			Long accountSeq) throws Exception {
		List<String> list = new ArrayList<String>();
		Map<String, Object> param = new HashMap<String, Object>();
		MultipartFile[] loadfile = files;// 取得上传文件
		if (null != loadfile) {
			for (int i = 0; i < loadfile.length; i++) {

				String fileName = loadfile[i].getOriginalFilename();
				int pos = fileName.lastIndexOf(".");
				String postfix = fileName.substring(pos);

				InputStream fileInputStream = loadfile[i].getInputStream();
				String lsid = String.valueOf(accountSeq);
				param.put("lsid", lsid);
				param.put("fileType", postfix);
				param.put("fileName", fileName);
				System.out.println(("文件上传参数：" + param.toString()));
				Object result = HttpClientUtils.sendJson(ParamPlatfrom.CREATE_UPLOAD_URL, param,
						fileInputStream, Object.class);
				list.add((String) result);
				fileInputStream.close();
			}
		} else {
			return list;
		}
		return list;
	}

	/**
	 * 修改(查看)公司信息时取得用户的旧时上传文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getUploadUrl")
	public void getUploadUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		String accountId = request.getParameter("accountId");
		PrintWriter out = response.getWriter();
		if (!accountId.equals("")) {
			List<AccountData> ad = accountDataService.getUrlFileName(Long.valueOf(accountId));
			JSONArray json = new JSONArray();
			for (AccountData a : ad) {
				JSONObject jo = new JSONObject();
				jo.put("fileName", a.getFileName());
				jo.put("fileKey", a.getFilekey());
				jo.put("fileType", a.getFileType());
				json.add(jo);
			}

			out.print(json);
		}
	}

	/**
	 * 删除已上传文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delUploadFile")
	public void delUploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileKey = request.getParameter("filekey");
		PrintWriter out = response.getWriter();
		if (!fileKey.equals("")) {
			int ad = accountDataService.delUploadFile(fileKey);
			if (ad > 0) {
				out.print("success");
			} else {
				out.print("fail");
			}
		}
	}

	/**
	 * 推广商分配页面
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "assign")
	public String assign(String id, Model model) {
		SpreadMerchant spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));
		List<Account> accounts = accountService.allAccountList(Long.valueOf(id));
		model.addAttribute("accounts", accounts);
		model.addAttribute("spreadMerchant", spreadMerchant);
		return "modules/assign/AssignMechant2";
	}

	/**
	 * 推广商分配审核页面
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:spreadMerchant:infoView")
	@RequestMapping(value = "assignAudit")
	public String assignAudit(String id, Model model) {
		SpreadMerchant spreadMerchant = spreadMerchantInfoService.getSpreadMerchantById(Long.valueOf(id));
		List<Account> accounts = accountService.allAccountList(Long.valueOf(id));
		// 审核历史
		List<Map<String, Object>> auditHisList = auditHistoryService.getAuditHis(Long.valueOf(id),
				DictConstUtil.OPREAND_CLASS_SPREAD_MERCHANT_INFO_ASSIGN, DictConstUtil.ACTION_FLAG_1);
		model.addAttribute("auditHisList", auditHisList);
		model.addAttribute("accounts", accounts);
		model.addAttribute("spreadMerchant", spreadMerchant);
		return "modules/assign/AssignAudit2";
	}

	/**
	 * 验证email是否有效
	 * 
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkEmail")
	public String checkEmail(String oldEmail, String email) {
		SpreadMerchant spreadMerchant = spreadMerchantInfoService.getSpreadMerchantByEmail(email);

		if (spreadMerchant == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商名称是否重复
	 * 
	 * @param merchantName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkMerchantName")
	public String checkMerchantName(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		String merchantName = request.getParameter("merchantName");
		if (spreadMerchantInfoService.getSpreadMerchantByMerchantName(merchantName) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商营业执照号或统一社会信用代码是否重复
	 * 
	 * @param licenceNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkLicenceNo")
	public String checkLicenceNo(String oldLicenceNo, String licenceNo) {
		if (spreadMerchantInfoService.getSpreadMerchantByLicenceNo(licenceNo) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商组织机构代码证代码是否重复
	 * 
	 * @param organizeNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkOrganizeNo")
	public String checkOrganizeNo(String oldorganizeNo, String organizeNo) {
		if (spreadMerchantInfoService.getSpreadMerchantByOrganizeNo(organizeNo) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商税务登记证税字号是否重复
	 * 
	 * @param taxId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkTaxId")
	public String checkTaxId(String oldTaxId, String taxId) {
		if (spreadMerchantInfoService.getSpreadMerchantByTaxId(taxId) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商机构信用代码证代码是否重复
	 * 
	 * @param organizeTrustNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkOrganizeTrustNo")
	public String checkOrganizeTrustNo(String oldOrganizeTrustNo, String organizeTrustNo) {
		if (spreadMerchantInfoService.getSpreadMerchantByOrganizeTrustNo(organizeTrustNo) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证推广商开户许可证核准号是否重复
	 * 
	 * @param accountLicenceNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkAccountLicenceNo")
	public String checkAccountLicenceNo(String oldAccountLicenceNo, String accountLicenceNo) {
		if (spreadMerchantInfoService.getSpreadMerchantByAccountLicenceNo(accountLicenceNo) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 验证上传文件格式
	 * 
	 * @param accountLicenceNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkFiles")
	public String checkFiles(@RequestParam("oldFiles") MultipartFile[] files) {
		String fileName = files[0].getOriginalFilename();
		int pos = fileName.lastIndexOf(".");
		String postfix = fileName.substring(pos);
		if ("zip".equals(postfix)) {
			return "true";
		}
		return "false";
	}
}
