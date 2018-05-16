<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>后端角色管理</title>
<script type="text/javascript" src="<%=path %>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<span>角色名:</span><input class="easyui-textbox" type="text" id="searchName"
					name="name" value="" size=10 width="100px"/> 

				 <a href="#" class="easyui-linkbutton"
					onclick="searchFunc()" iconCls="icon-search">查询</a> 
				<a href="#"
					class="easyui-linkbutton" onclick="toAddtype()" iconCls="icon-add"
					plain="true">新增</a> 
				<a href="#" class="easyui-linkbutton"
					onclick="toModify()" iconCls="icon-edit" plain="true">修改</a> 
				<a
					href="#" class="easyui-linkbutton" onclick="doDelete()"
					iconCls="icon-remove" plain="true">删除</a>
				<a href="#" class="easyui-linkbutton" onclick="permissioRole()">分配功能</a>
				
			</div>
		</div>

		
		<table id="roles_list" url="<%=path%>/roles/query.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<!-- singleSelect: true,  单选 -->
			<thead>
				<tr>
					<th data-options="field:'roleId',checkbox:true"></th>
					<th data-options="field:'name',width:100">角色名称</th>
					<th data-options="field:'description',width:200">角色描述</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:400px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
		<div class="ftitle">后台角色管理</div>
			<form id="fm" method="post">
				<input type='hidden' name='roleId' id="roleId" value='' />
				<table border="0">
					<tr>	
						<td>
							<div class="fitem">
								<label>角色名称:</label>
								<input class="easyui-validatebox" id="edit_name" name="name" required="true"
									missingMessage="角色名称必须填写"></input>
							</div>
						</td>
					</tr>
					<tr>	
						<td>
							<div class="fitem">
								<label>角色描述:</label>
								<textarea class="easyui-textbox" missingMessage="输入不能超过200个字符！" 
									 data-options="validType:['length[0,200]']" 
									  id="edit_description" name="description" style="width:200px;height:100px"></textarea>
							</div>
						</td>
					</tr>
					<tr>	
						<td>
							<div id="dlg-buttons">
								<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRole()" id="save-btn" style="width:90px">保存</a>
					<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRepair_()" style="width:90px">Saves</a> -->
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
							</div>
						</td>
					</tr>
				</table>
				
			</form>
	</div>
	
		
		<div id="rolesdlg" class="easyui-dialog" style="width:400px;height:470px;padding:10px 20px" closed="true" modal="true" >
			<input id="selectRoleId" name="selectRoleId"  type="hidden">
			<div class="ftitle">角色权限分配</div>
			<ul>
				<div id="resourcesList">
				
				</div>
			</ul>
		</div>
		
</body>
<script type="text/javascript">
$(function(){
	// 树
	 $('#roles_list').datagrid({
		url:'<%=path%>/roles/query.do',
		onLoadError:function(arguments){
			errorHandler(arguments);
		}
	});
	//设置分页控件 
	var p = $('#roles_list').datagrid('getPager'); 
	$(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
		beforePageText: '第',//页数文本框前显示的汉字 
		afterPageText: '页	共 {pages} 页', 
		displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
		onBeforeRefresh:function(){
			$(this).pagination('loading');
			$(this).pagination('loaded');
		}
	});
});

