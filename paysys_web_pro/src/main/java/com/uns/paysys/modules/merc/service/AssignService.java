package com.uns.paysys.modules.merc.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uns.paysys.common.persistence.Page;
import com.uns.paysys.common.service.CrudService;
import com.uns.paysys.modules.merc.dao.AssignDao;
import com.uns.paysys.modules.merc.dao.SpreadMerchantDao;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.AssignForm;


/**
 * 推广商分配Service
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class AssignService {
	
	@Autowired
	private AssignDao assignDao;
	

	
	/**
	 * 查询推广商分配列表
	 * @param page
	 * @param spreadMerchant
	 * @return
	 */
	public Page<Map<String , Object>> findAssignList(Page<Map<String , Object>> page, AssignForm form) {
		// 设置分页参数
		form.setMapPage(page);
		// 执行分页查询
		page.setList(assignDao.findAssignList(form));
		return page;
	}
	

}
