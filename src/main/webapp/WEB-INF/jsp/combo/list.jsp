<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=path %>/js/west.js"></script>
<title>套餐管理</title>
</head>
<body class="easyui-layout">
<div data-options="region:'center'" style="background: #eee; overflow-y:hidden">
		<table id="datas_list" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#tb',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<!-- singleSelect: true,  单选 -->
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'name',width:100,align:'center'">套餐名称</th>
					<th data-options="field:'number',width:100,align:'center'">次数</th>
					<th data-options="field:'price',width:100,align:'center'">价格</th>
					<th data-options="field:'coType',width:100,align:'center',formatter:userType">用户类型</th>
					<th data-options="field:'bkey',width:100,align:'center',formatter:bType">业务模块</th>
					<th data-options="field:'days',width:100,align:'center'">天数</th>
					<th data-options="field:'space',width:100,align:'center'">存储空间(G)</th>
					<th data-options="field:'status',width:100,align:'center',formatter:statusNew">状态</th>
					<th data-options="field:'content',width:100,align:'center',hidden:true">内容</th>					
				</tr>
			</thead>
		</table>
		<div  id="tb" style="height:auto;display: none;">
			<span>套餐名称:</span><input class="easyui-textbox" id="searchName" name="searchName" style="width:180px" value="" />
			<a href="#" class="easyui-linkbutton" onclick="findData()" iconCls="icon-search">查询</a> </br>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="ediCrmUser()">修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true" onclick="ena()">启用/禁用</a>
		</div>

<div id="dlg" class="easyui-dialog" style="width:550px;height:400px;padding:10px 20px;display: none;" closed="true" modal="true" buttons="#dlg-buttons">
	<form action="" id="fm" method="post">
		<input type="hidden" name="id" id="id">
		<input type="hidden" name="status" id="status">
		<table border="0" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td width="60px;">套餐名称：</td><td><input class="easyui-validatebox" data-options="required:true,validType:'nameLength[1,25]'"   id="name" name="name" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">次数：</td><td><input class="easyui-validatebox" data-options="required:true,validType:['digits','numberMax']" id="number" name="number" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">价格：</td><td><input class="easyui-validatebox" data-options="required:true,validType:['number','priceMax']" id="price" name="price" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">类型：</td><td><input type="radio"  name="type" value="1">个人<input type="radio" name="type" value="2" >企业</td>
			</tr>
			<tr>
				<td width="60px;">天数：</td><td><input class="easyui-validatebox" data-options="required:true,validType:['digits','numberMax']"  id="days" name="days" style="width: 300px;">
												<div><font color="red">*按次收费套餐填0</font></div>
											</td>
			</tr>
			<tr>
				<td width="60px;">套餐内容：</td><td><input class="easyui-textbox" name="content" data-options="multiline:true" value="" style="width:300px;height:100px"></td>
			</tr>															
		</table>
	</form>
</div>
<div id="dlg-buttons" style="display: none;">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveCrmUser()" id="save-btn" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateCrmUser()" id="update-btn" style="width:90px">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">关闭</a>
</div>		
</div>
</body>
<script type="text/javascript">

