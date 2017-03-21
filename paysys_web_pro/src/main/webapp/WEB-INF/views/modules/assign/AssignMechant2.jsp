<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配推广商</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/merc/assign/assignList">推广商分配列表</a></li>
		<%-- <li class="active"><a href="${ctx}/merc/assign/assign?id=${spreadMerchant.id}"><shiro:hasPermission name="sys:role:edit">推广商分配</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">商户列表</shiro:lacksPermission></a></li>
 --%>	</ul>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4">推广商名称: <b>${spreadMerchant.merchantName}</b></span>
		</div>
	</div>
	<sys:message content="${message}"/>
	<div class="breadcrumb">
		<form id="assignRoleForm" action="${ctx}/merc/assign/assignAccount" method="post" class="hide">
			<input type="hidden" name="id" value="${spreadMerchant.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn btn-primary" type="submit" value="分配商户"/>
		<input id="btnCancel" class="btn"
					type="button" value="返 回" onclick="history.go(-1)" />
		<script type="text/javascript">
			$("#assignButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/merc/assign/accounttoassign?id=${spreadMerchant.id}", "分配商户",810,$(top.document).height()-240,{
					buttons:{"确定分配":"ok", "清除已选":"clear", "关闭":true}, bottomText:"通过选择商户，然后分配给推广商。",submit:function(v, h, f){
						var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							// 删除''的元素
							if(ids[0]==''){
								ids.shift();
								pre_ids.shift();
							}
				/* 			if(pre_ids.sort().toString() == ids.sort().toString()){
								top.$.jBox.tip("未给推广商分配新商户！", 'info');
								return false;
							}; */
					    	// 执行保存
					    	loading('正在提交，请稍等...');
					    	var idsArr = "";
					    	for (var i = 0; i<ids.length; i++) {
					    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
					    	}
					    	$('#idsArr').val(idsArr);
					    	$('#assignRoleForm').submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>商户编号</th><th>商户名称</th><th>Email</th><th>商户类型</th><th>商户状态</th><shiro:hasPermission name="sys:user:edit"><!-- <th>操作</th> --></shiro:hasPermission></tr></thead>
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
</body>
</html>
