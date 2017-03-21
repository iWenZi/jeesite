<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核组维护</title>
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
		<li class="active"><a href="${ctx}/sys/auditRule/auditRuleList">审核规则列表</a></li>
		<shiro:hasPermission name="sys:auditRule:edit"><li><a href="${ctx}/sys/auditRule/form">审核规则添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="auditRule" action="${ctx}/sys/auditRule/auditRuleList" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>审核对象名称</th>
				<th>审核规则名称</th>
				<th>是否有效</th>
				<th>是否使用</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditRule">
			<tr>
				<td>${fns:getAuditOperandTypeName(auditRule.auditOperandTypeId, '未知')}</td>
				<td>${auditRule.name}</td>
				<td>${fns:getDictLabels(auditRule.valid, 'yes_no', '未知')}</td>
				<td>${fns:getDictLabels(auditRule.used, 'yes_no', '未知')}</td>
				<td>
    				<a href="${ctx}/sys/auditRule/modifyForm?id=${auditRule.id}">
    					<c:if test="${auditRule.valid=='1' and auditRule.used=='0'}"><shiro:hasPermission name="sys:auditRule:edit">修改</shiro:hasPermission></c:if>
    					<shiro:lacksPermission name="sys:auditRule:edit">查看</shiro:lacksPermission>
    				</a>
					<%-- <shiro:hasPermission name="sys:auditRule:edit">
						<c:if test="${auditRule.used=='0' }"><a href="${ctx}/sys/auditRule/delete?id=${auditRule.id}" onclick="return confirmx('确认要删除该审核组吗？', this.href)">删除</a></c:if>
					</shiro:hasPermission> --%>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>