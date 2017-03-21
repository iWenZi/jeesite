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
			
			$("#btnAgree").click(function(){
				top.$.jBox.confirm("确认要通过审核吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#inputForm").attr("action","${ctx}/merc/spreadMerchant/agree");
						$("#inputForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			$("#btnReject").click(function(){
				top.$.jBox.confirm("确认要拒绝审核吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#inputForm").attr("action","${ctx}/merc/spreadMerchant/reject");
						$("#inputForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
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
		<li class="active"><a href="${ctx}/merc/spreadMerchant/spreadMerchantFeeList">费率审核</a></li>

		<shiro:hasPermission name="merc:spreadMerchant:feeEdit">
			<li><a href="${ctx}/merc/spreadMerchant/form?sort=10">费率添加</a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="merc:spreadMerchant:feeView">
			<li><a href="${ctx}/merc/spreadMerchant/spreadMerchantHisFeeList">历史费率</a></li>
		</shiro:hasPermission>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="feeRatio" action="${ctx}/merc/spreadMerchant/agree" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">推广商名称:</label>
			<div class="controls">
				<form:select path="relatingId" class="input-large required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getSpreadMercList('effective')}" itemLabel="merchantName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline">若此处为空，说明该推广商正在审核或商户状态不正常，请审核拒绝。</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">交易类型:</label>
			<div class="controls">		
				<form:select path="actionSeq" class="input-large required" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getActionTypeList()}" itemLabel="actionName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">收费方式:</label>
			<div class="controls">
				<form:select path="feeMethod" class="input-mini required" onchange="switchInputBox(this.value)" disabled="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('FEE_METHOD')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group" id="fixFee">
			<label class="control-label">固定费用（元）:</label>
			<div class="controls">
				<form:input path="fixFee" htmlEscape="false" maxlength="100"  class="required number" disabled="true"/>
			</div>
		</div>
		
		<div id="ratio">
		<div class="control-group" >
			<label class="control-label">比例（%）:</label>
			<div class="controls">
				<form:input path="ratio" htmlEscape="false" maxlength="100"  class="required number" disabled="true"/>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label">下限（元）:</label>
			<div class="controls">
				<form:input path="minFee" htmlEscape="false" maxlength="100"  class="required number" disabled="true"/>
			</div>
		</div>
			
		<div class="control-group">
				<label class="control-label">上限（元）:</label>
				<div class="controls">
					<form:input path="maxFee" htmlEscape="false" maxlength="100"  class="required number" disabled="true"/>
				</div>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">起始日期:</label>
			<div class="controls">
				<label class="lbl">
					<input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required"
						value="<fmt:formatDate value="${feeRatio.beginDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" disabled="true"/>
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">截止日期:</label>
			<div class="controls">
				<label class="lbl">
					<input id="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate required"
						value="<fmt:formatDate value="${feeRatio.endDate}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" disabled="true"/>
				</label>
			</div>
		</div>
	
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
		
		
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="merc:spreadMerchant:feeAduit">
				<input id="btnAgree" class="btn btn-primary" type="button" value="同  意"/>&nbsp;
				<input id="btnReject" class="btn btn-primary" type="button" value="拒  绝"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>