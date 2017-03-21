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
									 '<div style="display:inline"><input type="hidden" value="'+ filekey +'" /> <a href="###" onclick="remove(this)">删除</a><br/></div></div>');
			}
		}
	})
	
	$("#uploadUrl").blur(function(){

		var a = $("#uploadUrl").val();
		if(a == ""){
			alert("上传文件不允许为空！");
			return;
		}
		var format = a.substring(a.length-3);
		var arr = new Array();
		$("#div-0-null").find("input[id=uploadUrl]").each(function(i,val){
			arr[i] = $(val).val().substring($(val).val().lastIndexOf(".") + 1);
		});
		arr.push(format);
		for(var i=0;i<arr.length;i++){
			if(!(arr[i] =="zip" || arr[i] =="pdf" || arr[i] =="png")){
				alert("上传仅支持zip,pdf,png格式文件,请更改！");
				return;
			}
		}
	})
         
	$("#merchantName").blur(function(){
		 var name=$("#merchantName").val();
		$.ajax({
			type:'POST',
			url:"${ctx}/merc/spreadMerchantBankInfo/checkMerchantName",
			data:'merchantName='+name,
			success: function(date){
				if(date=="false")
			    {
					alert("推广商名称已存在");
					 $("input[name='merchantName']").val("").focus();
			    }
			}
		})
		
		
	})
	
	var aa=$("#id").val();
		if(aa==""){
	$("#no").focus(); 
	$("#inputForm").validate({
		rules: {
			email: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkEmail?oldEmail="+encodeURIComponent('${spreadMerchant.email}')},
			licenceNo: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkLicenceNo?oldLicenceNo="+encodeURIComponent('${spreadMerchant.licenceNo}')},
			organizeNo: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkOrganizeNo?oldOrganizeNo="+encodeURIComponent('${spreadMerchant.organizeNo}')},
			taxId: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkTaxId?oldTaxId="+encodeURIComponent('${spreadMerchant.taxId}')},
			organizeTrustNo: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkOrganizeTrustNo?oldOrganizeTrustNo="+encodeURIComponent('${spreadMerchant.organizeTrustNo}')},
			accountLicenceNo: {remote: "${ctx}/merc/spreadMerchantBankInfo/checkAccountLicenceNo?oldAccountLicenceNo="+encodeURIComponent('${spreadMerchant.accountLicenceNo}')}
			
		},
		messages: {
			email: {remote: "Email已存在"},
			licenceNo:{remote: "营业执照号或统一社会信用代码已存在"},
			organizeNo:{remote: "组织机构代码证代码已存在"},
			taxId:{remote: "税务登记证税字号已存在"},
			organizeTrustNo:{remote: "机构信用代码证代码已存在"},
			accountLicenceNo:{remote: "开户许可证核准号已存在"} ,
		}
	}); 
		}else
		{
			$("#no").focus(); 
			$("#inputForm").validate({
				
			})
			
		}
}); 
function remove(obj){
	var fileId = $(obj).parent().find('input').val();
	var name=$(obj).parent().parent().find('input').val();

	var message = confirm("确定要删除此文件吗？ 点击确定将不可找回！");
	if(message == true){
		$.ajax({
			type:"POST",
			url:"${ctx}/merc/spreadMerchantBankInfo/delUploadFile",
			data:'filekey='+fileId,
			success:function(data){
				if(data == "success"){
					$(obj).parent().parent().parent().find('input[name="'+name+'"]').parent().remove();
					alert("成功删除！");
				}else{
					alert("失败了!");
				}
			}
		});
		var f = document.createElement("form");
		document.body.appendChild(f);
		var i = document.createElement("input");
		f.target="_blank";
		i.type = "hidden";
		f.appendChild(i);
		i.value = fileId;
		i.name = "fileId";
		f.action = "<%=ParamPlatfrom.DELETE_UPLOAD_URL%>";
		f.submit();
	}
}

