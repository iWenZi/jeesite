<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="default" />
<%@ page import="com.uns.paysys.modules.sys.utils.ParamPlatfrom" %>
<script type="text/javascript">
$(document).ready(function() { 
	var id = document.getElementById("id").value;
	$.ajax({
		type:"POST",
		url:"${ctx}/merc/spreadMerchantBankInfo/getUploadUrl",
		data:'accountId='+id,
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				var name = eval(data)[i].fileName;
				var filekey = eval(data)[i].fileKey;
				$("#uploadFile").append('<div><input type="text" name="'+ name +'" value="'+ name +'" readonly="readonly" style="width:350px"/>'+
									 '<div style="display:inline"><input type="hidden" value="'+ filekey +'" /> <a href="###" onclick="remove(this)"></a><br/></div></div>');
			}
		}
	})
	
	$("input[name='Download']").click(function(){
		var oHiddenId =$(this).next().val()
		if(oHiddenId != ""){
			var f = document.createElement("form");
			document.body.appendChild(f);
			var i = document.createElement("input");
			f.target="_blank";
			i.type = "hidden";
			f.appendChild(i);
			i.value = oHiddenId;
			i.name = "fileId";
			f.action = "<%=ParamPlatfrom.DOWN_UPLOAD_URL%>";
			f.submit();
		}
	})
	
	$("#btnReject").click(function(){
		top.$.jBox.confirm("确认要拒绝审核吗？","系统提示",function(v,h,f){
			if(v=="ok"){
				$("#inputForm").attr("action","${ctx}/merc/spreadMerchantBankInfo/rejectSpreadMerchant");
				$("#inputForm").submit();
			}
		},{buttonsFocus:1});
		top.$('.jbox-body .jbox-icon').css('top','55px');
	});
	
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


function downAll(){
	var accountId = document.getElementById("id").value;
	var f = document.createElement("form");
	document.body.appendChild(f);
	var i = document.createElement("input");
	f.target="_blank";
	i.type = "hidden";
	f.appendChild(i);
	i.value = accountId;
	i.name = "lsId";
	f.action = "<%=ParamPlatfrom.DOWNALL_UPLOAD_URL%>";
	f.submit();
}


$("#btnAgree").click(function(){
	top.$.jBox.confirm("确认要通过审核吗？","系统提示",function(v,h,f){
		if(v=="ok"){
			$("#inputForm").attr("action","${ctx}/merc/spreadMerchantBankInfo/spreadMerchantAudit");
			$("#inputForm").submit();
		}
	},{buttonsFocus:1});
	top.$('.jbox-body .jbox-icon').css('top','55px');
});


