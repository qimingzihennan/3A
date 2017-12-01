<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<style type="text/css">
tr {
	height: 40px;
}

.easyui-combobox {
	width: 175px;
}

.ui-button {
	background: #3cae56;
	border: 0;
	width: 88px;
	height: 38px;
	border-radius: 4px;
	margin-top: 15px;
	margin-left: 60px;
}

.button {
	color: #ffffff;
	font-size: 14px;
}
</style>
</head>
<body class="easyui-layout" style="overflow: auto;">
	<div id="dlg" align="center" style="height: 670px; padding-top: 50px;">
		<form id="fm" method="post">
			<input type='hidden' name='enterpriseId' id="enterpriseId" value='' />
			<table border="0">
				<tr>
					<td><label>企业名称:</label></td>
					<td><span>${enterprise.enterpriseName }</span></td>
				</tr>

				<tr>
					<td><label>营业执照登记号:</label></td>
					<td><span>${enterprise.businessNumber }</span></td>
				</tr>
				<tr>
					<td><label>组织机构代码证号:</label></td>
					<td><span>${enterprise.orgCertificate }</span></td>
				</tr>
				<tr>
					<td><label>税务登记证号:</label></td>
					<td><span>${enterprise.trCertificate }</span></td>
				</tr>
				<tr>
					<td><label>统一社会信用代码:</label></td>
					<td><span>${enterprise.uscCode }</span></td>
				</tr>
				<tr>
					<td><label>企业地址:</label></td>
					<td><span>${enterprise.enterpriseAddress }</span></td>
				</tr>
				<tr>
					<td><label>法人姓名:</label></td>
					<td><span>${enterprise.adminName }</span></td>
				</tr>

				<tr>
					<td><label>法人身份证号:</label></td>
					<td><span>${enterprise.adminIdCard }</span></td>
				</tr>
				<tr>
					<td><label>代理人姓名:</label></td>
					<td><span>${enterprise.agentName }</span></td>
				</tr>

				<tr>
					<td><label>代理人身份证号:</label></td>
					<td><span>${enterprise.agentIdCard }</span></td>
				</tr>
				<tr>
					<td><label>代理人手机号:</label></td>
					<td><span>${enterprise.agentMobile }</span></td>
				</tr>
				<tr>
					<td><label>PIN码:</label></td>
					<td><span>${PS.PIN }</span></td>
				</tr>
				<tr>
					<td><label>SD码:</label></td>
					<td><span>${PS.SD }</span></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 150px; display: block">企业营业执照:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv1"
								style="float: left; width: 200px; display: block">
								<a id="imga1" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file3.id}"> <img
									id="imgShow3" width="150" height="100"
									src="<%=path%>/photofile/download/${file3.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 180px; display: block">组织机构代码证:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv2"
								style="float: left; width: 200px; display: block">
								<a id="imga2" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file4.id}"> <img
									id="imgShow4" width="150" height="100"
									src="<%=path%>/photofile/download/${file4.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 180px; display: block">税务登记证:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv2"
								style="float: left; width: 200px; display: block">
								<a id="imga2" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file7.id}"> <img
									id="imgShow7" width="150" height="100"
									src="<%=path%>/photofile/download/${file7.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 180px; display: block">统一社会信用代码图片:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv2"
								style="float: left; width: 200px; display: block">
								<a id="imga2" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file8.id}"> <img
									id="imgShow8" width="150" height="100"
									src="<%=path%>/photofile/download/${file8.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 180px; display: block">法人手持证件照:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv3"
								style="float: left; width: 200px; display: block">
								<a id="imga3" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file5.id}"> <img
									id="imgShow5" width="150" height="100"
									src="<%=path%>/photofile/download/${file5.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td class="describe"><div style="width: 180px; display: block">代理人手持证件照:</div></td>
					<td colspan="2"><div style="width: 400px;">
							<div id="imgdiv4"
								style="float: left; width: 200px; display: block">
								<a id="imga4" class="example-image-link"
									data-lightbox="example-1"
									href="<%=path%>/photofile/download/${file6.id}"> <img
									id="imgShow6" width="150" height="100"
									src="<%=path%>/photofile/download/${file6.id}" />
								</a>
							</div>
						</div></td>
				</tr>
				<tr>
					<td><label>企业认证状态:</label></td>
					<td><span> <c:choose>
								<c:when test="${enterprise.status == 3}">待审核</c:when>
								<c:when test="${enterprise.status == 1}">认证成功</c:when>
								<c:when test="${enterprise.status == 2}">认证被驳回</c:when>
								<c:otherwise>等待认证</c:otherwise>
							</c:choose>
					</span></td>
				</tr>

			</table>
		</form>
		<div id="dlg-buttons">
			<button id="approve_button" onclick="approve()" class="ui-button">
				<span class="button"> <c:choose>
						<c:when test="${enterprise.status == 1}">已通过认证</c:when>
						<c:otherwise>认证通过</c:otherwise>
					</c:choose>
				</span>
			</button>
			<button id="reject_button" onclick="reject()" class="ui-button">
				<span class="button"> <c:choose>
						<c:when test="${enterprise.status == 2}">已驳回认证</c:when>
						<c:otherwise>认证驳回</c:otherwise>
					</c:choose>
				</span>
			</button>

		</div>
	</div>