</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a
			href="${ctx}/merc/spreadMerchantBankInfo/spreadMerchantList">推广商列表</a></li>

		<li class="active"><a
			href="${ctx}/merc/spreadMerchantBankInfo/toSpreadMerchant?id=${spreadMerchant.id}">
				推广商${not empty spreadMerchant.id?'修改':'添加'}
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="spreadMerchant"
		action="${ctx}/merc/spreadMerchantBankInfo/spreadMerchantFrom"
		method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<form:input path="id" type="hidden" id="id" />
		<div class="control-group" id="email">
			<label class="control-label">Email:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50"
					class="email required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">推广商名称:</label>
			<div class="controls">
				<form:input path="merchantName" htmlEscape="false" maxlength="50"
					class="required" id="merchantName" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">营业执照号或统一社会信用代码:</label>
			<div class="controls">
				<form:input path="licenceNo" htmlEscape="false" maxlength="50"
				class="required licenceNo"	 />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经营期限至:</label>
			<div class="controls">
				<input id="licenceNoDate" name="licenceNoDate" type="text"
					readonly="readonly" maxlength="20"
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
					class="required organizeNo"/>
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">税务登记证税字号:</label>
			<div class="controls">
				<form:input path="taxId" htmlEscape="false" maxlength="50"
				class="required taxId"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证代码:</label>
			<div class="controls">
				<form:input path="organizeTrustNo" htmlEscape="false" maxlength="50"
				class="required organizeTrustNo"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">机构信用代码证有效期至:</label>
			<div class="controls">
				<input id="organizeTrustNoDate" name="organizeTrustNoDate"
					type="text" readonly="readonly" maxlength="20"
					class="input-mini Wdate required"
					value="<fmt:formatDate value="${spreadMerchant.organizeTrustNoDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户许可证核准号:</label>
			<div class="controls">
				<form:input path="accountLicenceNo" htmlEscape="false"
				 class="required accountLicenceNo"	maxlength="50" />
				 <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">销售人员:</label>
			<div class="controls">
				<form:input path="salesMan" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">注册资本:</label>
			<div class="controls">
				<form:input path="capitalAmount" htmlEscape="false" maxlength="50"
					class="capitalAmount"/><span class="help-inline">单位：万元</span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">成立日期:</label>
			<div class="controls">
				<input id="foundedDate" name="foundedDate" type="text"
					readonly="readonly" maxlength="20"
					class="input-mini Wdate"
					value="<fmt:formatDate value="${spreadMerchant.foundedDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人:</label>
			<div class="controls">
				<form:input path="legalRepreName" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">手机号码:</label>
			<div class="controls">
				<form:input path="legalRepreMobile" htmlEscape="false"
					maxlength="50" class="required iphone" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">法定代表人身份证号码:</label>
			<div class="controls">
				<form:input path="legalRepreIdNo" htmlEscape="false" maxlength="50"
					class="required card" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">身份证有效期至:</label>
			<div class="controls">
				<input id="legalRepreIdDate" name="legalRepreIdDate" type="text"
					readonly="readonly" maxlength="20"
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
					maxlength="200" class="input-xlarge" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">联系地址:</label>
			<div class="controls">
				<form:textarea path="linkAddr" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge required ads" />
					<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">经办人:</label>
			<div class="controls">
				<form:input path="operator" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<form:input path="operatorTel" htmlEscape="false" maxlength="50"
					class="required simplePhone" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:select path="spreadMerchantType" class="input-large required">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('SPREADMERCHANT_TYPE')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> 

		<div class="control-group">
			<label class="control-label">经营范围:</label>
			<div class="controls">
				<form:textarea path="dealRange" htmlEscape="false" rows="3"
					maxlength="200" class="input-xlarge" />
			</div>
		</div>

				<div class="control-group">
				<c:if test="${empty spreadMerchant.id}">
			<label class="control-label">证件材料:</label>			
			     <div id="div-0" class="controls">
              		<input type="file" class="inputStyle required" name="files" id="uploadUrl" /> 
              		<input id="add" type="button" value=" + "/> 
              		<input type="button" value=" × " onclick="delinput(this)"/><label>支持zip,pdf,png格式</label> 
              	</div>
              	<div id="div-0-null"></div>
              	
              	 <div id="div-0" class="controls">
              	<table>
                  <tr>
                 	<td></td>
                 	<td id="uploadFile" style="width:600px"></td>
                 	<td></td>
                 </tr>
                 </table>
                 	</div>
                 	</c:if>
              	<c:if test="${not empty spreadMerchant.id}">
              	
			<label class="control-label">证件材料:</label>			
			     <div id="div-0" class="controls">
              		<input type="file" class="inputStyle" name="files" id="uploadUrl" /> 
              		<input id="add" type="button" value=" + "/> 
              		<input type="button" value=" × " onclick="delinput(this)"/><label>支持zip,pdf,png格式</label> 
              	</div>
              	<div id="div-0-null"></div>
              	
              	 <div id="div-0" class="controls">
              	<table>
                  <tr>
                 	<td></td>
                 	<td id="uploadFile" style="width:600px"></td>
                 	<td></td>
                 </tr>
                 </table>
                 	</div>
              	</c:if>
			
		</div>


		<div class="control-group">
			<label class="control-label">账户名称:</label>
			<div class="controls">
				<form:input path="accountName" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">账户别名:</label>
			<div class="controls">
				<form:input path="accountAlias" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">开户行省份:</label>
			<div class="controls">


				<form:select path="provSeq" class="input-large required"
					id="province">
					<form:option value="" label="请选择省" />
					<form:options items="${allProvince}" itemLabel="cityName"
						itemValue="cityId" htmlEscape="false" />
				</form:select>

<%-- 					<select id="province" name="provSeq">
					<option>请选择省</option>
					<c:forEach items="${AllProvince}" var="p">
						<option value="${p.cityId}">${p.cityName}</option>
					</c:forEach>
				</select>  --%>
				<span class="help-inline"><font color="red">*</font> </span>
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

							<form:select path="citySeq" class="input-large required"  name="citySeq" id="citySeq">
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
				<form:select path="bankSeq" class="input-large required">
					<form:option value="" label="请选择" />
					<form:options items="${allBank}" itemLabel="bankName"
						itemValue="Id" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">开户行名称:</label>
			<div class="controls">
				<form:input path="openAccountBankName" htmlEscape="false"
					maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">银行账号:</label>
			<div class="controls">
				<form:input path="cardNo" htmlEscape="false" maxlength="50"
					class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">卡类型:</label>
			<div class="controls">
				<form:select path="cardType" class="input-large required">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('CRAD_TYPE')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>


		<c:if test="${not empty spreadMerchant.id}">

			<div class="control-group">
				<label class="control-label">备注:</label>
				<div class="controls">
					<form:textarea path="remark" htmlEscape="false" rows="3"
						maxlength="200" class="input-xlarge required" />
							<span class="help-inline"><font color="red">*</font> </span>
				</div>
				
			</div>

			<div class="user-detail">
				<p class="">修改历史记录</p>


				<table id="contentTable"
					class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>操作用户</th>
							<th>操作时间</th>
							<th>备注</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${spreadmerchantHis}" var="m">
							<tr>
								<td>${m.operator}</td>
								<td><fmt:formatDate value="${m.operationtime}"
										pattern="yyyy-MM-dd" /></td>
								<td>${m.remark}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table> 

			</div>
		</c:if>


		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="保 存" />&nbsp; <input id="btnCancel" class="btn"
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
						
						
	var oId = 1;
	$("#add").click(function(){
		if(oId>=20){
			alert("最多只能上传20个文件！");
		}else{
			$("#div-0").clone(true).attr("id","div-0"+oId).appendTo("#div-0-null").show().find("input").eq(0).attr("name","files");
	
		}
		oId += 1;
	})

	function delinput(obj){
		var a = obj.parentNode.id;
		if(a == "div-0"){
			alert("此初始上传文件文本框不可删除！");
		}else{
			obj.parentNode.remove(obj);
		}
	}
	</script>
</body>
</html>