$.extend($.fn.validatebox.defaults.rules, {
    number: {
		validator: function(value,param){
			return !isNaN(value);
		},
		message: '输入合法数字.'
    },
    digits: {
		validator: function(value,param){
			return Math.floor(value) == value;
		},
		message: '必须整数.'
    },
    nameLength:{
    	validator : function(value, param) {  
    		var min = param[0];
    		var max = param[1];
    		if(value.length >= min && value.length <= max){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "套餐名称长度，1-25位."
    },
    numberMax:{
    	validator : function(value, param) {  
    		if(value <= 999999999){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "最大值999999999."
    },
    priceMax:{
    	validator : function(value, param) {  
    		var reg = /^[0-9]+(\.[0-9]{2})?$/;
    		return reg.test(value);
    		if(value*100 <= 9999999999){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "最大值99999999.99,只能保留两位小数."    	
    }
});
//判断是修改还是添加
var whatdo = 1;
$(function(){
	//表单
	 $('#datas_list').datagrid({
	        url:'<%=path %>/combo/query.do',	
	        method:'post',
			onLoadError:function(arguments){
				errorHandler(arguments);
			}
		});
});

//打开添加页
function append(){
	addRoleTab("新增套餐", "/combo/toAdd.do");
	/* $('#fm').form('clear');
	$('input[name=type]').get(0).checked = true;
	$("#save-btn").show();
	$("#update-btn").hide();
	$('#dlg').dialog('open').dialog('setTitle','新增套餐'); */
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
//添加
function saveCrmUser(){
	var addurl = '<%=path %>/combo/save.do';
	$('#fm').form('submit',{
		url: addurl,
		onSubmit:function(param){
			return $('#fm').form('validate');
		},
		type: "POST",
		beforeSend: ajaxLoading,
		error: function(request) {
			$.messager.alert('提示', request.responseJSON.reason, 'error');
		},
		success: function(data) {
			$.messager.alert('提示', '保存成功', 'info');
			$('#fm').form('clear');
		   	$('#datas_list').datagrid("reload");
			$('#dlg').dialog('close'); 
		},
		complete:function() {
			//关闭覆盖层 loading
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
}
//修改页打开
function ediCrmUser(){
	var row = $("#datas_list").datagrid('getSelections');
	if(row.length < 1){
		$.messager.alert('提示','请选择要修改的数据！！！','warning');
		return;
	}
	if(row.length > 1){
		$.messager.alert('提示','一次只能修改一条数据！！！','warning');
		return;
	}
		var selectedrow = $("#datas_list").datagrid('getSelected');
		editRoleTab("编辑套餐", "/combo/toEdit.do",selectedrow);
    	/* $('#fm').form('load',selectedrow);
    	$("input[name='type'][value="+selectedrow.type+"]").attr("checked",true);
    	$("#update-btn").show();
    	$("#save-btn").hide();
    	$('#dlg').dialog('open').dialog('setTitle','编辑套餐');  */
}
function editRoleTab(subtitle, url,selectedrow) {
	var jq = top.jQuery;
	var bashpath=getRootPath_web();
	if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
		jq('#tabs',window.top.document).tabs('add', {
			title : subtitle,
			content : createFrame3(bashpath+url,selectedrow),
			closable : true,
			width : jq('#mainPanle').width() - 10,
			height : jq('#mainPanle').height() - 26
		});
	} else {
		$.messager.alert('提示', "编辑套餐选项卡已存在，请关闭后再选择", 'info');
	}
	tabClose();
}
function createFrame3(url,selectedrow) {
	var iframeId = "${iframeId}";
	var arrays = new Array();
	arrays[0] = selectedrow.id;
	
	url = url + "?id=" + selectedrow.id +"&iframeId=" + iframeId;
	var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
		+ url + '" style="width:100%;height:100%;" ></iframe>';
	return s;
}
//修改
function updateCrmUser(){
	var addurl = '<%=path %>/combo/modify.do';
	$('#fm').form('submit',{
		url: addurl,
		onSubmit:function(param){
			return $('#fm').form('validate');
		},
		type: "POST",
		beforeSend: ajaxLoading,
		error: function(request) {
			$.messager.alert('提示', request.responseJSON.reason, 'error');			
		},
		success: function(data) {
			$.messager.alert('提示', '保存成功', 'info');
			$('#fm').form('clear');
		   	$('#datas_list').datagrid("reload");
			$('#dlg').dialog('close'); 
		},
		complete:function() {
			//关闭覆盖层 loading
			$(".datagrid-mask").remove();
			$(".datagrid-mask-msg").remove();
		}
	});
}
//删除
function del(){
	var row = $("#datas_list").datagrid('getSelections');
	if(row.length < 1){
		$.messager.alert('提示','请选择要删除的数据！！！','warning');
		return;
	}
	if(row.length > 1){
		$.messager.alert('提示','一次只能修改一条数据！！！','warning');
		return;
	}
	var selectedrow = $("#datas_list").datagrid('getSelected');
	$.messager.confirm('提示', '确认要删除所选数据？', function(r){
		if(r){
			$.ajax({
				 type:'post',
				 url:'<%=path%>/combo/deleStatus.do',
				 data:{id:selectedrow.id,status:selectedrow.status},
			     cache:false,    
			     dataType:'json',    
			     success:function(data) {
		  		 $.messager.alert('提示',data.msg+'！！！','info');
			 	 $('#datas_list').datagrid('reload');        		    	
			     },    
			     error : function() {    
			    	 $.messager.alert('提示','网络异常！！！','error');   
			     }           		 
			 });
		}
	 
	});	
}
//启用禁用
function ena(){
	var row = $("#datas_list").datagrid('getSelections');
	if(row.length < 1){
		$.messager.alert('提示','请选择要修改的数据！！！','warning');
		return;
	}
	if(row.length > 1){
		$.messager.alert('提示','一次只能修改一条数据！！！','warning');
		return;
	}
	var selectedrow = $("#datas_list").datagrid('getSelected');
	if(selectedrow.status == 2){
		$.messager.alert('提示','已删除的套餐不可修改！！！','warning');
		return false;
	}
	 $.ajax({
		 type:'post',
		 url:'<%=path%>/combo/changeStatus.do',
		 data:{id:selectedrow.id,status:selectedrow.status},
	     cache:false,    
	     dataType:'json',    
	     success:function(data) {
  		 $.messager.alert('提示',data.msg+'！！！','info');
	 	 $('#datas_list').datagrid('reload');        		    	
	     },    
	     error : function() {    
	    	 $.messager.alert('提示','网络异常！！！','error');   
	     }           		 
	 });	
}

function see(){
	var row = $("#datas_list").datagrid('getSelections');
	if(row.length < 1){
		$.messager.alert('提示','请选择要查看的数据！！！','warning');
		return;
	}
	if(row.length > 1){
		$.messager.alert('提示','一次只能查看一条数据！！！','warning');
		return;
	}
	var selectedrow = $("#datas_list").datagrid('getSelected');	
	$('#fm').form('load',selectedrow);
	$("input[name='type'][value="+selectedrow.type+"]").attr("checked",true);
	$("#save-btn").hide();
	$("#update-btn").hide();
	$('#dlg').dialog('open').dialog('setTitle','套餐详情'); 	
}
<!--列表方法-->
var userType = function(value, row, index){
	if(value == 2){
		return "企业";
	}else{
		return "个人";
	}
}
var bType = function(value, row, index){
	if(value == 'timestamp'){
		return "时间戳";
	}else if(value == 'preservation'){
		return "电子存证";
	}else if(value == 'contract'){
		return "电子合同";
	}else{
		return value;
	}
}
var statusNew = function(value, row, index){
	if(value == 1){
		return "启用";
	}else if(value == 0){
		return "禁用";
	}else{
		return "已删除";
	}
}
<!--条件查询-->
function findData(){
	$('#datas_list').datagrid('load',{  
		name:$('#searchName').val()
	});
}
</script>
</html>