package com.uns.paysys.modules.merc.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.utils.DateUtils;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.common.utils.excel.ExportExcel;
import com.uns.paysys.common.web.BaseController;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetail;
import com.uns.paysys.modules.merc.entity.MerchantFeeDetailSum;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailForm;
import com.uns.paysys.modules.merc.form.MerchantFeeDetailSumForm;
import com.uns.paysys.modules.merc.service.MerchantFeeDetailService;

/**
 * 推广商分润查询类
 * @author tiantian.zhang
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/merc/merchantFeeDetail")
public class MerchantFeeDetailController extends BaseController{
	
	@Autowired
	private MerchantFeeDetailService merchantFeeDetailService;
	
	/**
	 * 推广商交易查询
	 * @param merchantFeeDetail
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("merc:merchantFeeDetail:feeView")
	@RequestMapping(value = "merchantFeeDetailList")
	public String merchantFeeDetailList(MerchantFeeDetailForm form,Model model,
					HttpServletRequest request, HttpServletResponse response){
		
		Map<String, Object> mapPage = merchantFeeDetailService.findMerchant(new Page<Map<String , Object>>(request, response), form);
		
		model.addAttribute("page", mapPage.get("page"));
		model.addAttribute("sumFee", mapPage.get("sumFee"));
		return "modules/merc/merchantFeeDetailList";
	}
	
	/**
	 * 导出推广商交易数据
	 * @param merchantFeeDetail
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("merc:merchantFeeDetail:feeExport")
	@RequestMapping(value = "exportFile", method=RequestMethod.POST)
	public String exportFile(MerchantFeeDetail merchantFeeDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
		try {
			String fileName = "推广商交易数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<MerchantFeeDetail> page = merchantFeeDetailService.exportMerchant(new Page<MerchantFeeDetail>(request, response, -1),merchantFeeDetail);
			new ExportExcel("推广商交易数据",MerchantFeeDetail.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出推广商交易失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/merc/merchantFeeDetail/merchantFeeDetailList?repage";
	}
	
	/**
	 * 汇总查询推广商交易
	 * @param form
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequiresPermissions("merc:merchantFeeDetail:feeViewSum")
	@RequestMapping(value = "sumMerchantList")
	public String sumMerchantList(MerchantFeeDetailSumForm form,Model model,
			HttpServletRequest request, HttpServletResponse response){
		
		Map<String,Object> mapPage = merchantFeeDetailService.getSumMerchant(new Page<Map<String , Object>>(request, response), form);
		
		model.addAttribute("mapPage",mapPage.get("page"));
		model.addAttribute("sumFee", mapPage.get("sumFee"));
		return "modules/merc/merchantFeeDetailSum";
	}
	
	/**
	 * 导出推广商汇总数据
	 * @param merchantFeeDetailSum
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("merc:merchantFeeDetail:feeExportSum")
	@RequestMapping(value = "exportFileSum", method=RequestMethod.POST)
	public String exportFileSum(MerchantFeeDetailSum merchantFeeDetailSum,HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
		try {
//			MerchantFeeDetailSum merchantFeeDetailSum = new MerchantFeeDetailSum();
//			PropertyUtils.copyProperties(merchantFeeDetailSum, merchantFeeDetailSumForm);
			String fileName = "推广商交易汇总"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			Page<MerchantFeeDetailSum> page = merchantFeeDetailService.exportMerchantSum(new Page<MerchantFeeDetailSum>(request, response, -1), merchantFeeDetailSum);
			new ExportExcel("推广商交易汇总数据",MerchantFeeDetailSum.class).setDataList(page.getList()).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出推广商交易汇总失败！失败信息："+e.getMessage());
		}
		return "redirect:" + adminPath + "/merc/merchantFeeDetail/merchantFeeDetailSum?repage";
	}
	
	/**
	 * 根据交易编号查询推广商交易明细
	 * @param merchantFeeDetail
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("merc:merchantFeeDetail:feeView")
	@RequestMapping(value = "viewMerchantFeeDetail")
	public String viewMerchantFeeDetail(@RequestParam(required=false) String id, Model model){
		Map map = null;
		if (StringUtils.isNotBlank(id)){
			map = merchantFeeDetailService.getViewMerchantById(Long.valueOf(id));
		}
		model.addAttribute("map", map);
		return "modules/merc/merchantFeeDetailView";
	}

}
