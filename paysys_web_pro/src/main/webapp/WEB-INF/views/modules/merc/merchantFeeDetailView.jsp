<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html lang="en">


<head>
<title>推广商分润交易明细</title>
<meta name="decorator" content="default" />
<style type="text/css">
	#contentTable{
		width:70%;
		margin:0 100px;
	}
	h4{
		margin:10px 30px;
		color:gray;
	}
	#btnCancel{
		margin:0px 100px;
	}
</style>
</head>

<body>
	<section>
			<h4>交易明细查询</h4><hr/>
	  <table id="contentTable"	
			class="table table-striped table-bordered table-condensed">
		<tr></tr>	
		<tr>
			<th text-align="right">推广商编号</th>
			<td>${map.MERCHANT_ID }</td>
			<th>推广商名称</th>
			<td>${map.MERCHANT_NAME }</td>
		</tr>
		<tr>
			<th>交易编号</th>
			<td>${map.TRANS_ID }</td>
			<th>订单编号</th>
			<td>${map.ORDER_ID }</td>
		</tr>
		<tr>
			<th>商户名称</th>
			<td>${map.CREDIT_SEQ_NAME }</td>
			<th>交易类型</th>
			<td>${map.ACTION_NAME }</td>
		</tr>
		<tr>
			<th>付款方</th>
			<td colspan="3">${map.DEBIT_SEQ_NAME }</td>
		</tr>
		<tr>
			<th>收款方</th>
			<td colspan="3">${map.CREDIT_SEQ_NAME }</td>
		</tr>
		<tr>
			<th>交易模式</th>
			<td></td>
			<th>交易状态</th>
			<td>
				<c:if test="${map.TRANS_STATUS == 0 }">待确认</c:if>
				<c:if test="${map.TRANS_STATUS == 3 }">交易成功</c:if>
				<c:if test="${map.TRANS_STATUS == 4 }">交易失败</c:if>
				<c:if test="${map.TRANS_STATUS == 5 }">等待买方付款</c:if>
				<c:if test="${map.TRANS_STATUS == 6 }">交易取消</c:if>
				<c:if test="${map.TRANS_STATUS == 9 }">退款成功</c:if>
				<c:if test="${map.TRANS_STATUS == 10 }">取消退款</c:if>
				<c:if test="${map.TRANS_STATUS == 11 }">待银行确认</c:if>
				<c:if test="${map.TRANS_STATUS == 12 }">可疑交易</c:if>
			</td>
		</tr>
		<tr>
			<th>交易开始时间</th>
			<td>${map.BEGIN_DATE}</td>
			<th>交易结束时间</th>
			<td>${map.END_DATE}</td>
		</tr>
		<tr>
			<th>交易最后更新时间</th>
			<td>${map.END_DATE}</td>
			<th>交易金额</th>
			<td>${map.AMOUNT}</td>
		</tr>
		<tr><td>...</td></tr><tr></tr>
		<tr>
			<th>付款方名称</th>
			<td>${map.DEBIT_SEQ_NAME}</td>
			<th>付款方Email</th>
			<td>${map.DEBIT_SEQ_EMAIL}</td>
		</tr>
		<tr>
			<th>付款方手机号码</th>
			<td>${map.DEBIT_SEQ_MOBILE}</td>
			<th>付款方手续费</th>
			<td><fmt:formatNumber pattern="0.00">${map.DEBIT_FEE }</fmt:formatNumber></td>
		</tr>
		<tr>
			<th>付款方实际交易金额</th>
			<td>${map.AMOUNT}</td>
			<th></th>
			<td></td>
		</tr>
		<tr></tr><tr><td>...</td></tr><tr></tr>
		<tr>
			<th>收款方名称</th>
			<td>${map.CREDIT_SEQ_NAME}</td>
			<th>收款方Email</th>
			<td>${map.CREDIT_SEQ_EMAIL}</td>
		</tr>
		<tr>
			<th>收款方手机号码</th>
			<td>${map.CREDIT_SEQ_MOBILE}</td>
			<th>收款方手续费</th>
			<td><fmt:formatNumber pattern="0.00">${map.CREDIT_FEE }</fmt:formatNumber></td>
		</tr>
		<tr>
			<th>收款方实际交易金额</th>
			<td>${map.AMOUNT}</td>
			<th></th>
			<td></td>
		</tr>
	  </table>
	  <hr/>
	  <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</section>
</body>

</html>