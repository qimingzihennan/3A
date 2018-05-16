<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>订单使用情况查看</title>
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
				</select> 
				<span>客户姓名:</span><input type="text" id="searchcName"
					class="easyui-textbox" name="cName" value="" size=10 />
				<span>客户电话号码:</span><input
					type="text" id="searchMobile" class="easyui-textbox"
					name="mobile" value="" size=10 />

				<a href="#" class="easyui-linkbutton" onclick="searchFunc()"
					iconCls="icon-search">查询</a>
				<a href="#" class="easyui-linkbutton" onclick="queryRedisData()">查看Redis数据</a>
				<br></br> <a href="#" class="easyui-linkbutton"
					onclick="switchStatus()" iconCls="icon-remove" plain="true">开启/暂定用户使用</a>

			</div>
		</div>

		<table id="consume_list" url="<%=path%>/consume/cci/query.do"
			class="easyui-datagrid" style="width: auto;"
			data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th data-options="field:'orderNO',width:100">订单编号</th>
					<th data-options="field:'businessName',width:100">业务模块名称</th>
					<th data-options="field:'cName',width:100">用户名称</th>
					<th data-options="field:'mobile',width:100">电话</th>
					<th data-options="field:'orderType',width:100,formatter:typeFormatter">用户类型</th>
					<th data-options="field:'totalNumber',width:100">总次数</th>
					<th data-options="field:'residueNumber',width:100">剩余次数</th>
					<!-- 时间 -->
					<th
						data-options="field:'startTime',width:100,formatter:myformatter,parser:myparser">开始时间</th>
					<!-- 时间 -->
					<th
						data-options="field:'endTime',width:100,formatter:myformatter,parser:myparser">结束时间</th>
					<!-- 下拉框 -->
					<th
						data-options="field:'paidMode',width:100,formatter:paidModeFormatter">计费模式</th>
					<!-- 下拉框 -->
					<th
						data-options="field:'status',width:100,formatter:statusFormatter">状态</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="consumedlg" class="easyui-dialog" style="width:600px;height:200px;padding:10px 20px" closed="true" modal="true" buttons="#consumedlg-buttons">
		<div class="ftitle">用户消费数据查看</div>
		<table>
			<thead>
			<tr style="background-color: #e6eaec;">
				<th>计费模式</th>
				<th>状态</th>
				<th>开始时间</th>
				<th>截止时间</th>
				<th>剩余次数</th>
				<th style="text-align: center;">套餐内容</th>
			</tr>
			</thead>
			<tbody id="consumeQueryList">
			</tbody>
		</table>
	</div>
</body>
</html>
<script type="text/javascript">
	var paidModeFormatter = function(value, row, index) {
		if (value == 1) {
			return "次数";
		} else if (value == 2) {
			return "天数";
		} else if(value == 3){
			return "次数+天数";
		}else if(value == 4){
			return "储存空间";
		}else{
			return "储存空间+天数";
		}
	}
	var statusFormatter = function(value, row, index) {
		if (value == 0) {
			return "正在使用";
		} else if (value == 1) {
			return "暂停";
		}else if(value == 2){
			return "待使用";
		}else if(value == 3){
			return "完成消费";
		}
	}
	var typeFormatter = function(value,row,index){
		if(value == 1){
			return "个人";
		}else{
			return "企业";
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
		$('#consume_list').datagrid('load',{  
			orderNO:$('#searchOrderNO').val(),
			businessName:$('#searchBusinessName').val(),
			paidMode:$('#searchPaidMode').combobox('getValue'),
			customerName:$('#searchcName').val(),
			enterpriseName:$('#searchEnterpriseName').val(),
			mobile:$('#searchMobile').val()
		});
	}
	
	function queryRedisData() {
		var rows = $('#consume_list').datagrid('getSelections');
		var parm = "";
		$('#consume_list').html("");
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要查看的对象!', 'info');
			return;
		}
		$.ajax({
			type: "POST",
			dataType:"JSON",
			url:"<%=path%>/consume/cci/queryRedisData.do",
			data:{cusId:rows[0].cusId,orderType:rows[0].orderType,bkey:rows[0].bkey},
			beforeSend:ajaxLoading,
			async: true,
			error: function(request) {
				//alert("Connection error");
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				//打开编缉框
				$('#consumedlg').dialog('open').dialog('setTitle','用户消费数据展示');
				var obj = data.cusConsumeInventoryModel;
				$("#consumeQueryList").empty();
				var html;
				if(obj != null){
					html = "<tr>";
					var paidMode = obj.paidMode;
					if(paidMode == '1'){
						html = html+"<td>次数</td>";
					}else if(paidMode == '2'){
						html = html+"<td>天数</td>";
					}else if(paidMode == '3'){
						html = html+"<td>次数+天数</td>";
					}else if(paidMode == '4'){
						html = html+"<td>存储空间</td>";
					}else if(paidMode == '5'){
						html = html+"<td>存储空间+天数</td>";
					}
					var status = obj.status;
					if(status == '0'){
						html = html+"<td>正在使用</td>";
					}else if(status == '1'){
						html = html+"<td>暂停</td>";
					}else if(status == '2'){
						html = html+"<td>待使用</td>";
					}else if(status == '3'){
						html = html+"<td>完成消费</td>";
					}
					html = html+"<td>"+obj.startTime+"</td>"+"<td>"+obj.endTime+"</td>"+"<td>"+obj.num+"</td>"
							+"<td>"+obj.content+"</td>";
				}else{
					html = "<tr><td colspan='6'>Redis数据不存在</td></tr>"
				}


					console.log(html);
					$(html).appendTo("#consumeQueryList");

			},
			complete:function(){
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
	
	
	function switchStatus(){
		var rows = $('#consume_list').datagrid('getSelections');
		var status = rows[0].status;
		var changeStatus = '0';

		if(status == '1'){
			changeStatus = '0';
		}else if(status == '0'){
			changeStatus = "1";
		}else{
			alert("不可修改！");
			return;
		}
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要修改的对象!', 'info');
			return;
		}
		
		$.messager.confirm('提示', '确认要修改所选数据？', function(r){
			if (r){
			$.post(_basePath+"/consume/cci/switchStatus.do", { "id":rows[0].id,"changeStatus":changeStatus },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#consume_list').datagrid("reload");
				});
			}
		});
	}
</script>