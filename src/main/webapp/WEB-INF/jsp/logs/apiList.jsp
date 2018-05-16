<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
	<title>3A系统异常日志</title>
	<script type="text/javascript" src="<%=path%>/js/west.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'center'"
	 style="height: 100%; overflow-y: hidden">
	<form id="searchForm">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a href="#" class="easyui-linkbutton" onclick="searchDate(0)">当天</a>
				<a href="#" class="easyui-linkbutton" onclick="searchDate(1)">最近7天</a>
				<a href="#" class="easyui-linkbutton" onclick="searchDate(2)">最近30天</a>
				<a href="#" class="easyui-linkbutton" onclick="searchDate(3)">当月</a>
				<span>操作模块:</span><input
					type="text" id="searchModule" class="easyui-textbox" name="module"
					value="" size=10/>
				<span>操作方法:</span><input type="text" id="searchcMethods" class="easyui-textbox"
										 name="methods" value="" size=10/>

				<span>操作日期: </span> <input class="easyui-datetimebox"
										   id="startTime" name="startTime" style="width: 150px">
				<span>至</span> <input class="easyui-datetimebox" id="endTime" name="endTime"
									  style="width: 150px">
				<a href="#" class="easyui-linkbutton" onclick="searchFunc()" iconCls="icon-search">查询</a>
			</div>
		</div>
	</form>
	<table id="log_list" url="<%=path%>/apilogs/query.do"
		   class="easyui-datagrid" style="width: auto;"
		   data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
		<thead>
		<tr>
			<th data-options="field:'id',checkbox:true"></th>
			<th data-options="field:'module',width:100">操作模块</th>
			<th data-options="field:'methods',width:100">操作方法</th>
			<th data-options="field:'description',width:100">操作描述</th>
			<th data-options="field:'nowTime',width:100">操作用时</th>
			<th data-options="field:'server_ip',width:100">服务器ip</th>
			<th data-options="field:'className',width:100">AOP代理类的名字</th>
			<th data-options="field:'methodName',width:100">代理的方法</th>
			<th data-options="field:'argString',width:100">请求参数信息</th>
			<th data-options="field:'exceptionMessage',width:100">异常信息</th>
		</tr>
		</thead>
	</table>
</div>

</body>
</html>
<script type="text/javascript">
	$(function () {
		//设置分页控件
		var p = $('#log_list').datagrid('getPager');
		$(p).pagination({
			pageSize: 10,//每页显示的记录条数，默认为10
			pageList: [5, 10, 15],//可以设置每页记录条数的列表
			beforePageText: '第',//页数文本框前显示的汉字
			afterPageText: '页 共 {pages} 页',
			displayMsg: '当前显示 {from} - {to} 条记录 共 {total} 条记录',
			onBeforeRefresh: function () {
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
	});

	function searchDate(dateSelectType) {
		$("#log_list").datagrid("load", {
			dateSelectType : dateSelectType
		}); //将searchForm表单内的元素序列为对象传递到后台
	}

	//点击查找按钮出发事件
	function searchFunc() {
		var startTime = $('#startTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		$("#log_list").datagrid("load", {
			startTime : startTime,
			endTime : endTime,
			module : $('#searchModule').val(),
			methods : $('#searchcMethods').val()
		}); //将searchForm表单内的元素序列为对象传递到后台
	}

</script>