</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/merc/spreadMerchantBankInfo/spreadMerchantList">推广商列表</a></li>

		<li class="active"><a
			href="${ctx}/merc/spreadMerchantBankInfo/form?id=${feeRatio.id}">
				推广商审核 <shiro:lacksPermission name="merc:spreadMerchant:feeEdit">查看</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="spreadMerchant"
		action="${ctx}/merc/spreadMerchantBankInfo/spreadMerchantAudit"
		method="post" class="form-horizontal">
		<sys:message content="${message}" />
		<form:input path="id" type="hidden" />

		<div class="control-group" id="email">
			<label class="control-label">Email:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">推广商名称:</label>
			<div class="controls">
				<form:input path="merchantName" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />


			</div>
		</div>

		<div class="control-group">
			<label class="control-label">营业执照号或统一社会信用代码:</label>
			<div class="controls">
				<form:input path="licenceNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经营期限至:</label>
			<div class="controls">
				<input readonly="readonly" id="licenceNoDate" name="licenceNoDate"
					type="text" readonly="readonly" maxlength="20"
					class="input-mini Wdate required"
					value="<fmt:formatDate value="${spreadMerchant.licenceNoDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">组织机构代码证代码:</label>
			<div class="controls">
				<form:input path="organizeNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">税务登记证税字号:</label>
			<div class="controls">
				<form:input path="taxId" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证代码:</label>
			<div class="controls">
				<form:input path="organizeTrustNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证有效期至:</label>
			<div class="controls">
				<input readonly="readonly" id="organizeTrustNoDate"
					name="organizeTrustNoDate" type="text" readonly="readonly"
					maxlength="20" class="input-mini Wdate required"
					value="<fmt:formatDate value="${spreadMerchant.organizeTrustNoDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户许可证核准号:</label>
			<div class="controls">
				<form:input path="accountLicenceNo" htmlEscape="false"
					maxlength="50" class="required" disabled="true" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">销售人员:</label>
			<div class="controls">
				<form:input path="salesMan" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">注册资本:</label>
			<div class="controls">
				<form:input path="capitalAmount" htmlEscape="false" maxlength="50"
					class="required" disabled="true" /><span class="help-inline">单位：万元</span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">成立日期:</label>
			<div class="controls">
				<input id="foundedDate" name="foundedDate" type="text"
					readonly="readonly" readonly="readonly" maxlength="20"
					class="input-mini Wdate"
					value="<fmt:formatDate value="${spreadMerchant.foundedDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人:</label>
			<div class="controls">
				<form:input path="legalRepreName" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">手机号码:</label>
			<div class="controls">
				<form:input path="legalRepreMobile" htmlEscape="false"
					maxlength="50" class="required" disabled="true" />
	<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人身份证号码:</label>
			<div class="controls">
				<form:input path="legalRepreIdNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>


		<div class="control-group">
			<label class="control-label">身份证有效期至:</label>
			<div class="controls">
				<input id="legalRepreIdDate" name="legalRepreIdDate" type="text"
					readonly="readonly" readonly="readonly" maxlength="20"
					class="input-mini Wdate required"
					value="<fmt:formatDate value="${spreadMerchant.legalRepreIdDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">注册地址:</label>
			<div class="controls">
				<form:textarea path="regiAddr" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" disabled="true" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:textarea path="linkAddr" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" disabled="true" />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经办人:</label>
			<div class="controls">
				<form:input path="operator" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>


		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<form:input path="operatorTel" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="spreadMerchantType" class="input-large required"
					disabled="true">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('SPREADMERCHANT_TYPE')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
				<form:input path="merchantStatus" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">推广商编号:</label>
			<div class="controls">
				<form:input path="id" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
				<input id="createTime" name="legalRepreIdDate" type="text"
					readonly="readonly" readonly="readonly" maxlength="20"
					class="input-mini Wdate required"
					value="<fmt:formatDate value="${spreadMerchant.legalRepreIdDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">审核状态:</label>
			<div class="controls">
				<form:input path="auditStatus" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营范围:</label>
			<div class="controls">
				<form:textarea path="dealRange" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" disabled="true" />
			</div>
		</div>
		
				<div class="control-group">
			<label class="control-label">证件材料:</label>			
			     <div id="div-0" class="controls">
              		<input type="file" class="inputStyle" name="files" id="uploadUrl" disabled="true" /> 
              		<input id="add" type="button" value=" + " disabled="true" /> 
              		<input type="button" value=" × " onclick="delinput(this)"disabled="true" /><label>支持zip,pdf,png格式</label> 
              	</div>
              	 <div id="div-0" class="controls">
              	<table>
                  <tr>
                 	<td></td>
                 	<td id="uploadFile" style="width:600px"></td>
                 	<td></td>
                 </tr>
                 </table>
                 	</div>
              	<div id="div-0-null"></div>
			
		</div>

		<div class="control-group">
			<label class="control-label">账户名称:</label>
			<div class="controls">
				<form:input path="accountName" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">账户别名:</label>
			<div class="controls">
				<form:input path="accountAlias" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>


		<div class="control-group">
			<label class="control-label">开户行省份:</label>
			<div class="controls">


				<form:select path="provSeq" class="input-large required"
					id="province" disabled="true">
					<form:option value="" label="请选择省" />
					<form:options items="${allProvince}" itemLabel="cityName"
						itemValue="cityId" htmlEscape="false" />
				</form:select>

				<%-- 	<select id="province" name="provSeq">
					<option>请选择省</option>
					<c:forEach items="${AllProvince}" var="p">
						<option value="${p.cityId}">${p.cityName}</option>
					</c:forEach>
				</select>  --%>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户行城市:</label>
			<div class="controls">
				<!-- <select id="city" name="citySeq">
					<option>请选择市</option>
				</select> -->


<%-- 				<select id="citySeq" name="citySeq">
					<option>请选择省</option>
					<c:forEach items="${allCity}" var="p">
						<option value="${p.cityId}">${p.cityName}</option>
					</c:forEach>
				</select> --%>

							<form:select path="citySeq" class="input-large required"  name="citySeq" id="citySeq" disabled="true"> 
					<form:option value="" label="请选择" />
                    <form:options items="${allCity}" itemLabel="cityName"
						itemValue="cityId" htmlEscape="false" />
				</form:select>

				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">所属银行:</label>
			<div class="controls">
				<form:select path="bankSeq" class="input-large required"
					disabled="true">
					<form:option value="" label="请选择" />
					<form:options items="${allBank}" itemLabel="bankName"
						itemValue="bankId" htmlEscape="false" />
				</form:select>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户行名称:</label>
			<div class="controls">
				<form:input path="openAccountBankName" htmlEscape="false"
					maxlength="50" class="required" disabled="true" />

			</div>
		</div>


		<div class="control-group">
			<label class="control-label">银行账号:</label>
			<div class="controls">
				<form:input path="cardNo" htmlEscape="false" maxlength="50"
					class="required" disabled="true" />

			</div>
		</div>

		<div class="control-group">
			<label class="control-label">卡类型:</label>
			<div class="controls">
				<form:select path="cardType" class="input-large required"
					disabled="true">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('CRAD_TYPE')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>

			</div>
		</div>


		<div class="user-detail">
			<p class="">证件材料下载</p>


			<table id="contentTable"
				class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>文件名称</th>
						<th>文件格式</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:set var="aa" value="1"/>
				
					<c:forEach items="${ad}" var="m">
						<tr>
							<td>${aa}<c:set var="aa" value="${aa+1}"/><input type="hidden" value="${m.filekey}"></td>
							<td>${m.fileName}</td>
							<td>${m.fileType}</td>
							<td>
							<input  name="Download"  class="btn"
				type="button" value="下载" /><input type="hidden" value="${m.filekey}" > <input type="button" class="btn" value="下载全部" onclick="downAll()"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div class="user-detail">
			<p class="">审核历史记录</p>


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
		
		</div>
		<div class="control-group">
			<label class="control-label">审核意见:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge required" />
						<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnAgree"  class="btn btn-primary" type="submit"
				value="同意" />&nbsp; <input  id="btnReject" class="btn btn-primary"
				type="button" value="拒绝" />&nbsp;<input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>


	<script type="text/javascript">
		$("#province")
				.change(
						function() {
							$("#citySeq option").remove();
							var level = $("#province").val();
							if (level == "请选择省") {

								$("#citySeq").append(new Option("请选择市"));
							}
							$
									.ajax({
										type : "post",
										url : "${ctx}/merc/spreadMerchantBankInfo/selectProvinceAllCity",
										data : {
											level : level
										},
										dataType : "json", //表示返回值类型，不必须
										success : function(data) {
											$.each(data, function(index, obj) {

												$("#citySeq").append(
														new Option(
																obj.cityName,
																obj.cityId));
											});
										}
									});

						})
	</script>
</body>
</html>