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
		<li class="active"><a href="${ctx}/sys/auditGroup/auditGroupList">审核组列表</a></li>
		<shiro:hasPermission name="sys:auditGroup:edit"><li><a href="${ctx}/sys/auditGroup/form">审核组添加</a></li></shiro:hasPermission>
	</ul>
	
	<form:form id="searchForm" modelAttribute="auditGroup" action="${ctx}/sys/auditGroup/auditGroupList" method="post" >
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	
	<sys:message content="${message}"/>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>审核组名称</th>
				<th>审核人</th>
				<th>说明</th>
				<th>使用情况</th>
				<shiro:hasPermission name="sys:auditGroup:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="auditGroup">
			<tr>
				<td>${auditGroup.name}</td>
				<td>
					<c:forEach var="userId" items="${auditGroup.userList}">
						${fns:getUserNameById(userId)}
					</c:forEach>
				</td>
				<td>${auditGroup.remark}</td>
				<td>${fns:getDictLabels(auditGroup.used, 'yes_no', '未知')}</td>
				<shiro:hasPermission name="sys:auditGroup:edit">
					<td>
	    				<a href="${ctx}/sys/auditGroup/form?id=${auditGroup.id}">修改</a>
	    				<%-- <c:if test="${auditGroup.used==0}">
							<a href="${ctx}/sys/auditGroup/delete?id=${auditGroup.id}" onclick="return confirmx('确认要删除该审核组吗？', this.href)">删除</a>
						</c:if> --%>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>