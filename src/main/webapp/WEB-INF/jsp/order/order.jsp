<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>订单管理</title>
<script type="text/javascript" src="<%=path%>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">

		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<span>订单编号:</span><input type="text" id="searchOrderNO"
					class="easyui-textbox" name="orderNO" value="" size=10 /> <span>业务模块名称:</span><input
					type="text" id="searchBusinessName" class="easyui-textbox"
					name="businessName" value="" size=10 /> <span>计费模式:</span><select
					id="searchPaidMode" class="easyui-combobox" name="paidMode">
					<option value="">请选择</option>
					<option value="1">次数</option>
					<option value="2">天数</option>
					<option value="3">次数+天数</option>
					<option value="4">储存空间</option>
					<option value="5">储存空间+天数</option>
				</select> <a href="#" class="easyui-linkbutton" onclick="searchFunc()"
					iconCls="icon-search">查询</a> <br></br> <a href="#"
					class="easyui-linkbutton" onclick="toModify()" iconCls="icon-edit"
					plain="true">修改</a> <a href="#" class="easyui-linkbutton"
					onclick="doDelete()" iconCls="icon-remove" plain="true">取消订单</a>

			</div>
		</div>



		<table id="order_list" url="<%=path%>/order/query.do"
			class="easyui-datagrid" style="width: auto;"
			data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'orderNO',width:100">订单编号</th>
					<th data-options="field:'businessName',width:100">业务模块名称</th>
					<th data-options="field:'cName',width:100">用户账号</th>
					<th data-options="field:'comboName',width:100">套餐名称</th>
					<th data-options="field:'price',width:150">实际价格</th>
					<th data-options="field:'number',width:100">次数</th>
					<th data-options="field:'days',width:100">天数</th>
					<th data-options="field:'space',width:100">储存空间G</th>
					<!-- 时间 -->
					<th
						data-options="field:'startTime',width:100,formatter:myformatter,parser:myparser">开始时间</th>
					<!-- 时间 -->
					<th
						data-options="field:'endTime',width:100,formatter:myformatter,parser:myparser">结束时间</th>
					<!-- 时间 -->
					<th
						data-options="field:'orderTime',width:100,formatter:myformatter,parser:myparser">下单时间</th>
					<!-- 时间 -->
					<th
						data-options="field:'payTime',width:100,formatter:myformatter,parser:myparser">支付时间</th>
					<!-- 下拉框 -->
					<th
						data-options="field:'paidMode',width:100,formatter:paidModeFormatter">计费模式</th>
					<th
						data-options="field:'orderType',width:100,formatter:orderTypeFormatter">订单类型</th>
					<!-- 下拉框 -->
					<th
						data-options="field:'status',width:100,formatter:statusFormatter">状态</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript">

	$(function(){
		// 树
		 $('#user_list').datagrid({
			url:'<%=path%>/order/query.do',
			onLoadError : function(arguments) {
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#order_list').datagrid('getPager');
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
	var orderTypeFormatter = function(value, row, index) {
		if (value == 1) {
			return "个人订单";
		} else if (value == 2) {
			return "企业订单";
		} 
	}
	var paidModeFormatter = function(value, row, index) {
		if (value == 1) {
			return "次数";
		} else if (value == 2) {
			return "天数";
		} else if(value == 3){
			return "次数+天数";
		}else if(value == 4){
			return "存储空间";
		}else{
			return "储存空间+天数";
		}
	}
	var statusFormatter = function(value, row, index) {
		if (value == 0) {
			return "待缴费";
		} else if (value == 1) {
			return "已交费";
		} else {
			return "取消";
		}
	}
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
	function toModify() {
		var rows = $('#order_list').datagrid('getSelections');
		var parm = "";

		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		var status = rows[0].status;
		if (status != '1') {
			editOrderTab("修改订单", "/order/toModify.do", rows[0].id);
		} else {
			alert("订单已缴费无法修改订单");
		}

	}

	function editOrderTab(subtitle, url, id) {
		var jq = top.jQuery;
		var bashpath = getRootPath_web();
		if (!jq('#tabs', window.top.document).tabs('exists', subtitle)) {
			jq('#tabs', window.top.document).tabs('add', {
				title : subtitle,
				content : createOrderFrame(bashpath + url, id),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "修改订单选项卡已存在，请关闭后再选择", 'info');
		}
		tabClose();
	}

	function createOrderFrame(url, id) {
		var iframeId = "${iframeId}";
		url = url + "?iframeId=" + iframeId + "&id=" + id;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
				+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}

	function doDelete() {

		var rows = $('#order_list').datagrid('getSelections');
		var status = rows[0].status;
		if (status == '1') {
			alert("订单已付费无法取消");
			return false;
		}
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要删除的对象!', 'info');
			return;
		}

		$.messager.confirm('提示', '确认要删除所选数据？', function(r) {
			if (r) {
				$.post(_basePath + "/order/deleteStatus.do", {
					"id" : rows[0].id
				}, function(result) {
					$.messager.alert('提示', result.data.msg + '！！！', 'info');
					$('#order_list').datagrid("reload");
				});
			}
		});
	}

	function searchFunc() {
		$('#order_list').datagrid('load', {
			orderNO : $('#searchOrderNO').val(),
			businessName : $('#searchBusinessName').val(),
			paidMode : $('#searchPaidMode').combobox('getValue')

		});
	}
</script>
</html>