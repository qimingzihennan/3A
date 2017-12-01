<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>后端用户管理</title>
<script type="text/javascript" src="<%=path %>/js/west.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow-y: hidden">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<a
					href="#" class="easyui-linkbutton"  onclick="toListPer()"
					iconCls="icon-search" plain="true">查看异常信息</a>
				
			</div>
		</div>

	
		<table id="task_list"url="<%=path%>/task/query.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">	
		<!-- singleSelect: true,  单选 -->
	
			<thead>
				<tr>
					<th data-options="field:'jobId',checkbox:true"></th>
					<th data-options="field:'jobName',width:100">定时器任务方法名称</th>
					<!-- <th
						data-options="field:'dateTime',width:100,formatter:dateFormatter">发布日期</th> -->
					<th data-options="field:'jobDescribe',width:100">定时器任务描述</th>
					<th data-options="field:'jobStatus',width:100,formatter:strutsFormatter">定时器任务状态</th>
					
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:470px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
		<div class="ftitle">后台用户管理</div>
			<form id="fm" method="post">
				<input type='hidden' name='id' id="id" value='' />
				<table border="0">
					<tr>	
						<td>
							<label>用户名称:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="customerName" name="customerName" 
								missingMessage="用户必须填写"  data-options="required:true,validType:['checkUser','userLength[4,16]']" ></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用戶类型:</label>
						</td>
						<td>
							<select class="easyui-combobox" id="customerType" name="customerType" style="width:200px;">
								<option id="sexMale" value="1" >男</option>
								<option id="sexMaleFemale" value="2">女</option>
							</select>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户移动电话:</label>
						</td>
						<td>
							<input id="mobile" class="easyui-validatebox"  missingMessage="电话号码不可以为空" name="mobile" data-options="required:true,validType:['mobileRule','length[11,11]']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户邮箱地址:</label>
						</td>
						<td>
							<input id="email" class="easyui-validatebox"  missingMessage="邮箱不可以为空" name="email" data-options="required:true,validType:['mobileRule','length[11,11]']"></input>
						</td>
					</tr>
					
				</table>
				
			</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveCrmUser()" id="save-btn" style="width:90px">保存</a>
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveRepair_()" style="width:90px">Saves</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
		</div>
		
		
	<div id="rolesdlg" class="easyui-dialog" style="width:400px;height:470px;padding:10px 20px" closed="true" modal="true" buttons="#rolesdlg-buttons">
		<div class="ftitle">用户角色分配</div>
		<input type='hidden' name='selectUserId' id="selectUserId" value='' />
			<table>
					<thead>
						<tr style="background-color: #e6eaec;">
							<th></th>
							<th>角色名</th>
							<th style="text-align: center;">描述</th>
						</tr>
					</thead>
					<tbody id="rolesList">	
					</tbody>
				</table>
	</div>
	<div id="rolesdlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="allocation()" id="save-btn1" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#rolesdlg').dialog('close')" style="width:90px">取消</a>
		</div>
		
</body>
<script type="text/javascript">
	
	

	$(function(){
		// 树
		 $('#task_list').datagrid({
			url:'<%=path%>/task/query.do',
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

	var strutsFormatter = function(value, row, index) {
		if (value == 0) {
			return "正常";
		} else {
			return "异常";
		}
	}
	
	
	function searchFunc() {
		$('#task_list').datagrid('load',{  
			customerName:$('#searchUserName').val(),
		});
	}
	


	function createFrame3(url,jobId) {
		var iframeId = "${iframeId}";
		url = url + "?id=" + iframeId + "&jobId=" + jobId;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}
	
	function toListPer(){
		var rows = $('#task_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要查看的对象!', 'info');
			return;
		}
		editOrderTab1("查看异常信息", "/task/findTaskJobById.do",rows[0].jobId);
	}
	function editOrderTab1(subtitle, url,jobId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,jobId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "查看异常信息选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	
	
	function allocation() {
		$
				.ajax({
					async : false, //请勿改成异步，下面有些程序依赖此请数据
					type : "POST",
					data : {
						userId : $('#selectUserId').val(),
						roleId : $('input[name="id"]:checked').val()
					},
					url : "<%=path%>/personal/allocation.do",
					dataType : 'json',
					success : function(json) {
						$.messager.alert('提示', '保存成功', 'info');
						$('#custom_list').datagrid("reload");
						$('#rolesdlg').dialog('close'); 
					},
					complete:function(){
						//关闭覆盖层 loading
					   	$(".datagrid-mask").remove(); 
					   	$(".datagrid-mask-msg").remove();  
					}
				});
	}
</script>
</html>