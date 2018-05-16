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
				<span>用户名称:</span><input type="text" id="searchUserName" class="easyui-textbox"
					name="customerName" value="" size=10 /> 
					<span>手机号:</span><input type="text" id="searchMobile" class="easyui-textbox"
					name="mobile" value="" size=10 /> 
					<span>审核时间:</span>
					<input class="easyui-datetimebox" id = "searchStartTime" name="startTime"  style="width:150px">
					<span>至 </span>
					<input class="easyui-datetimebox" id = "searchEndTime" name="endTime"  style="width:150px">
					<a href="#" class="easyui-linkbutton"
					onclick="searchFunc()" iconCls="icon-search">查询</a> 
				<a href="#"
					class="easyui-linkbutton" onclick="toAddtype()" iconCls="icon-add"
					plain="true">新增</a> 
				
				
			</div>
		</div>

	
		<table id="custom_list"url="<%=path%>/personal/query.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">	
		<!-- singleSelect: true,  单选 -->
	
			<thead>
				<tr>
					<th data-options="field:'customerName',width:100">用户名称</th>
					<!--<th data-options="field:'id',checkbox:true"></th>
					 <th
						data-options="field:'dateTime',width:100,formatter:dateFormatter">发布日期</th> -->
					
					<th data-options="field:'mobile',width:100">用户移动电话</th>
					<th data-options="field:'email',width:100">用户邮箱地址</th>
					<th data-options="field:'idCard',width:100">用户身份证号</th>
					<th data-options="field:'address',width:100">用户地址</th>
					<th data-options="field:'postcode',width:100">邮政编码</th>
					<th data-options="field:'bkey',width:100">数据来源（key）</th>
					<th data-options="field:'status',width:100,formatter:strutsFormatter">实名认证状态</th>
					<th data-options="field:'delStatus',width:100,formatter:delStrutsFormatter">用户状态</th>
					<th data-options="field:'appOperateTime',width:100,formatter:dateFormatter">审核时间</th>
					<th data-options="field:'ids',width:180,formatter: rowformater">操作</th>
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
	
	$.extend($.fn.validatebox.defaults.rules,{
		mobileRule: {//value值为文本框中的值
	        validator: function (value) {
	            var reg =  /^(13[0-9]{9})|(18[0-9]{9})|(15[0-9][0-9]{8})$/;
	            return reg.test(value);
	        },
	        message: "输入手机号码格局不正确."
	    },
	    checkUser:{
	    	validator : function(value, param) {      
	    		var username = $("#edit_userName").val().trim();            
	    		console.log(username);
	    		var msg;
	    		  $.ajax({               
	    		     type : 'post',                
	    		    async : false,                
	    		    url : _basePath+"/personal/checkUser.do",                
	    		    data : { "userName" : username},                
	    		    success : function(data) {                   
	    		    		msg =data.msg ;                
	    		    	}            
	    		     });
	    		   var customerId = $('#id').val();
	    		   if(customerId > 0 ){
	    			   return  true;
	    		   }
					if(msg == '1000'){
						return true;
					}else{
						return false;
					}    
	    	},
	        message: "用户名已被占用."
	    },
	    userLength:{
	    	validator : function(value, param) {  
	    		var min = param[0];
	    		var max = param[1];
	    		if(value.length >= min && value.length <= max){
	    			var reg =  /^[a-zA-z]\w{3,15}$/;
		            return reg.test(value);
	    		}else{
	    			return false;
	    		}
	    		return value.length >= param[0];
	    	},
	        message: "用户名需以字母开头，并由字母、数字、下划线组成，4-16位."
	    }
	});

	function doDelete(id,delStruts){
		if(delStruts == 1){
			$.messager.alert('提示','已冻结用户不可点击','info');
			return;
		}
		$.messager.confirm('提示', '确认要冻结所选数据？', function(r){
			if (r){
			$.post(_basePath+"/personal/delStatus.do", { "customerId":id },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#custom_list').datagrid("reload");
				});
			}
		});
	}
	function doDeletes(id,delStruts){
		if(delStruts == 0){
			$.messager.alert('提示','未冻结用户不可点击','info');
			return;
		}
		$.messager.confirm('提示', '确认要解冻所选数据？', function(r){
			if (r){
			$.post(_basePath+"/personal/delStatus.do", { "customerId":id },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#custom_list').datagrid("reload");
				});
			}
		});
	}
	$(function(){
		// 树
		 $('#custom_list').datagrid({
			url:'<%=path%>/personal/query.do',
			onLoadError:function(arguments){
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#custom_list').datagrid('getPager'); 
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

	var sexFormatter = function(value, row, index) {
		if (value == 1) {
			return "个人";
		} else {
			return "企业";
		}
	}
	var strutsFormatter = function(value, row, index) {
		if (value == 0) {
			return "未认证";
		} else if(value == 1){
			return "认证成功";
		}else if(value == 2){
			return "认证驳回";
		}else{
			return "待审核";
		}
	}
	var delStrutsFormatter = function(value, row, index) {
		if (value == 0) {
			return "正常";
		} else if(value == 1){
			return "冻结";
		}
	}
	var dateFormatter = function(value, row, index) {
		if(value == '' || value == null){
			return "";
		}else{
			return new Date(value).format('yyyy-MM-dd hh:mm:ss');
		}
		 
	}
	function searchFunc() {
		var startTime = $('#searchStartTime').datebox('getValue');
		var endTime = $('#searchEndTime').datebox('getValue');
		$('#custom_list').datagrid('load',{  
			customerName:$('#searchUserName').val(),
			mobile:$('#searchMobile').val(),
			startTime:startTime,
			endTime:endTime
		});
	}
	
	function toAddtype(){
		addRoleTab("添加个人用户", "/personal/addCustom.do");
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
	function saveCrmUser(){
		var operateUrl = "";
		//判断是否选择行
		
		
		var rows = $('#custom_list').datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/personal/save.do";
		}else{
			operateUrl = "<%=path%>/personal/modify.do";
		}
		console.log(operateUrl);
		$('#fm').form('submit',{
			url:operateUrl ,
			onSubmit:function(param){
				return $(this).form('validate');
			},
			type: "POST",
			beforeSend: ajaxLoading,
			error: function(request) {
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				$.messager.alert('提示', '保存成功', 'info');
				$('#fm').form('clear');
			   	$('#custom_list').datagrid("reload");
				$('#dlg').dialog('close'); 
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
	function addRole(){
		var rows = $('#custom_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		$.ajax({
			type: "POST",
			dataType:"JSON",
			url:"<%=path%>/personal/userRole.do",
			data:{id:rows[0].id},
			beforeSend:ajaxLoading,
			async: true,
			error: function(request) {
				//alert("Connection error");
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
			   	//打开编缉框
				$('#rolesdlg').dialog('open').dialog('setTitle','用户角色分配');
				var obj = eval(data.roles);
				var user = data.user;
				$("#selectUserId").val(user.userId);
				for (var j = 0; j < obj.length; j++) {
					$("<tr><td>"+"<input type='radio' name='iframeId' value='"+obj[j].iframeId+"'"+(obj[j].name==user.roleName?"checked='checked'":'')+
							"</td><td>"+obj[j].name+"</td><td>"+obj[j].description+"</td></tr>").appendTo("#rolesList");
				}
			},
			complete:function(){
				//关闭覆盖层 loading
			   	$(".datagrid-mask").remove(); 
			   	$(".datagrid-mask-msg").remove();  
			}
		});
	}
	function editUserTab(subtitle, url,customerId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,customerId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "修改用户选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	function createFrame3(url,customerId) {
		var iframeId = "${iframeId}";
		url = url + "?id=" + iframeId + "&customerId=" + customerId;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}
	function toModify(id){
		editUserTab("修改个人用户", "/personal/toModify.do",id);
	}
	function toAddOrder(id){
		editOrderTab("添加订单", "/personal/toAddOrder.do",id);
	}
	
	
	
	
	function editOrderTab(subtitle, url,customerId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,customerId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "添加订单选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	function toListPer(id){
		editOrderTab1("查看个人详细信息", "/personal/toModifys.do",id);
	}
	function editOrderTab1(subtitle, url,customerId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,customerId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "查看详细信息选项卡已存在，请关闭后再选择", 'info');
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
	
	function rowformater(value,row,index){
		//return "<a href='#' onclick='detail("+row.registerId+","+"\""+row.realName+"\""+","+row.risterType+")'>通过</a>";
		var html = "<a href='#' onclick='doDelete("+row.id+","+row.delStatus+")'>冻结</a>"
			+"&nbsp;&nbsp;"+"<a href='#' onclick='doDeletes("+row.id+","+row.delStatus+")'>解冻</a>"
			+"&nbsp;&nbsp;"+"<a href='#' onclick='toModify("+row.id+")'>修改</a>"
			+"&nbsp;&nbsp;"+"<a href='#' onclick='toAddOrder("+row.id+")'>添加订单</a>"
			+"&nbsp;&nbsp;"+"<a href='#' onclick='toListPer("+row.id+")'>查看详细信息</a>";
		return html;
		 
	}
</script>
</html>