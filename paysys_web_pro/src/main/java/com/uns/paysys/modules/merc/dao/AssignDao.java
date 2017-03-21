package com.uns.paysys.modules.merc.dao;
import java.util.List;
import java.util.Map;
import com.uns.paysys.common.persistence.CrudDao;
import com.uns.paysys.common.persistence.annotation.MyBatisDao;
import com.uns.paysys.modules.merc.entity.SpreadMerchant;
import com.uns.paysys.modules.merc.form.AssignForm;

@MyBatisDao
public interface AssignDao extends CrudDao<SpreadMerchant> {

	
	public List<Map<String , Object>> findAssignList(AssignForm form);

}
