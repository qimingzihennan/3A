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

		<table id="enterprise_list"url="<%=path%>/enterprise/searchPerson.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">	
		<!-- singleSelect: true,  单选 -->
	
			<thead>
				<tr>
					<th data-options="field:'customerId',checkbox:true"></th>
					<th data-options="field:'customerName',width:100">员工名称</th>
					<th data-options="field:'mobile',width:100">员工电话</th>
					<th data-options="field:'email',width:100">员工邮箱</th>
					<th data-options="field:'idCard',width:100">员工身份证号</th>
					<th data-options="field:'address',width:100">员工地址</th>
					<th data-options="field:'status',width:100,formatter:statusFormatter">员工认证状态</th>
					<th data-options="field:'relation',width:100,formatter:isAdminFormatter">是否为管理员</th>
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
var isAdminFormatter = function(value, row, index) {
	console.log(value);
	
	if (value.isAdmin == 0) {
		return "否";
	} else if (value.isAdmin == 1) {
		return "是";
	} 
}
	
	$(function(){
		// 树
		var enterpriseId = "${enterprise.enterpriseId}";
		 $('#enterprise_list').datagrid({
			url:'<%=path%>/enterprise/searchPerson.do?enterpriseId='+enterpriseId,
			onLoadError:function(arguments){
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#enterprise_list').datagrid('getPager'); 
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

	
	
</script>
</html>