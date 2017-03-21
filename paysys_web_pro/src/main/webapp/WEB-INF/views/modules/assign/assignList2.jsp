<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>推广商分配</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a
			href="${ctx}/merc/assign/assignList">推广商分配列表</a></li>
<%-- 		<shiro:hasPermission name="merc:spreadMerchant:feeView">
			<li><a href="${ctx}/merc/spreadMerchantBankInfo/add?sort=10">推广商分配</a></li>
		</shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="assignForm"
		action="${ctx}/merc/assign/assignList"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />

			<label for="">账户email：</label>
		<form:input path="email" htmlEscape="false" maxlength="50"
			class="input-medium" />
		<label for="">推广商名称：</label>
		<form:input path="merchantName" htmlEscape="false" maxlength="50"
			class="input-medium" />

   <BR>
		<label for="">推广商状态：</label>
		<form:select path="merchantStatus" class="input-small">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('ACCOUNT_STATUS')}"
				itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
		
			<label for="" style="margin-left: 100px;">创建时间：</label>
		<input name="createTime" class="Wdate" type="text" id="d16"
			onfocus="WdatePicker({position:{left:0,top:0}})" /> - <input
			name="createTime" class="Wdate" type="text" id="d17"
			onfocus="WdatePicker({position:{left:0,top:0}})" />
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>





	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>推广商编号</th>
				<th>email</th>
				<th>推广商名称</th>
				<th>推广商状态</th>
				<th>销售人员</th>
				<th>创建时间</th>
				<th>分配审核状态</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${mapPage.list}" var="m">
				<tr>
					<td>${m.ID}</td>
					<td>${m.ENCRYPTEMAIL}</td>
					<td>${m.MERCHANT_NAME}</td>
				     <td>${fns:getDictLabel(m.MERCHANT_STATUS,'ACCOUNT_STATUS','')}</td>
					<td>${m.SALES_MAN}</td>
					<td><fmt:formatDate value="${m.CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<td>${fns:getDictLabel(m.ASSIGN_STATUS,'AUDIT_STATUS','')}</td>
					<td>
					<shiro:hasPermission name="merc:spreadMerchant:assign">
					<a href="${ctx}/merc/spreadMerchantBankInfo/assign?id=${m.ID}">分配</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="merc:spreadMerchant:assignAudit">
					 <a href="${ctx}/merc/spreadMerchantBankInfo/assignAudit?id=${m.ID}">审核</a>
					 </shiro:hasPermission>
					 </td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<div class="pagination">${mapPage}</div>
</body>
</html>