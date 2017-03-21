<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">


<head>
<title>推广商分润汇总查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#btnExport")
								.click(
										function() {
											top.$.jBox
													.confirm(
															"确认要导出推广商交易汇总数据吗？",
															"系统提示",
															function(v, h, f) {
																if (v == "ok") {
																	$(
																			"#searchForm")
																			.attr(
																					"action",
																					"${ctx}/merc/merchantFeeDetail/exportFileSum");
																	$(
																			"#searchForm")
																			.submit();
																	$(
																			"#searchForm")
																			.attr(
																					"action",
																					"${ctx}/merc/merchantFeeDetail/sumMerchantList");
																}
															},
															{
																buttonsFocus : 1
															});
											top.$('.jbox-body .jbox-icon').css(
													'top', '55px');
										});
					});

	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
<style type="text/css">
#foot {
	color: gray;
}
</style>
</head>

<body>
	<section>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/merc/merchantFeeDetail/">推广商分润汇总</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="merchantFeeDetailSumForm" action="${ctx}/merc/merchantFeeDetail/sumMerchantList" 
				method="post" class="breadcrumb form8-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
			<div>
				<label>推广商名称：</label>
				<form:select path="merchantId" class="input-large">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getSpreadMercList('')}" itemLabel="merchantName" itemValue="id" htmlEscape="false"/>
				</form:select>&nbsp;&nbsp;&nbsp;&nbsp;
				
				<label>交易类型：</label>
				<form:select path="actionSeq" class="input-large">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getActionTypeList()}" itemLabel="actionName" itemValue="id" htmlEscape="false"/>
				</form:select>&nbsp;&nbsp;&nbsp;&nbsp;
				
				<label>日期范围：&nbsp;</label>
				<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${merchantFeeDetailSumForm.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<label>--</label>
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${merchantFeeDetailSumForm.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
				<input id="btnExport" class="btn btn-primary" type="button" value="导出" />
			</div>
		</form:form>
		<hr />
		<sys:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>推广商名称</th>
					<th>交易时间</th>
					<th>交易金额</th>
					<th>交易笔数</th>
					<th>推广费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mapPage.list }" var="map">
					<tr>
						<td>${fns:getSpreadMercNameById(map.MERCHANT_ID,'未知推广商')}</td>
						<td>${map.TRANS_DATE}</td>
						<td>${map.SUM_AMOUNT}</td>
						<td>${map.COUNT}</td>
						<td>${map.SUM_FEE}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="foot" class="pagination">
			<br>推广商分润总批次：${mapPage.count }笔&nbsp;&nbsp;&nbsp;&nbsp;<span
				id="sumFee">推广费：<fmt:formatNumber pattern="0.00">${sumFee }</fmt:formatNumber></span><br>
			<br> ${mapPage}
		</div>
	</section>
</body>

</html>