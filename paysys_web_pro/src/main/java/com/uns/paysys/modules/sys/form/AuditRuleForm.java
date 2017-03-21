package com.uns.paysys.modules.sys.form;

import com.uns.paysys.modules.merc.form.BaseForm;

public class AuditRuleForm extends BaseForm {
	
	private Long auditOperandTypeId;
	
	private String auditRuleName;
	
	private String valid;
	
	private String[] auditStepName;
	
	private Long[] auditGroupId;

	public Long getAuditOperandTypeId() {
		return auditOperandTypeId;
	}

	public void setAuditOperandTypeId(Long auditOperandTypeId) {
		this.auditOperandTypeId = auditOperandTypeId;
	}

	public String getAuditRuleName() {
		return auditRuleName;
	}

	public void setAuditRuleName(String auditRuleName) {
		this.auditRuleName = auditRuleName;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String[] getAuditStepName() {
		return auditStepName;
	}

	public void setAuditStepName(String[] auditStepName) {
		this.auditStepName = auditStepName;
	}

	public Long[] getAuditGroupId() {
		return auditGroupId;
	}

	public void setAuditGroupId(Long[] auditGroupId) {
		this.auditGroupId = auditGroupId;
	}
	
	

}
