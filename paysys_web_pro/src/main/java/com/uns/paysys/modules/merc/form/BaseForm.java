package com.uns.paysys.modules.merc.form;

import java.util.Map;

import com.uns.paysys.common.persistence.Page;

public class BaseForm {
	
	protected Page<Map<String , Object>> mapPage;
	

	public Page<Map<String, Object>> getMapPage() {
		return mapPage;
	}

	public void setMapPage(Page<Map<String, Object>> mapPage) {
		this.mapPage = mapPage;
	}
	
	

}
