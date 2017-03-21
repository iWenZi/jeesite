<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>推广商费率管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/merc/spreadMerchant/spreadMerchantFeeList">推广商费列表</a></li>
		<shiro:hasPermission name="merc:spreadMerchant:feeEdit">
			<li><a href="${ctx}/merc/spreadMerchant/form?sort=10">费率添加</a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="merc:spreadMerchant:feeView">
			<li  class="active"><a href="${ctx}/merc/spreadMerchant/spreadMerchantHisFeeList">历史费率</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="spreadMerchantForm" action="${ctx}/merc/spreadMerchant/spreadMerchantHisFeeList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>推广商名称：</label>
		<form:input path="merchantName" htmlEscape="false" maxlength="50" class="input-medium"/>
		&nbsp;&nbsp;
		<label>交易类型：</label>
		<form:select path="actionSeq" class="input-large">
			<form:option value="" label="请选择"/>
			<form:options items="${fns:getActionTypeList()}" itemLabel="actionName" itemValue="id" htmlEscape="false"/>
		</form:select>
		&nbsp;&nbsp;
		<label>收费方式：</label>
		<form:select path="feeMethod" class="input-mini">
			<form:option value="" label="请选择"/>
			<form:options items="${fns:getDictList('FEE_METHOD')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>

		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>推广商名称</th>
				<th>交易类型</th>
				<th>收费方式</th>
				<th>固定费用（元）</th>
				<th>比例（%）</th>
				<th>下限（元）</th>
				<th>上限（元）</th>
				<th>起始时间</th>
				<th>截止时间</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${mapPage.list}" var="map">
			<tr>
				<td>${map.MERCHANT_NAME}</td>
				<td>${fns:getActionName(map.ACTION_SEQ,'未知交易类型')}</td>
				<td>${fns:getDictLabel(map.FEE_METHOD,'FEE_METHOD','')}</td>
				<td>
					<c:if test="${map.FEE_METHOD == 1}">
						<fmt:formatNumber pattern="0.00">${map.FIX_FEE}</fmt:formatNumber>
					</c:if>
				</td>
				<td>
					<c:if test="${map.FEE_METHOD == 2}">
					<fmt:formatNumber pattern="0.00">${map.RATIO}</fmt:formatNumber>
					</c:if>
				</td>
				<td>
					<c:if test="${map.FEE_METHOD == 2}">
					<fmt:formatNumber pattern="0.00">${map.MIN_FEE}</fmt:formatNumber>
					</c:if>
				</td>
				<td>
					<c:if test="${map.FEE_METHOD == 2}">
					<fmt:formatNumber pattern="0.00">${map.MAX_FEE}</fmt:formatNumber>
					</c:if>
				</td>
				<td>${map.BEGIN_DATE}</td>
				<td>${map.END_DATE}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${mapPage}</div>
</body>
</html>