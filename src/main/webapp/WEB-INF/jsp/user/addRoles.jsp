<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>后端角色管理</title>
<style type="text/css">
.fitem{margin-top:15px;}
.ui-button{background:#3cae56;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.button{color:#ffffff;font-size:14px;}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">
	<div id="dlg" style="height:400px;margin-top:40px" align="center">
		<form id="fm" method="post">
			<input type='hidden' name='roleId' id="roleId" value='' />
			<table border="0">
				<tr>	
					<td>
						<div class="fitem">
							<label>角色名称:</label>
							<input class="easyui-validatebox" id="edit_name" name="name" required="true"
								missingMessage="角色名称必须填写" value="${roles.name}"></input>
						</div>
					</td>
				</tr>
				<tr>	
					<td>
						<div class="fitem">
							<label>角色描述:</label>
							<textarea class="easyui-textbox" missingMessage="输入不能超过200个字符！" 
								 data-options="validType:['length[0,200]']" 
								  id="edit_description" name="description" style="width:200px;height:100px">${roles.description}</textarea>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons">
			<button onclick="saveRole()" class="ui-button">
 				<span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span></button>
		</div>
	</div>
</body>

<script type="text/javascript">
function saveRole(){
	var operateUrl = "";
	var iframeId = "${iframeId}";
	var jq = window.parent.frames[iframeId].jQuery;
	var rows = jq('#roles_list',window.parent.frames[iframeId].document).datagrid('getSelections');
	//判断是否选择行
	if (!rows || rows.length != 1) {
		operateUrl = "<%=path%>/roles/save.do";
	}else{
		var roleId = "${roles.roleId}";
		if(roleId == null || roleId == ""){
			operateUrl = "<%=path%>/roles/save.do";
		}else{
			operateUrl = "<%=path%>/roles/modify.do?roleId=" + roleId;
		}
		
	}
	
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
			//$.messager.alert('提示', '保存成功', 'info');
			var obj = eval("("+data+")");
			alert(obj.msg);
			$('#fm').form('clear');
			jq('#roles_list',window.parent.frames[iframeId].document).datagrid("reload");
			var jtop = top.jQuery;
			if (!rows || rows.length != 1) {
				jtop('#tabs',window.top.document).tabs('close',"添加角色");
			}else{
				if(roleId == null || roleId == ""){
					jtop('#tabs',window.top.document).tabs('close',"添加角色");
				}else{
					jtop('#tabs',window.top.document).tabs('close',"修改角色");
				}
				
			}
		},	
		complete:function() {
			//关闭覆盖层 loading
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
	
}
</script>