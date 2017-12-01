<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<title>后端企业管理</title>

</head>
<body class="easyui-layout">
	<div data-options="region:'center'"
		style="height: 100%; overflow: auto">
		<div id="toolbar" style="padding: 5px; height: auto">
			<div style="margin-bottom: 5px">
				<span>企业名称:</span><input type="text" id="searchEnterpriseName"
					class="easyui-textbox" name="enterpriseName" value="" size=10 /> <a
					href="#" class="easyui-linkbutton" onclick="searchFunc()"
					iconCls="icon-search">查询</a> <a href="#" class="easyui-linkbutton"
					onclick="toAddtype()" iconCls="icon-add" plain="true">新增</a> <a
					href="#" class="easyui-linkbutton" onclick="toModify()"
					iconCls="icon-edit" plain="true">修改</a> <a href="#"
					class="easyui-linkbutton" onclick="doDelete()"
					iconCls="icon-remove" plain="true">删除</a> <a href="#"
					class="easyui-linkbutton" onclick="toSearchPerson()"
					iconCls="icon-search" plain="true">查看企业员工</a> <a href="#"
					class="easyui-linkbutton" onclick="toAddOrder()" iconCls="icon-add"
					plain="true">添加订单</a> <a href="#" class="easyui-linkbutton"
					onclick="toApprove()" iconCls="icon-search" plain="true">查看/审核企业信息</a>
				<a href="#" class="easyui-linkbutton" onclick="toAddPIN_SD()"
					iconCls="icon-add" plain="true">添加企业PIN/SD码</a>
			</div>
		</div>
		<%-- <div id = "uploadFile2">
         <div class="form-group mno file">
                  <label for="photo" class="col-sm-1 control-label" style="text-align:left;width:100px;">对应首页图片</label>
                   <div class="col-sm-3" style="margin-top:7px;">
                   <form id="myform1"  action="<%=path%>/enterprise/upload.do" enctype="multipart/form-data" class="form-horizontal"  method="post">
                             <input  id="photo" name="photo" type="file">
                             <button type="submit">提交</button>
                             </form>
                     </div>
          </div>
   </div> --%>


		<table id="enterprise_list" url="<%=path%>/enterprise/query.do"
			class="easyui-datagrid" style="width: auto;"
			data-options="iconCls: 'icon-tip',rownumbers:true,toolbar: '#toolbar',
		pagination:'true',fit:true,fitColumns:true,striped:true,nowrap:false">
			<!-- singleSelect: true,  单选 -->

			<thead>
				<tr>
					<th data-options="field:'enterpriseId',checkbox:true"></th>
					<th data-options="field:'enterpriseName',width:100">企业名称</th>
					<th data-options="field:'telephone',width:100">企业固定电话</th>
					<th data-options="field:'email',width:100">企业邮箱</th>
					<th data-options="field:'postCode',width:100">邮政编码</th>
					<th data-options="field:'enterpriseAddress',width:100">企业地址</th>
					<th data-options="field:'adminName',width:100">法人姓名</th>
					<th data-options="field:'adminIdCard',width:100">法人身份证号</th>
					<th
						data-options="field:'status',width:100,formatter:statusFormatter">企业认证状态</th>
				</tr>
			</thead>
		</table>
	</div>






</body>
<script type="text/javascript">