function searchFunc() {
	$('#roles_list').datagrid('load',{  
		name:$('#searchName').val()
	});
}
function toAddtype(){
	addRoleTab("添加角色", "/roles/addRoles.do");
}
function addRoleTab(subtitle, url) {
	var jq = top.jQuery;
	var bashpath=getRootPath_web();
	if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
		jq('#tabs',window.top.document).tabs('add', {
			title : subtitle,
			content : createFrame2(bashpath+url),
			closable : true,
			width : jq('#mainPanle').width() - 10,
			height : jq('#mainPanle').height() - 26
		});
	} else {
		jq('#tabs',window.top.document).tabs('select', subtitle);
	}
	tabClose();
}
function createFrame2(url) {
	var iframeId = "${iframeId}";
	url = url + "?id=" + iframeId;
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;" ></iframe>';
	return s;
}
function saveRole(){
	var operateUrl = "";
	//判断是否选择行
	var rows = $('#roles_list').datagrid('getSelections');
	//判断是否选择行
	if (!rows || rows.length != 1) {
		operateUrl = "<%=path%>/roles/save.do";
	}else{
		operateUrl = "<%=path%>/roles/modify.do";
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
			$.messager.alert('提示', '保存成功', 'info');
			$('#fm').form('clear');
		   	$('#roles_list').datagrid("reload");
			$('#dlg').dialog('close'); 
		},
		complete:function() {
			//关闭覆盖层 loading
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
}
function editRoleTab(subtitle, url,roleId) {
	var jq = top.jQuery;
	var bashpath=getRootPath_web();
	if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
		jq('#tabs',window.top.document).tabs('add', {
			title : subtitle,
			content : createFrame3(bashpath+url,roleId),
			closable : true,
			width : jq('#mainPanle').width() - 10,
			height : jq('#mainPanle').height() - 26
		});
	} else {
		$.messager.alert('提示', "修改角色选项卡已存在，请关闭后再选择", 'info');
		//jq('#tabs',window.top.document).tabs('select', subtitle);
	}
	tabClose();
}
function createFrame3(url,roleId) {
	var iframeId = "${iframeId}";
	url = url + "?id=" + iframeId + "&roleId=" + roleId;
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;" ></iframe>';
	return s;
}
function toModify(){
	var rows = $('#roles_list').datagrid('getSelections');
	var parm = "";
	
	//判断是否选择行
	if (!rows || rows.length != 1) {
		$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
		return;
	}
	editRoleTab("修改角色", "/roles/toModify.do",rows[0].roleId);
}
	function doDelete(){
		
		var rows = $('#roles_list').datagrid('getSelections');
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要删除的对象!', 'info');
			return;
		}
		
		$.messager.confirm('提示', '确认要删除所选数据？', function(r){
			if (r){
			$.post(_basePath+"/roles/delete.do", { "roleId":rows[0].roleId },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#roles_list').datagrid("reload");
				});
			}
		});
	}

	function permissioRole(){
		var rows = $('#roles_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		$.ajax({
			type: "POST",
			dataType:"JSON",
			url:"<%=path%>/roles/resources/permissioRole.do",
			data:{roleId:rows[0].roleId,},
			beforeSend:ajaxLoading,
			async: true,
			error: function(request) {
				//alert("Connection error");
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				$("#resourcesList").empty();;
			   	//打开编缉框
				$('#rolesdlg').dialog('open').dialog('setTitle','角色权限分配');
				
				var obj = eval(data.resouces);
				var roleId = data.roleId;
				$("#selectRoleId").val(roleId);
				
				for (var j = 0; j < obj.length; j++) {
					html	= "<li><label><input type='checkbox' name='"+obj[j].id+"' id='"+obj[j].id+
							"'" + (obj[j].checked=='checked'?"checked='checked'":"")+
							" onclick='updateRoleResource(\""+obj[j].id+"\",\""+obj[j].url+"\",\""+roleId+"\");'\>"
							+obj[j].showName+"</label></li>";
				
					$(html).appendTo("#resourcesList");
				}
			},
			complete:function(){
				//关闭覆盖层 loading
			   	$(".datagrid-mask").remove(); 
			   	$(".datagrid-mask-msg").remove();  
			}
		});
	}
	
	function updateRoleResource(name, url, roleId) {
		var value;
		var checkName = "#"+name;
		var val = $(checkName).val();
		var result = $(checkName).is(':checked');
		//alert(result);
		if(!result){
			value = 0;
			$(checkName).val('')
		}else{
			value = 1;
			$(checkName).val('checked')
		}
		
		console.log(checkName);
		$
				.ajax({
					type : "POST",
					data : {
						url : url,
						roleId : roleId,
						value : value
					},
					url : "${pageContext.servletContext.contextPath }/roles/resources/updateRolesResources.do",
					dataType : 'json'
				});
	};
</script>