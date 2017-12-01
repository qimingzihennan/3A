<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>业务管理</title>
<script type="text/javascript" src="<%=path%>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" onclick="doDelete()"
					iconCls="icon-remove" plain="true">删除</a>
			</div>
		</div>


		<table id="business_list" url="<%=path%>/business/query.do"
			class="easyui-datagrid" style="width: auto;"
			data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<!-- singleSelect: true,  单选 -->

			<thead>
				<tr>
					<th data-options="field:'userId',checkbox:true"></th>
					<th data-options="field:'userName',width:100">业务模块名称</th>
					<th data-options="field:'realName',width:100">业务模块描述</th>
					<th data-options="field:'mobile',width:100">业务模块key</th>
					<th data-options="field:'email',width:150">业务扩展字段</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var url =<%=path%>+ "/user/query.do"
		$('#user_list').datagrid({
			url : url,
			onLoadError : function(arguments) {
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#user_list').datagrid('getPager');
		$(p).pagination({
			pageSize : 10,//每页显示的记录条数，默认为10 
			pageList : [ 5, 10, 15 ],//可以设置每页记录条数的列表 
			beforePageText : '第',//页数文本框前显示的汉字 
			afterPageText : '页	共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			onBeforeRefresh : function() {
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
	});
</script>
</html>