var statusFormatter = function(value, row, index) {
	if (value == 3) {
		return "待审核";
	} else if (value == 1) {
		return "认证成功";
	} else if (value == 2){
		return "认证驳回";
	}
}
	
	
	$.extend($.fn.validatebox.defaults.rules,{
		
		checkEnterprise:{
	    	validator : function(value, param) {      
	    		var enterprisename = $("#enterpriseName").val().trim();            
	    		console.log(enterprisename);
	    		var msg;
	    		  $.ajax({               
	    		     type : 'post',                
	    		    async : false,                
	    		    url : _basePath+"/enterprise/checkEnterprise.do",                
	    		    data : { "enterpriseName" : enterprisename},                
	    		    success : function(data) {                   
	    		    		msg =data.msg ;                
	    		    	}            
	    		     });
	    		   var enterpriseId = $('#enterpriseId').val();
	    		   if(enterpriseId > 0 ){
	    			   return  true;
	    		   }
					if(msg == '1000'){
						return true;
					}else{
						return false;
					}    
	    	},
	        message: "企业名已被占用."
	    },
	    enterpriseLength:{
	    	validator : function(value, param) {  
	    		var min = param[0];
	    		var max = param[1];
	    		if(value.length >= min && value.length <= max){
	    			return true;
	    		}else{
	    			return false;
	    		}
	    		return value.length >= param[0];
	    	},
	        message: "用户名需由中文、字母或数字组成，4-20位."
	    }
	});

	function doDelete(){
		
		var rows = $('#enterprise_list').datagrid('getSelections');
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要删除的对象!', 'info');
			return;
		}
		
		$.messager.confirm('提示', '确认要删除所选数据？', function(r){
			if (r){
			$.post(_basePath+"/enterprise/delete.do", { "enterpriseId":rows[0].enterpriseId },
				function(result){
					$.messager.alert('提示',result.data.msg+'！！！','info');
				 	$('#enterprise_list').datagrid("reload");
				});
			}
		});
	}

	$(function(){
		// 树
		 $('#enterprise_list').datagrid({
			url:'<%=path%>/enterprise/query.do',
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

	
	function searchFunc() {
		$('#enterprise_list').datagrid('load',{  
			enterpriseName:$('#searchEnterpriseName').val(),
		});
	}
	
	function toAddOrder(){
		var rows = $('#enterprise_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		editOrderTab("添加订单", "/enterprise/toAddOrder.do",rows[0].enterpriseId);
	}
	
	function editOrderTab(subtitle, url,enterpriseId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,enterpriseId),
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
	
	function toAddPIN_SD(){
		var rows = $('#enterprise_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		editPIN_SDTab("添加企业PIN/SD码", "/enterprise/toAddPIN_SD.do",rows[0].enterpriseId);
	}
	
	function editPIN_SDTab(subtitle, url,enterpriseId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,enterpriseId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "添加企业PIN/SD码选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	
	function toAddtype(){
		addEnterpriseTab("添加企业", "/enterprise/addEnterprise.do");
	}
	function addEnterpriseTab(subtitle, url) {
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
	

	
	function approveEnterpriseTab(subtitle, url,enterpriseId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,enterpriseId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
			
		} else {
			$.messager.alert('提示', "查看/审核企业信息选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	
	function searchPersonTab(subtitle, url,enterpriseId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,enterpriseId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
			
		} else {
			$.messager.alert('提示', "查看企业员工信息选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	function editEnterpriseTab(subtitle, url,enterpriseId) {
		var jq = top.jQuery;
		var bashpath=getRootPath_web();
		if (!jq('#tabs',window.top.document).tabs('exists', subtitle)) {
			jq('#tabs',window.top.document).tabs('add', {
				title : subtitle,
				content : createFrame3(bashpath+url,enterpriseId),
				closable : true,
				width : jq('#mainPanle').width() - 10,
				height : jq('#mainPanle').height() - 26
			});
		} else {
			$.messager.alert('提示', "修改企业信息选项卡已存在，请关闭后再选择", 'info');
			//jq('#tabs',window.top.document).tabs('select', subtitle);
		}
		tabClose();
	}
	function createFrame3(url,enterpriseId) {
		var iframeId = "${iframeId}";
		url = url + "?id=" + iframeId + "&enterpriseId=" + enterpriseId;
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
		return s;
	}
	function toModify(){
		var rows = $('#enterprise_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		editEnterpriseTab("修改企业信息", "/enterprise/toModify.do",rows[0].enterpriseId);
	}
	
	function toSearchPerson(){
		var rows = $('#enterprise_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		searchPersonTab("查看企业员工", "/enterprise/toSearchPerson.do",rows[0].enterpriseId);
	}
	
	function toApprove(){
		var rows = $('#enterprise_list').datagrid('getSelections');
		var parm = "";
		
		//判断是否选择行
		if (!rows || rows.length != 1) {
			$.messager.alert('提示', '请选择一条要编缉的对象!', 'info');
			return;
		}
		approveEnterpriseTab("查看/审核企业信息", "/enterprise/toApprove.do",rows[0].enterpriseId);
	}
			
	function allocation() {
		$
				.ajax({
					async : false, //请勿改成异步，下面有些程序依赖此请数据
					type : "POST",
					data : {
						userId : $('#selectUserId').val(),
						roleId : $('input[name="customerId"]:checked').val()
					},
					url : "<%=path%>/custom/allocation.do",
			dataType : 'json',
			success : function(json) {
				$.messager.alert('提示', '保存成功', 'info');
				$('#custom_list').datagrid("reload");
				$('#rolesdlg').dialog('close');
			},
			complete : function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
</script>
</html>