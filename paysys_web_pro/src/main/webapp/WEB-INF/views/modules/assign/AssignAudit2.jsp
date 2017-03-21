<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分配推广商</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() { 
	$("#btnReject").click(function(){
		top.$.jBox.confirm("确认要拒绝审核吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#inputForm").attr("action","${ctx}/merc/assign/assignReject");
				$("#inputForm").submit();
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
	});
	$("#no").focus();
	$("#inputForm").validate({
		rules: {
			loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
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
		<li><a href="${ctx}/merc/assign/assignList">推广商分配列表</a></li>
		<%-- <li class="active"><a href="${ctx}/merc/assign/assign?id=${spreadMerchant.id}"><shiro:hasPermission name="sys:role:edit">推广商分配</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">商户列表</shiro:lacksPermission></a></li>
 --%>
	</ul>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4">推广商名称: <b>${spreadMerchant.merchantName}</b></span>
		</div>
	</div>
	<sys:message content="${message}" />

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>Email</th>
				<th>商户类型</th>
				<th>商户状态</th>
				<shiro:hasPermission name="sys:user:edit">
					<!-- <th>操作</th> -->
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${accounts}" var="a">
				<tr>
					<td>${a.ID}</a></td>
					<td>${a.NAME}</td>
					<td>${a.EMAIL}</td>
					<td>${fns:getDictLabel(a.ACCOUNT_TYPE,'ACCOUNT_TYPE','')}</td>
				     <td>${fns:getDictLabel(a.STATUS,'ACCOUNT_STATUS','')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
			<div class="user-detail">
			<p class="">审核历史记录</p>


		<!-- 审核历史 -->
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>操作时间</th>
					<th>操作人员</th>
					<th>操作类型</th>
					<th>审核意见</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${auditHisList}" var="map">
				<tr>
					<td><fmt:formatDate value="${map.UPDATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${fns:getUserNameById(map.UPDATE_USER)}</td>
					<td>
						<c:if test="${map.NAME != null}">${map.NAME}</c:if>
						<c:if test="${map.POST_STATUS == -1 }">创建</c:if>
						<c:if test="${map.POST_STATUS == 1 }">提交</c:if>
					</td>
					<td>${map.ATTITUDE}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
	<div class="breadcrumb">
			<form:form id="inputForm" modelAttribute="spreadMerchant"
		action="${ctx}/merc/assign/assignAudit"
		method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${spreadMerchant.id}" /> <input
				id="idsArr" type="hidden" name="idsArr" value="" />
			<div class="control-group">
				<label class="control-label">审核意见:</label>
				<div class="controls">
					<form:textarea path="remarks" htmlEscape="false" rows="3"
						maxlength="200" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnAgree" class="btn btn-primary" type="submit"
					value="同意" />&nbsp; <input id="btnReject" class="btn btn-primary"
					type="button" value="拒绝" />&nbsp;<input id="btnCancel" class="btn"
					type="button" value="返 回" onclick="history.go(-1)" />
			</div>
	</form:form>
	</div>

</body>
</html>
