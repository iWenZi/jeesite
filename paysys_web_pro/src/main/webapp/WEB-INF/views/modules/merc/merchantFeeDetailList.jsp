<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">


<head>
<title>推广商分润查询</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
	$("#btnExport").click(function(){
		top.$.jBox.confirm("确认要导出推广商交易数据吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#searchForm").attr("action","${ctx}/merc/merchantFeeDetail/exportFile");
				$("#searchForm").submit();
				$("#searchForm").attr("action","${ctx}/merc/merchantFeeDetail/merchantFeeDetailList");
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
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
	#foot{
		color: gray;
	}
</style>
</head>

<body>
	<section>
	
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/merc/merchantFeeDetail/">推广商分润</a></li>
		</ul>
		
		<form:form id="searchForm" modelAttribute="merchantFeeDetailForm" action="${ctx}/merc/merchantFeeDetail/merchantFeeDetailList" method="post" 
			class="breadcrumb form8-search">
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
					value="<fmt:formatDate value="${merchantFeeDetailForm.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<label>--</label>
				<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
					value="<fmt:formatDate value="${merchantFeeDetailForm.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
			<div style="margin-top:8px;">
				<label>交易编号：</label>
				<form:input path="transId" htmlEscape="false" maxlength="50" class="input-large" />&nbsp;&nbsp;
				
				<label>订单编号：</label>
				<form:input path="orderId" htmlEscape="false" maxlength="50" class="input-large" />&nbsp;&nbsp;
				
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			</div>
	</form:form>
	<hr/>
	<sys:message content="${message}" />
	<table id="contentTable"	
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>推广商名称</th>
					<th>交易编号</th>
					<th>订单编号</th>
					<th>商户名称</th>
					<th>收款方</th>
					<th>付款方</th>
					<th>交易类型</th>
					<th>交易状态</th>
					<th>交易时间</th>
					<th>交易金额</th>
					<th>是否冲正</th>
					<th>推广商费率</th>
					<th>商户费率</th>
					<th>推广费</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="map">
					<tr>
						<td>${fns:getSpreadMercNameById(map.MERCHANT_ID,'未知推广商')}</td>
						<td><a href="${ctx}/merc/merchantFeeDetail/viewMerchantFeeDetail?id=${map.TRANS_ID}">${map.TRANS_ID}</a></td>
						<td>${map.ORDER_ID}</td>
						<td>
							<c:if test="${map.ACCOUNT_TYPE=='B' }">${map.CREDIT_NAME}</c:if>
							<c:if test="${map.ACCOUNT_TYPE=='C' }">${map.DEBIT_NAME}</c:if>
						</td>
						<td>${map.CREDIT_NAME}</td>
						<td>${map.DEBIT_NAME}</td>
						<td>${fns:getActionName(map.ACTION_SEQ,'未知交易类型')}</td>
						<td>
							${fns:getDictLabel(map.TRANS_STATUS, 'trans_status', '未知交易状态')}
						</td>
						<td><fmt:formatDate value="${map.CREATE_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${map.AMOUNT}</td>
						<td>
							<c:if test="${map.REVERSE_FLAG == null}">否</c:if>
							<c:if test="${map.REVERSE_FLAG != null}">是</c:if>
						</td>
						<td>
							<c:if test="${map.SPREAD_FEE_METHOD == '1' }">${map.SPREAD_FEE}  /笔</c:if>
							<c:if test="${map.SPREAD_FEE_METHOD == '2' }">${map.SPREAD_FEE} %</c:if>
						</td>
						<td>
							<c:if test="${map.ACCOUNT_FEE_METHOD == '1' }">${map.ACCOUNT_FEE}  /笔</c:if>
							<c:if test="${map.ACCOUNT_FEE_METHOD == '2'}">${map.ACCOUNT_FEE} %</c:if>
						</td>
						<td><fmt:formatNumber pattern="0.00">${map.FEE}</fmt:formatNumber></td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div id="foot" class="pagination">
			<br>交易总笔数：${page.count }笔&nbsp;&nbsp;&nbsp;&nbsp;<span id="sumFee">推广费：<fmt:formatNumber pattern="0.00">${sumFee }</fmt:formatNumber></span><br><br>
			${page}
		</div>
		
		
	</section>
</body>

</html>