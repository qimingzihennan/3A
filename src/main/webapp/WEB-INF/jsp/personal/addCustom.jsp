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
				<input type='hidden' name='id' id="id" value='${personal.id }' />
				<table border="0">
				
				
					
					<tr>	
						<td>
							<label>用户姓名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="customerName" name="customerName" value="${personal.customerName }"
								missingMessage="用户名必须填写"  data-options="required:true,validType:['checkUser','userLength[4,16]']" ></input>
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>用户登录名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="loginCode" name="loginCode" value="${register.loginCode }"
								missingMessage="用户登录名必须填写"  data-options="required:true,validType:['checkCName','userLength[4,16]']" ></input>
							<input id="loginType" name="loginType" value="${register.loginType }" type="hidden">
							<input id="registerId" name="registerId" value="${register.registerId }" type="hidden">
							<c:if test="${personal != null }">
							<input id="status" name="status" type="hidden" value="${personal.status }">
							</c:if>
							<c:if test="${personal == null }">
							<input id="status" name="status" type="hidden" value="1">
							</c:if>
						</td>
					</tr>
					<tr>	
						<td>
							<label>移动电话:</label>
						</td>
						<td>
							<input id="mobile" class="easyui-validatebox"  missingMessage="电话号码不可以为空"
							value="${personal.mobile}" name="mobile" data-options="required:true,validType:['mobileRule','checkMobile']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>邮箱地址:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="email" name="email" 
							value="${personal.email}"  missingMessage="邮箱不可以为空"  
							data-options="required:true,validType:['checkEmails','checkEmail']"></input>
						</td>
					</tr>
		
					<tr>	
						<td>
							<label>身份证号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="idCard" name="idCard" 
							value="${personal.idCard}"  missingMessage="身份证号不可以为空"  
							data-options="required:true,validType:['checkidCards','checkidCard']"></input>
						</td>
					</tr>
					
					<tr>	
						<td>
							<label>地址:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="address" name="address" 
							value="${personal.address}"    ></input>
						</td>
					</tr>
					 <tr>	
						<td>
							<label>邮政编码:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="postcode" name="postcode" 
							value="${personal.postcode}"    ></input>
						</td>
					</tr>
					<!-- 
					<tr>	
						<td>
							<label>数据来源（业务key）:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="bkey" name="bkey" 
							value="${personal.bkey}"    ></input>
						</td>
					</tr>
					 -->
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
	        message: "输入手机号码格局不正确.",
	        
	    },
	    checkEmails:{
	    	validator: function (value) {
	    		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	            return reg.test(value);
	        },
	        message: "输入邮箱格局不正确.",
	    },
	    checkidCards:{
	    	validator: function (value) {
	    		var reg =  /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	            return reg.test(value);
	        },
	        message: "输入身份证号码格局不正确.",
	    },
	    checkEmail:{
	    	validator : function(value, param) {      
	    		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	    		var email = $("#email").val().trim();            
	    		console.log(email);
	    		if(reg.test(value)){
	    	    	var msg;
	    	    		$.ajax({               
	    	    		type : 'post',                
	    	    		async : false,                
	    	    		url : _basePath+"/personal/checkEmail.do",                
	    	    		data : { "email" : email},                
	    	    		success : function(data) {                   
	    	    		   msg =data.msg ;                
	    	    		}            
	    	    	});
	    	    	var userId = $('#id').val();
	    	    	if(userId > 0 ){
	    	    		return  true;
	    	    	}
	    			if(msg == '1000'){
	    				return true;
	    			}else{
	    				return false;
	    			}    
	    		}
	    	
	    	},
	        message: "邮箱已被占用."
	    }, 
	    
	    checkCName:{
	    	validator : function(value, param) {      
	    		
	    		var username = $("#loginCode").val().trim();
	    		var type = $("#loginType").val().trim();
	    		if(type == null || type == ""){
	    			type = 1;
	    		}
	    		console.log(username);
	    		var msg;
	    		  $.ajax({               
	    		     type : 'post',                
	    		    async : false,                
	    		    url : _basePath+"/personal/checkCName.do",                
	    		    data : { "loginCode" : username},                
	    		    success : function(data) {                   
	    		    		msg =data.msg ;                
	    		    	}            
	    		     });
	    		   var userId = $('#id').val();
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
	    checkMobile:{
	    	validator : function(value, param) { 
	    		
	    		var reg =  /^(13[0-9]{9})|(18[0-9]{9})|(15[0-9][0-9]{8})$/;
	    		var mobile = $("#mobile").val().trim();            
	    		if(reg.test(value)){
	    			console.log(mobile);
		    		var msg;
		    		  $.ajax({               
		    		     type : 'post',                
		    		    async : false,                
		    		    url : _basePath+"/personal/checkMobile.do",                
		    		    data : { "mobile" : mobile},                
		    		    success : function(data) {                   
		    		    		msg =data.msg ;                
		    		    	}            
		    		     });
		    		   var userId = $('#id').val();
		    		   if(userId > 0 ){
		    			   return  true;
		    		   }
						if(msg == '1000'){
							return true;
						}else{
							return false;
						}    
	    		}
	    		
	    	},
	    	message: "手机号码已被占用."
	        
	    },
	    checkidCard:{
	    	validator : function(value, param) { 
	    		var reg =  /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	    		var idCard = $("#idCard").val().trim();            
	    		if(reg.test(value)){
	    			console.log(mobile);
		    		var msg;
		    		  $.ajax({               
		    		     type : 'post',                
		    		    async : false,                
		    		    url : _basePath+"/personal/checkIdCard.do",                
		    		    data : { "idCard" : idCard},                
		    		    success : function(data) {                   
		    		    		msg =data.msg ;                
		    		    	}            
		    		     });
		    		   var userId = $('#id').val();
		    		   if(userId > 0 ){
		    			   return  true;
		    		   }
						if(msg == '1000'){
							return true;
						}else{
							return false;
						}    
						
	    		}
	    		
	    	},
	    	message: "身份证号已被占用."
	        
	    }
	    /*
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
	    }*/
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
		var rows = jq('#custom_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/personal/save.do";
		}else{
			var id = "${personal.id}";
			if(id == null || id == ""){
				operateUrl = "<%=path%>/personal/save.do";
			}else{
				operateUrl = "<%=path%>/personal/modify.do?id=" + id;
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
				jq('#custom_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				if (!rows || rows.length != 1) {
					jtop('#tabs',window.top.document).tabs('close',"添加个人用户");
				}else{
					if(id == null || id == ""){
						jtop('#tabs',window.top.document).tabs('close',"添加个人用户");
					}else{
						jtop('#tabs',window.top.document).tabs('close',"修改个人用户");
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