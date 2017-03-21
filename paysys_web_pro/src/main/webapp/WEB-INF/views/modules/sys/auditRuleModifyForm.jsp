<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核组管理</title>
	<meta name="decorator" content="default"/>
	<%-- <script type="text/javascript" src="${ctxStatic}/paysys/jquery.fix.clone.js"></script> --%>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')+"&loginName="+encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
		function addLine(){
			$("#contentTable tbody").append($("#copyTable tbody tr").clone(true, true));
			 reloadNum();
		}
		
		function delLine(element){
			 $(element).parent().parent().remove();
			 reloadNum();
		}
		
		function reloadNum(){
			var i = 1;
			$("#contentTable tbody tr").each(function () { //循环tab tbody下的tr
				$(this).find('td')[0].innerText = i++;
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/auditRule/auditRuleList">审核规则列表</a></li>
		<shiro:hasPermission name="sys:auditRule:edit"><li class="active"><a href="${ctx}/sys/auditRule/form">审核规则添加</a></li></shiro:hasPermission>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="auditRule" action="${ctx}/sys/auditRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		
	<div class="control-group">
		<label class="control-label">审核对象:</label>
		<div class="controls">
			<form:select path="auditOperandTypeId" class="input-large required">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getAuditOperandTypeList()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">审核规则名称:</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>	

	<div class="control-group">
		<label class="control-label">是否有效:</label>
		<div class="controls">
			<form:select path="valid">
				<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	
		<table id="copyTable" style="display:none">
			<tbody>
				<tr>
					<td></td>
					<td><form:input path="auditStepName" htmlEscape="false" maxlength="50" class="required" />
						<span class="help-inline"><font color="red">*</font> </span></td>
					<td><form:select path="auditGroupId" class="input-large required" multiple="false">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getAuditGroupList()}" itemLabel="name" itemValue="id" htmlEscape="false" />
						</form:select> <span class="help-inline"><font color="red">*</font> </span></td>
					<td><a href="javascript:void(0)" onclick="delLine(this)">删除</a></td>
				</tr>
			</tbody>
		</table>
		
		<!-- 原审核步骤 -->
		<table id="orgContentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>审核级别</th>
					<th>审核步骤名称</th>
					<th>审核组</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${auditRule.stepList }" var="step" varStatus="num">
					<tr>
						<td>${num.count}</td>
						<td>${step.name }</td>
						<td>${fns:getAuditGroupName(step.auditGroupId, '未知')}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<!-- 审核步骤 -->
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>审核级别</th>
					<th>审核步骤名称</th>
					<th>审核组</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td><form:input path="auditStepName" htmlEscape="false" maxlength="50" class="required" />
						<span class="help-inline"><font color="red">*</font> </span></td>
					<td><form:select path="auditGroupId" class="input-large required" multiple="false">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getAuditGroupList()}" itemLabel="name" itemValue="id" htmlEscape="false" />
						</form:select> <span class="help-inline"><font color="red">*</font> </span></td>
					<td><a href="javascript:void(0)" onclick="addLine()">添加</a></td>
				</tr>
			</tbody>
		</table>
		
	<div class="form-actions">
		<shiro:hasPermission name="sys:auditRule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>