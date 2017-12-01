<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>用户操作日志情况查看</title>
<script type="text/javascript" src="<%=path%>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<span>业务模块名称:</span><input type="text" id="searchBusinessName"
					class="easyui-textbox" name="businessName" value="" size=10 /> <span>企业名称:</span><input
					type="text" id="searchEnterpriseName" class="easyui-textbox" name="enterpriseName"
					value="" size=10 /> <a href="#" class="easyui-linkbutton"
					onclick="searchFunc()" iconCls="icon-search">查询</a>

			</div>
		</div>
		<table id="consumelog_list"
			url="<%=path%>/consume/log/enterprise/query.do"
			class="easyui-datagrid" style="width: auto;"
			data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'businessName',width:100">业务模块名称</th>
					<th data-options="field:'enterpriseName',width:100">企业名称</th>
					<th
						data-options="field:'operateTime',width:100,formatter:myformatter,parser:myparser">操作时间</th>

				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
<script type="text/javascript">
	function myformatter(value) {
		  var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h = date.getHours();
			var M = date.getMinutes();
			var s = date.getSeconds();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
					+ (d < 10 ? ('0' + d) : d) +' '+h+":"+M+":"+s;
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

	function searchFunc() {
		$('#consumelog_list').datagrid('load', {
			businessName : $('#searchBusinessName').val(),
			enterpriseName : $('#searchEnterpriseName').val()
		});
	}
</script>