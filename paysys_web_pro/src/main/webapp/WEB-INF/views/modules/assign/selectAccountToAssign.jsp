<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分配推广商</title>
<meta name="decorator" content="blank" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
	
		var userTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
			userTree	 = $.fn.zTree.init($("#userTree"), setting, userNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
		});
		
		
		var userNodesa;
		
		function findIt()
		{
			var name=$("#searchstr").val();
			$.ajax({
			type:'POST',
			url:"${ctx}/merc/assign/accounttoassignAjax",
			data:'merchantName='+name,
			dataType:'json',
			success: function(date){
				userNodes = [];
				$.each(date, function(index, obj) {

					userNodes[index]=
                               {id:obj.ID,
                               pId:"0", 
                             name:obj.NAME}   
					
									
				});

				$.fn.zTree.init($("#userTree"), setting, userNodes);
			}
		})
			
		}

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: false}},
				callback: {onClick: treeOnClick}};
		
		var userNodes=[
	            <c:forEach items="${allUserList}" var="user">
	            {id:"${user.ID}",
	             pId:"0", 
	             name:"${user.NAME}"},
	            </c:forEach>];
	

		
	var pre_selectedNodes =[
   		        <c:forEach items="${userList}" var="user">
   		        {id:"${user.ID}",
   		         pId:"0",
   		         name:"<font color='red' style='font-weight:bold;'>${user.NAME}</font>"},
   		        </c:forEach>];
		
		var selectedNodes =[
		        <c:forEach items="${userList}" var="user">
		        {id:"${user.ID}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${user.NAME}</font>"},
		        </c:forEach>];
		
		var pre_ids = "${selectIds}".split(",");
		var ids = "${selectIds}".split(",");
				
		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			/* alert(treeNode.id + " | " + ids); */
			if("userTree"==treeId){
				if($.inArray(String(treeNode.id), ids)<0){
					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
				}
			};
			if("selectedTree"==treeId){
				if($.inArray(String(treeNode.id), pre_ids)<0){
					
					selectedTree.removeNode(treeNode);
					ids.splice($.inArray(String(treeNode.id), ids), 1);
				}else{
					selectedTree.removeNode(treeNode);
					/* top.$.jBox.tip("推广商原有商户不能清除！", 'info'); */
					ids.splice($.inArray(String(treeNode.id), ids), 1);
				}
			}
		};
			function clearAssign(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
					var tips="";
					if(pre_ids.sort().toString() == ids.sort().toString()){
						tips = "未给推广商分配新商户！";
					}else{
						tips = "已选商户清除成功！";
					}
					ids=pre_ids.slice(0);
					selectedNodes=pre_selectedNodes;
					$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			    	top.$.jBox.tip(tips, 'info');
			    } else if (v == 'cancel'){
			    	// 取消
			    	top.$.jBox.tip("取消清除操作！", 'info');
			    }
			    return true;
			};
			tips="确定清除推广商下的已选商户？";
			top.$.jBox.confirm(tips, "清除确认", submit);
		}; 
		

	</script>

</head>
<body>
	<input type="text" id="searchstr" name="searchstr" class="textbox"
		size="20">
	<input type="button" value="查找" onclick="findIt();" class="sbttn">
	<div id="assignRole" class="row-fluid span12">
		<div class="span3">
			<p>待选商户：</p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="span3"
			style="padding-left: 16px; border-left: 1px solid #A8A8A8;">
			<p>已选商户：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
