<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//初始化费率输入框
			var feeMethod = $("#feeMethod").val();
			if(feeMethod==1){//固定
				$("#fixFee").show();
				$("#ratio").hide();
			}else if(feeMethod==2){//比例
				$("#ratio").show();
				$("#fixFee").hide();
			}else if(feeMethod==3){
				alertx('暂不支持阶梯！');
			}else{
				$("#ratio").hide();
				$("#fixFee").hide();
			}
			
			
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
		
		function switchInputBox(feeMethod){
			if(feeMethod==1){//固定
				$("#fixFee").show();
				$("#ratio").hide();
			}else if(feeMethod==2){//比例
				$("#ratio").show();
				$("#fixFee").hide();
			}else if(feeMethod==3){
				alertx('暂不支持阶梯！');
			}else{
				$("#ratio").hide();
				$("#fixFee").hide();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/merc/spreadMerchant/spreadMerchantFeeList">推广商费列表</a></li>
	
		<%-- <li class="active">
			<shiro:hasPermission name="merc:spreadMerchant:feeEdit">
				<a href="${ctx}/merc/spreadMerchant/form?sort=10">费率添加</a>
			</shiro:hasPermission>
		</li> --%>
		
		<li class="active">
			<a href="${ctx}/merc/spreadMerchant/form?id=${feeRatio.id}">
				费率<shiro:hasPermission name="merc:spreadMerchant:feeEdit">${not empty feeRatio.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="merc:spreadMerchant:feeEdit">查看</shiro:lacksPermission>
			</a>
		</li>
		
		
		<li>
			<shiro:hasPermission name="merc:spreadMerchant:feeView">
				<a href="${ctx}/merc/spreadMerchant/spreadMerchantHisFeeList">历史费率</a>
			</shiro:hasPermission>
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="feeRatio" action="${ctx}/merc/spreadMerchant/saveSpreadMerchantFee" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="auditStatus"/>
		<sys:message content="${message}"/>
		<form:input path="id" type="hidden"/>
		<div class="control-group">
			<label class="control-label">推广商名称:</label>
			<div class="controls">
				<form:select path="relatingId" class="input-large required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getSpreadMercList('effective')}" itemLabel="merchantName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<font color="red">*</font>
				<span class="help-inline">仅审核通过且商户状态正常的推广商才会显示在此处。</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">交易类型:</label>
			<div class="controls">		
				<form:select path="actionSeq" class="input-large required">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getActionTypeList()}" itemLabel="actionName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收费方式:</label>
			<div class="controls">
				<form:select path="feeMethod" class="input-mini required" onchange="switchInputBox(this.value)">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('FEE_METHOD')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div class="control-group" id="fixFee">
			<label class="control-label">固定费用（元）:</label>
			<div class="controls">
				<form:input path="fixFee" htmlEscape="false" maxlength="100"  class="required number"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		
		<div id="ratio">
			<div class="control-group" >
				<label class="control-label">比例（%）:</label>
				<div class="controls">
					<form:input path="ratio" htmlEscape="false" maxlength="100"  class="required number"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">下限（元）:</label>
				<div class="controls">
					<form:input path="minFee" htmlEscape="false" maxlength="100"  class="required number"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label">上限（元）:</label>
				<div class="controls">
					<form:input path="maxFee" htmlEscape="false" maxlength="100"  class="required number"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">起始日期:</label>
			<div class="controls">
				<label class="lbl">
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required"
						value="<fmt:formatDate value="${feeRatio.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">截止日期:</label>
			<div class="controls">
				<label class="lbl">
					<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required"
						value="<fmt:formatDate value="${feeRatio.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</label>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="merc:spreadMerchant:feeEdit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>