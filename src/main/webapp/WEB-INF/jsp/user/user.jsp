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
				<span>用户名:</span><input type="text" id="searchUserName" class="easyui-textbox"
					name="userName" value="" size=10 /> 
			  <span>用户姓名:</span><input
					type="text" id="searchRealName" name="realName" value="" class="easyui-textbox" size=10 />
				 <a href="#" class="easyui-linkbutton"
					onclick="searchFunc()" iconCls="icon-search">查询</a> 
				<a href="#"
					class="easyui-linkbutton" onclick="toAddtype()" iconCls="icon-add"
					plain="true">新增</a> 
				<a href="#" class="easyui-linkbutton"
					onclick="toModify()" iconCls="icon-edit" plain="true">修改</a> 
					
				<a href="#" class="easyui-linkbutton"
					onclick="toModifyPwd()" iconCls="icon-cut" plain="true">修改密码</a> 
					
				<a
					href="#" class="easyui-linkbutton" onclick="doDelete()"
					iconCls="icon-remove" plain="true">删除</a>
				<a href="#" class="easyui-linkbutton" onclick="addRole()">分配角色</a>
			</div>
		</div>

	
		<table id="user_list"url="<%=path%>/user/query.do" class="easyui-datagrid" style="width:auto;"  data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">	
		<!-- singleSelect: true,  单选 -->
	
			<thead>
				<tr>
					<th data-options="field:'userId',checkbox:true"></th>
					<th data-options="field:'userName',width:100">用户名</th>
					<!-- <th
						data-options="field:'dateTime',width:100,formatter:dateFormatter">发布日期</th> -->
					<th data-options="field:'realName',width:100">用户姓名</th>
					<th data-options="field:'sex',width:100,formatter:sexFormatter">性别</th>
					<th data-options="field:'mobile',width:100">用户移动电话</th>
					<th data-options="field:'email',width:150">用户名邮箱地址</th>
					<th data-options="field:'roleName',width:100">角色名称</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:470px;padding:10px 20px" closed="true" modal="true" buttons="#dlg-buttons">
		<div class="ftitle">后台用户管理</div>
			<form id="fm" method="post">
				<input type='hidden' name='userId' id="userId" value='' />
				<table border="0">
					<tr>	
						<td>
							<label>用户名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_userName" name="userName" 
								missingMessage="用户名必须填写"  data-options="required:true,validType:['checkUser','userLength[4,16]']" ></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户姓名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_realName" name="realName" required="true"
								missingMessage="姓名必须填写"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>性别:</label>
						</td>
						<td>
							<select class="easyui-combobox" id="edit_sex" name="sex" style="width:200px;">
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
							<input id="edit_mobile" class="easyui-validatebox"  missingMessage="电话号码不可以为空" name="mobile" data-options="required:true,validType:['mobileRule','length[11,11]']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户名邮箱地址:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_email" name="email" validtype="email" 
							required="true" missingMessage="邮箱不可以为空" invalidMessage="邮箱格式不正确" ></input>
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
	    		    url : _basePath+"/user/checkUser.do",                
	    		    data : { "userName" : username},                
	    		    success : function(data) {                   
	    		    		msg =data.msg ;                
	    		    	}            
	    		     });
	    		   var userId = $('#userId').val();
	    		   if(userId > 0 ){
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

	function doDelete(){
		
		var rows = $('#user_list').datagrid('getSelections');
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要删除的对象!', 'info');
			return;
		}
		
		$.messager.confirm('提示', '确认要删除所选数据？', function(r){
			if (r){
			$.post(_basePath+"/user/delete.do", { "userId":rows[0].userId },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#user_list').datagrid("reload");
				});
			}
		});
	}

	$(function(){
		// 树
		 $('#user_list').datagrid({
			url:'<%=path%>/user/query.do',
			onLoadError:function(arguments){
				errorHandler(arguments);
			}
		});
		//设置分页控件 
		var p = $('#user_list').datagrid('getPager'); 
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
			return "男";
		} else {
			return "女";
		}
	}
	
	function searchFunc() {
		$('#user_list').datagrid('load',{  
			userName:$('#searchUserName').val(),
			realName:$('#searchRealName').val()
		});
	}
	
	function toAddtype(){
		addRoleTab("添加用户", "/user/addUser.do");
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
		
		
		var rows = $('#user_list').datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/user/save.do";
		}else{
			operateUrl = "<%=path%>/user/modify.do";
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
			   	$('#user_list').datagrid("reload");
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
		var rows = $('#user_list').datagrid('getSelections');
		var parm = "";
		$('#rolesList').html("");
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		$.ajax({
			type: "POST",
			dataType:"JSON",
			url:"<%=path%>/user/userRole.do",
			data:{userId:rows[0].userId},
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
					$("<tr><td>"+"<input type='radio' name='roleId' value='"+obj[j].roleId+"'"+(obj[j].name==user.roleName?"checked='checked'":'')+
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
	function editUserTab(subtitle, url,userId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,userId),
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
	function createFrame3(url,userId) {
		var iframeId = "${iframeId}";
		url = url + "?id=" + iframeId + "&userId=" + userId;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}
	function toModify(){
		var rows = $('#user_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		editUserTab("修改用户", "/user/toModify.do",rows[0].userId);
	}
	
	function toModifyPwd(){
		var rows = $('#user_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		editUserTab("修改用户密码", "/user/toModifyPwd.do",rows[0].userId);
	}
	
	function allocation() {
		$
				.ajax({
					async : false, //请勿改成异步，下面有些程序依赖此请数据
					type : "POST",
					data : {
						userId : $('#selectUserId').val(),
						roleId : $('input[name="roleId"]:checked').val()
					},
					url : "<%=path%>/user/allocation.do",
					dataType : 'json',
					success : function(json) {
						$.messager.alert('提示', '保存成功', 'info');
						$('#user_list').datagrid("reload");
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