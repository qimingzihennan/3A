<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<style type="text/css">
tr{height:40px;}
.easyui-combobox{width:175px;}
.ui-button{background:#3cae56;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.ui-buttons{background:#8E8E8E;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.button{color:#ffffff;font-size:14px;}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">
	<div id="dlg"  align="center" style="height:470px;padding-top:50px">
			<form id="fm" method="post">
				<input type='hidden' name='id' id="id" value='${personal.id }' />
				<table border="0">
				
					<tr>	
						<td>
							<label>用户姓名:</label>
						</td>
						<td>
							${personal.customerName }
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户登录名:</label>
						</td>
						<td>
							${register.loginCode }
						</td>
					</tr>
					<tr>	
						<td>
							<label>移动电话:</label>
						</td>
						<td>
							${personal.mobile}
						</td>
					</tr>
					<tr>	
						<td>
							<label>邮箱地址:</label>
						</td>
						<td>
							${personal.email}
						</td>
					</tr>
		
					<tr>	
						<td>
							<label>身份证号:</label>
						</td>
						<td>
							${personal.idCard}
						</td>
					</tr>
					
					<tr>	
						<td>
							<label>地址:</label>
						</td>
						<td>
							${personal.address}
						</td>
					</tr>
					
			<tr>
			    <td class="describe"><div style="width:150px;display:block">本人照片:</div></td>
			    <td colspan="2"><div style="width:400px;">
			    	<div id="imgdiv1" style="float:left;width:200px;display:block">
			    	<a id="imga1" class="example-image-link" data-lightbox="example-1"
			    	href="<%=path%>/photofile/download/${file0.id}">
			    	<img id="imgShow1" width="100" height="100" src="<%=path%>/photofile/download/${file0.id}" />
		    		</a></div>
			    </div></td>
		    </tr>
		    <tr>
			    <td class="describe"><div style="width:150px;display:block">身份证正面:</div></td>
			    <td colspan="2"><div style="width:400px;">
			    	 <div id="imgdiv2" style="float:left;width:200px;display:block">
			    	 <a id="imga2" class="example-image-link" data-lightbox="example-1"
			    	 href="<%=path%>/photofile/download/${file1.id}">
			    	 <img id="imgShow2" width="100" height="100" src="<%=path%>/photofile/download/${file1.id}" />
		    		 </a></div>
			    </div></td>
		    </tr>
		    <tr>
			    <td class="describe"><div style="width:150px;display:block">身份证反面:</div></td>
			    <td colspan="2"><div style="width:400px;">
			    	 <div id="imgdiv3" style="float:left;width:200px;display:block">
			    	 <a id="imga3" class="example-image-link" data-lightbox="example-1"
			    	 href="<%=path%>/photofile/download/${file2.id}">
			    	 <img id="imgShow3" width="100" height="100" src="<%=path%>/photofile/download/${file2.id}" />
		    		 </a></div>
			    </div></td>
		    </tr>
				</table>
			</form>
			
			<div id="dlg-buttons">
				
			<button id="approve_button" onclick="saveCrmUser()" class="ui-button" >
						<span class="button">
							<c:choose>
									<c:when test="${personal.status == 1}">已通过认证</c:when>
									<c:otherwise>认证通过</c:otherwise>
								</c:choose>
						</span></button>
				<button id="reject_button" onclick="savesCrmUser()" class="ui-button" >
						<span class="button">
							<c:choose>
									<c:when test="${personal.status == 2}">已驳回认证</c:when>
									<c:otherwise>认证驳回</c:otherwise>
								</c:choose>
						</span></button>			
						
			</div>
	</div>
</body>
<script type="text/javascript">
function firmApprove() {  
    //利用对话框返回的值 （true 或者 false）  
    if (confirm("确定认证通过吗？")) {  
        return true;  
    }  
    else {  
       return false;  
    }  

} 
window.onload=function(){ 
	//要初始化的东西 
	
	var enterpriseStatus = "${personal.status}";
	if(enterpriseStatus=="1"){
		$("#approve_button").attr({
			"disabled" : "true"
			});
		$("#reject_button").attr({
			"disabled" : "true"
			});
	}else if(enterpriseStatus=="2"){
		$("#reject_button").attr({
			"disabled" : "true"
			});
	}
	} 
function firmReject() {  
    //利用对话框返回的值 （true 或者 false）  
    if (confirm("确定认证驳回吗？")) {  
        return true;  
    }  
    else {  
       return false;  
    }  

}
function toAddtype(){
	$('#fm').form('clear');
	$('#dlg').dialog('open').dialog('setTitle','后端用户管理');
}
	function saveCrmUser(){
		
		if(firmApprove()){
		var operateUrl = "";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#custom_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/personal/save.do";
		}else{
			var id = "${personal.id}";
			operateUrl = "<%=path%>/personal/changeStatus.do?id=" + id;
		}
		console.log(operateUrl);
		$('#fm').form('submit',{
			url:operateUrl ,
			onSubmit:function(param){
				return $(this).form('validate');
			},
			type: "POST",
			beforeSend: ajaxLoading,
			error: function(request) {
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				var obj = eval("("+data+")");
				alert(obj.msg);
				$('#fm').form('clear');
				jq('#custom_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				if (!rows || rows.length != 1) {
					jtop('#tabs',window.top.document).tabs('close',"查看个人详细信息");
				}else{
					jtop('#tabs',window.top.document).tabs('close',"查看个人详细信息");
				}
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
		
		}
		
	}
	function savesCrmUser(){
		if(firmReject()){
		var operateUrl = "";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#custom_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/personal/save.do";
		}else{
			var id = "${personal.id}";
			operateUrl = "<%=path%>/personal/changesStatus.do?id=" + id;
		}
		console.log(operateUrl);
		$('#fm').form('submit',{
			url:operateUrl ,
			onSubmit:function(param){
				return $(this).form('validate');
			},
			type: "POST",
			beforeSend: ajaxLoading,
			error: function(request) {
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				var obj = eval("("+data+")");
				alert(obj.msg);
				$('#fm').form('clear');
				jq('#custom_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				if (!rows || rows.length != 1) {
					jtop('#tabs',window.top.document).tabs('close',"查看个人详细信息");
				}else{
					jtop('#tabs',window.top.document).tabs('close',"查看个人详细信息");
				}
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
		}
	}
</script>
</html>