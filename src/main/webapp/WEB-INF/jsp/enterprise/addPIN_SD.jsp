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
			<form id="fm" method="post">
				<input type='hidden' name='enterpriseId' id="enterpriseId" value='' />
				<%-- <table border="0">

					<tr>	
						<td>
							<label>企业PIN码:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="PIN" name="PIN" value="${enterprise.PIN }"						
								missingMessage="企业PIN码不能为空"  data-options="required:true,validType:['checkPIN','PINLength[0,25]']" ></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>企业SD码:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="SD" name="SD" value="${enterprise.SD }"
								missingMessage="企业SD码不能为空"  data-options="required:true,validType:['checkSD','SDLength[0,25]']" ></input>
						</td>
					</tr>
					 
				</table> --%>
			</form>
			<div id="dlg-buttons">
				<button onclick="savePIN_SD()" class="ui-button">
						<span class="button">生成一组新的PIN/SD码</span></button>
			</div>
	</div>
</body>
<script type="text/javascript">


/* 	
$.extend($.fn.validatebox.defaults.rules,{
	
	checkPIN:{
    	validator : function(value, param) {      
    		var PIN = $("#PIN").val().trim();            
    		console.log(PIN);
    		var msg;
    		  $.ajax({               
    		     type : 'post',                
    		    async : false,                
    		    url : _basePath+"/enterprise/checkPIN.do",                
    		    data : { "PIN" : PIN},                
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
        message: "PIN码已被占用."
    },
    SDLength:{
    	validator : function(value, param) {  
    		var reg= /^[A-Za-z0-9]{0,25}$/;
    		var min = param[0];
    		var max = param[1];
    		if(value.length >= min && value.length <= max && reg.test(value)){
    			return true;
    		}else{
    			return false;
    		}
    		return value.length >= param[0];
    	},
        message: "SD码必须在25位之内,由数字或字母组成."
    },
    PINLength:{
    	validator : function(value, param) {  
    		var reg= /^[A-Za-z0-9]{0,25}$/;
    		var min = param[0];
    		var max = param[1];
    		if(value.length >= min && value.length <= max && reg.test(value)){
    			return true;
    		}else{
    			return false;
    		}
    		return value.length >= param[0];
    	},
        message: "PIN码必须在25位之内，由数字或字母组成."
    }
});
 */
	
	function savePIN_SD(){
	 
	 $.messager.confirm('提示', '确认要生成一组新的PIN/SD码？', function(r){
			if (r){
				
		var operateUrl = "";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		var enterpriseId = "${enterprise.enterpriseId}";
		operateUrl = "<%=path%>/enterprise/addPIN_SD.do?enterpriseId=" + enterpriseId;
		
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
				jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
					jtop('#tabs',window.top.document).tabs('close',"添加企业PIN/SD码");
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
			}
		});
	}
	
</script>
</html>