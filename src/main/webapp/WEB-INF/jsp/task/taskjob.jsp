<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>后端企业管理</title>
<script type="text/javascript" src="<%=path %>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">

		<table id="task_list"url="<%=path%>/task/taskById.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">	
		<!-- singleSelect: true,  单选 -->
	
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'methods',width:100">异常方法</th>
					<th data-options="field:'description',width:100">异常信息</th>
					<th data-options="field:'nowTime',width:100,formatter:myformatter,parser:myparser">时间</th>
				</tr>
			</thead>
		</table>
	</div>
	
	
</body>
<script type="text/javascript">

var statusFormatter = function(value, row, index) {
	if (value == 0) {
		return "未认证";
	} else if (value == 1) {
		return "认证成功";
	} else if (value == 2){
		return "认证驳回";
	}else {
		return "待审核";
	}
}
	
	
	$(function(){
		// 树
		var jobId = "${taskJob.jobId}";
		 $('#task_list').datagrid({
			url:'<%=path%>/task/taskById.do?jobId='+jobId,
			onLoadError:function(arguments){
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#task_list').datagrid('getPager'); 
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
	function myformatter(value) {
		  var date = new Date(value);
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h = date.getHours();
		var M = date.getMinutes();
		var s = date.getSeconds();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d) ;
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
	
	
</script>
</html>