<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>
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
			f.action =  "<%=ParamPlatfrom.DOWN_UPLOAD_URL%>";
			f.submit();
		}
	})
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

</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/merc/spreadMerchantBankInfo/spreadMerchantList">推广商列表</a></li>

		<li class="active"><a
			href="${ctx}/merc/spreadMerchantBankInfo/form?id=${feeRatio.id}">
				推广商查看
				<shiro:lacksPermission name="merc:spreadMerchant:feeEdit">查看</shiro:lacksPermission>
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="spreadMerchant"
		action="${ctx}/merc/spreadMerchantBankInfo/addSpreadMerchant"
		method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:input path="id" type="hidden" />

		<div class="control-group" id="email">
			<label class="control-label">Email:</label>
			<div class="controls">
				${spreadMerchant.email}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">推广商名称:</label>
			<div class="controls">
					${spreadMerchant.merchantName}
				
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">营业执照号或统一社会信用代码:</label>
			<div class="controls">
					${spreadMerchant.licenceNo}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经营期限至:</label>
			<div class="controls">
			<fmt:formatDate value="${spreadMerchant.licenceNoDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">组织机构代码证代码:</label>
			<div class="controls">
					${spreadMerchant.organizeNo}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">税务登记证税字号:</label>
			<div class="controls">
				${spreadMerchant.taxId}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证代码:</label>
			<div class="controls">
				${spreadMerchant.organizeTrustNo}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证有效期至:</label>
			<div class="controls">
			<fmt:formatDate value="${spreadMerchant.organizeTrustNoDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户许可证核准号:</label>
			<div class="controls">
			${spreadMerchant.accountLicenceNo}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">销售人员:</label>
			<div class="controls">
					${spreadMerchant.salesMan}
				
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">注册资本:</label>
			<div class="controls">
					${spreadMerchant.capitalAmount}<span class="help-inline">单位：万元</span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">成立日期:</label>
			<div class="controls">
				<fmt:formatDate value="${spreadMerchant.foundedDate}" pattern="yyyy-MM-dd"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人:</label>
			<div class="controls">
			${spreadMerchant.legalRepreName}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">手机号码:</label>
			<div class="controls">
			${spreadMerchant.legalRepreMobile}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人身份证号码:</label>
			<div class="controls">
					${spreadMerchant.legalRepreIdNo}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">身份证有效期至:</label>
			<div class="controls">
		<fmt:formatDate value="${spreadMerchant.legalRepreIdDate}" pattern="yyyy-MM-dd"/>
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
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经办人:</label>
			<div class="controls">
			${spreadMerchant.operator}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
			${spreadMerchant.operatorTel}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
			${fns:getDictLabel(spreadMerchant.spreadMerchantType,'SPREADMERCHANT_TYPE','')}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">状态:</label>
			<div class="controls">
		    ${fns:getDictLabel(spreadMerchant.merchantStatus,'ACCOUNT_STATUS','')}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">推广商编号:</label>
			<div class="controls">
			${spreadMerchant.id}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">创建时间:</label>
			<div class="controls">
			<fmt:formatDate value="${spreadMerchant.createTime}" pattern="yyyy-MM-dd"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">审核状态:</label>
			<div class="controls">
			 ${fns:getDictLabel(spreadMerchant.auditStatus,'AUDIT_STATUS','')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">经营范围:</label>
			<div class="controls">
				<form:textarea path="dealRange" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" disabled="true" />
			</div>
		</div>

		<%-- 		<div class="control-group">
			<label class="control-label">证件材料:</label>
			<div class="controls">
				<form:input path="accountName" htmlEscape="false" maxlength="50"
					class="required" />
				
			</div>
		</div>
 --%>

		<div class="control-group">
			<label class="control-label">账户名称:</label>
			<div class="controls">
			${spreadMerchant.accountName}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">账户别名:</label>
			<div class="controls">
				${spreadMerchant.accountAlias}
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
			${spreadMerchant.openAccountBankName}
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">银行账号:</label>
			<div class="controls">
			${spreadMerchant.cardNo}
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">卡类型:</label>
			<div class="controls">
			 ${fns:getDictLabel(spreadMerchant.cardType,'CRAD_TYPE','')}
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
				<p class="">修改历史记录</p>


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
		<div class="form-actions"><input id="btnCancel" class="btn"
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