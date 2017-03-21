<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核组管理</title>
	<meta name="decorator" content="default"/>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/auditGroup/auditGroupList">审核组列表</a></li>
		<shiro:hasPermission name="sys:auditGroup:edit"><li class="active"><a href="${ctx}/sys/auditGroup/form">审核组添加</a></li></shiro:hasPermission>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="auditGroup" action="${ctx}/sys/auditGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
		<label class="control-label">审核组名称:</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	
	<div class="control-group">
		<label class="control-label">组内人员:</label>
		<div class="controls">
			<form:select path="userList" multiple="true" class="input-xlarge required">
				<form:options items="${fns:getUserList('')}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>	

	<div class="control-group">
		<label class="control-label">说明:</label>
		<div class="controls">
			<form:input path="remark" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
		
	<div class="form-actions">
		<shiro:hasPermission name="sys:auditGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>