<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<style type="text/css">
tr{height:40px;}
.easyui-combobox{width:175px;}
.ui-button{background:#3cae56;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.button{color:#ffffff;font-size:14px;}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">
	<div id="dlg"  align="center" style="height:470px;padding-top:50px">
			<form id="fm" method="post">
				<input type='hidden' name='userId' id="userId" value='' />
				<table border="0">
					<tr>	
						<td>
							<label>用户名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_userName" name="userName" value="${user.userName}"
								missingMessage="用户名必须填写"  data-options="required:true,validType:['checkUser','userLength[4,16]']" ></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户姓名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_realName" name="realName" required="true"
								value="${user.realName}" missingMessage="姓名必须填写"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>性别:</label>
						</td>
						<td>
							<select class="easyui-combobox" id="edit_sex" name="sex">
								<option id="sexMale" value="1" <c:if test="${user.sex=='1'}">selected</c:if>>男</option>
								<option id="sexMaleFemale" value="2" <c:if test="${user.sex=='2'}">selected</c:if>>女</option>
							</select>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户移动电话:</label>
						</td>
						<td>
							<input id="edit_mobile" class="easyui-validatebox"  missingMessage="电话号码不可以为空" 
							value="${user.mobile}" name="mobile" data-options="required:true,validType:['mobileRule','length[11,11]']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户名邮箱地址:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_email" name="email" validtype="email" 
							value="${user.email}" required="true" missingMessage="邮箱不可以为空" invalidMessage="邮箱格式不正确" ></input>
						</td>
					</tr>
				</table>
			</form>
			<div id="dlg-buttons">
				<button onclick="saveCrmUser()" class="ui-button">
						<span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span></button>
			</div>
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
	    		var userId = $("#userId").val();
	    		if (userId !== null || userId !== undefined || userId !== '') {
	    			return true;
	    		} 
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

	function toAddtype(){
		$('#fm').form('clear');
		$('#dlg').dialog('open').dialog('setTitle','后端用户管理');
	}
	
	function saveCrmUser(){
		var operateUrl = "";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#user_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/user/save.do";
		}else{
			var userId = "${user.userId}";
			if(userId == null || userId == ""){
				operateUrl = "<%=path%>/user/save.do";
			}else{
				operateUrl = "<%=path%>/user/modify.do?userId=" + userId;
			}
			
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
				var obj = eval("("+data+")");
				alert(obj.msg);
				$('#fm').form('clear');
				jq('#user_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				if (!rows || rows.length != 1) {
					jtop('#tabs',window.top.document).tabs('close',"添加用户");
				}else{
					if(userId == null || userId == ""){
						jtop('#tabs',window.top.document).tabs('close',"添加用户");
					}else{
						jtop('#tabs',window.top.document).tabs('close',"修改用户");
					}
					
				}
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
	
</script>
</html>