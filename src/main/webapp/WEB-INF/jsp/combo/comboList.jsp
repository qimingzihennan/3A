<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/lightbox.css">
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/lightbox-plus-jquery.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript" src="<%=path %>/js/west.js"></script>
<title>套餐信息显示</title>
<style type="text/css">
 .describe{text-align:right;}

#ui-upload{ position:relative;width:60px;height:35px;border:1px solid silver; overflow:hidden;} 
#contractFile{ position:absolute;top:0px;right:0px;height:100%;cursor:pointer; opacity:0;filter:alpha(opacity:0);z-index:999;} 
#ui-upload-txt{ position:absolute;top:0px;left:0px;width:100%;height:100%;line-height:35px;text-align:center;} 
#ui-upload-filepath{ position:relative; border:1px solid silver; width:180px; height:35px; overflow:hidden; float:left;} 
#ui-upload-filepathtxt{ position:absolute; top:0px;left:0px; width:100%;height:35px; border:0px; line-height:35px; } 
#enterpriseForm{margin-top:20px;}
#personalForm{margin-top:10px;}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'" style="height:100%; overflow-y:hidden">
		<!-- 工具栏 -->
		<form id="searchForm">
		<div id="toolbar" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				名称: <input class="easyui-textbox" id="realName" name="realName" style="width:180px">
				类型: <select class="easyui-combobox" id="registerType" name="registerType" style="width:70px;">
					<option value="0">全部</option>
					<option value="1">个人</option>
					<option value="2">企业</option>
				</select>
				状态: <select class="easyui-combobox" id="status" name="status" style="width:70px;">
					<option value="0">全部</option>
					<option value="2">未认证</option>
					<option value="3">认证成功</option>
				</select>
				<a href="#" class="easyui-linkbutton" onclick="searchFunc()" iconCls="icon-search">查询</a>
			</div>
		</div>
		</form>
		<!-- 数据列表 -->
		<table id="listdata" class="easyui-datagrid"  toolbar="#toolbar" style="width:auto;"
				data-options="iconCls: 'icon-ok',pagination:'true', 
					rownumbers: true, fitColumns: true,toolbar: '#toolbar',
					url:'<%=path %>/combo/query.do',
					method: 'get', idField: 'registerId',fit:true">
		
			<thead>
				<tr> 
				<th data-options="field:'id',checkbox:true"></th>
				<th data-options="field:'name',width:180,">套餐名称</th> 
				 <th data-options="field:'number',width:180">次数</th> 
				 <th data-options="field:'price',width:180">价格</th> 
				 <th data-options="field:'coType',width:240,formatter:userType">用户类型</th> 
				 <th data-options="field:'bkey',width:180,formatter:bType">业务模块</th> 
				 <th data-options="field:'days',width:180">天数</th> 
				 <th data-options="field:'status',width:180,formatter:statusNew">状态</th>  
				 <th data-options="field:'registerId',width:180,formatter: rowformater">操作</th>
			</tr></thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	function extshow(){
		var url = $("#imgShow1").attr('src');
		window.location.href=url;
	}
	function detail(registerId,realName,registerType){
		addRoleTab("审批详情", "/combo/detail.do",registerId,realName,registerType);
	}
	function addRoleTab(subtitle, url,registerId,realName,registerType) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame2(bashpath+url,registerId,realName,registerType),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "审批详情选项卡已存在，请关闭后再选择", 'info');
		}
		tabClose();
	}
	function createFrame2(url,registerId,realName,registerType) {
		var iframeId = "${iframeId}";
		url = url + "?id=" + iframeId + "&registerId=" + registerId +
				"&realName=" + realName + "&registerType=" + registerType;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}

	
	function rowformater(value,row,index){
		return "<a href='#' onclick='detail("+row.id+","+"\""+row.name+"\""+","+row.registerType+")'>删除</a>";
	}
	
	var dateFormatter = function(value, row, index){
		return new Date(value).format('yyyy-MM-dd hh:mm:ss');
	}
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
	var statusFormatter = function(value, row, index){
		if(value == 1){
    		return "未激活";
    	}else if(value == 2){
    		return "未认证";
    	}else if(value == 3){
    		return "认证成功";
    	}else{
    		return "认证失败";
    	}
	}
	
	$(function(){
		//设置分页控件 
		var p = $('#listdata').datagrid('getPager'); 
		$(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [10,20,50],//可以设置每页记录条数的列表 
			beforePageText: '第',//页数文本框前显示的汉字 
			afterPageText: '页	共 {pages} 页', 
			displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
			onBeforeRefresh:function(){
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
	});
	
	//点击查找按钮出发事件
	function searchFunc() {
		$("#listdata").datagrid("load", {
			realName:$('#realName').val(),
			registerType:$('#registerType').combobox('getValue'),
			status:$('#status').combobox('getValue')
		}); //将searchForm表单内的元素序列为对象传递到后台
	}
	
	</script>
</html>