</body>
<script type="text/javascript">



window.onload=function(){ 
	//要初始化的东西 
	
	var enterpriseStatus = "${enterprise.status}";
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
	
//弹出一个询问框，有确定和取消按钮  
function firmApprove() {  
    //利用对话框返回的值 （true 或者 false）  
    if (confirm("确定该企业认证通过吗？")) {  
        return true;  
    }  
    else {  
       return false;  
    }  

} 

function firmReject() {  
    //利用对话框返回的值 （true 或者 false）  
    if (confirm("确定该企业认证驳回吗？")) {  
        return true;  
    }  
    else {  
       return false;  
    }  

}

	
	function approve(){
		if(firmApprove()){
			var operateUrl = "";
			//判断是否选择行
			var iframeId = "${iframeId}";
			var jq = window.parent.frames[iframeId].jQuery;
			var rows = jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid('getSelections');
			//判断是否选择行
			
				var enterpriseId = "${enterprise.enterpriseId}";
				operateUrl = "<%=path%>/enterprise/approve.do?enterpriseId=" + enterpriseId;
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
					jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid("reload");
					var jtop = top.jQuery;
					
						jtop('#tabs',window.top.document).tabs('close',"查看/审核企业信息");
					
				},
				complete:function() {
					//关闭覆盖层 loading
					$(".datagrid-mask").remove();
					$(".datagrid-mask-msg").remove();
				}
			});
		}	
	}
	
	function reject(){
		if(firmReject()){
			var operateUrl = "";
			//判断是否选择行
			var iframeId = "${iframeId}";
			var jq = window.parent.frames[iframeId].jQuery;
			var rows = jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid('getSelections');
			//判断是否选择行
			
				var enterpriseId = "${enterprise.enterpriseId}";
				operateUrl = "<%=path%>/enterprise/reject.do?enterpriseId="
					+ enterpriseId;
			console.log(operateUrl);
			$('#fm').form(
					'submit',
					{
						url : operateUrl,
						onSubmit : function(param) {
							return $(this).form('validate');
						},
						type : "POST",
						beforeSend : ajaxLoading,
						error : function(request) {
							$.messager.alert('提示', request.responseJSON.reason,
									'error');
						},
						success : function(data) {
							var obj = eval("(" + data + ")");
							alert(obj.msg);
							$('#fm').form('clear');
							jq('#enterprise_list',
									window.parent.frames[iframeId].document)
									.datagrid("reload");
							var jtop = top.jQuery;

							jtop('#tabs', window.top.document).tabs('close',
									"查看/审核企业信息");

						},
						complete : function() {
							//关闭覆盖层 loading
							$(".datagrid-mask").remove();
							$(".datagrid-mask-msg").remove();
						}
					});
		}
	}
</script>
</html>