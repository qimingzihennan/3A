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
<body class="easyui-layout">
	<div id="dlg"  align="center" style="height:470px;padding-top:50px">
			<form id="fm" method="post" autocomplete="off">
				<input type='hidden' name='userId' id="userId" value='' />
				<table border="0">
					<tr>	
						<td>
							<label>请输入新密码:&nbsp;</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_pwd" name="password" type="password" 
							value="" required="true" missingMessage="密码不可以为空" data-options="required:true,validType:['password','length[6,16]']" ></input>
						</td>
					</tr>
				</table>
				
				<table>
					<tr>	
						<td>
							<label>请再输入一次:&nbsp;</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="edit_pwd_2" name="password_2" type="password" 
							value="" required="true" missingMessage="密码不可以为空" ></input>
						</td>
					</tr>
				</table>
			</form>
			<div id="dlg-buttons">
				<button onclick="save()" class="ui-button">
						<span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span></button>
			</div>
	</div>
</body>
<script type="text/javascript">
	
	var temp;
	
	function save(){
		
			

		var operateUrl = "";
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
	
		
		
		
		
			var userId = "${user.userId}";
			operateUrl = "<%=path%>/user/modifyPwd.do?userId=" + userId;
		
		console.log(operateUrl);
		$('#fm').form('submit',{
			url:operateUrl ,
			onSubmit:function(param){
				return $(this).form('validate')&&test()&&check();
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

				jtop('#tabs',window.top.document).tabs('close',"修改用户密码");
				jtop('#tabs',window.top.document).tabs('close',"修改密码");
				
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
	
	function test(){
		var password = document.getElementById("edit_pwd");
		var passwordConfirm = document.getElementById("edit_pwd_2");
		if(password.value != passwordConfirm.value){
			alert("对不起，您2次输入的密码不一致,请重新输入");
			return false;
		}else {
			return true;
		}
	}
	
	 function check(){
		var userId = "${user.userId}";
		
		operateUrl = "<%=path%>/user/checkpwd.do?userId=" + userId;
		$
		.ajax({
			type : "POST",
			async:false,
			data : {
				password : $('#edit_pwd').val(),
				userId : $('input[name="userId"]:checked').val()
			},
			url : operateUrl,
			dataType : 'text',
			success : function(data) {
				if(data=="equal"){
					temp = 1;
					$.messager.alert('提示', '与原密码相同，请重新设置', 'info');					
				}
			}
			
		});
		
		if(temp==1){
			return false;
		}
		
		return true;
	}
	
	
	
</script>
</html>