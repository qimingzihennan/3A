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
				<input type='hidden' name='enterpriseId' id="enterpriseId" value='' />
				<table border="0">
				<tr>	
						<td>
							<label>企业名称:</label>
						</td>
						<td>
							<c:choose>
									<c:when test="${enterprise.enterpriseId == null}"><input class="easyui-validatebox" id="enterpriseName" name="enterpriseName" value="${enterprise.enterpriseName }"
								missingMessage="企业名必须填写"  data-options="required:true,validType:['checkEnterprise','enterpriseLength[1,30]']" ></input></c:when>
									<c:otherwise><input class="easyui-validatebox" id="enterpriseName" name="enterpriseName" value="${enterprise.enterpriseName }" disabled="true"></input>
									</c:otherwise>
								</c:choose>
							
						</td>
					</tr>
					
					<tr>	
						<td>
							<label>营业执照登记号:</label>
						</td>
						<td>
							<c:choose>
									<c:when test="${enterprise.businessNumber == null}"><input class="easyui-validatebox" id="businessNumber" name="businessNumber" value="${enterprise.businessNumber }"
								  data-options="required:false,validType:['businessNumberRule','BusinessLength[15,15]']" ></input></c:when>
									<c:otherwise><input class="easyui-validatebox" id="businessNumber" name="businessNumber" value="${enterprise.businessNumber }" disabled="true"></input>
									</c:otherwise>
								</c:choose>
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>组织机构代码证号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="orgCertificate" name="orgCertificate" 
								value="${enterprise.orgCertificate }" ></input>	
						</td>
					</tr>
					<tr>	
						<td>
							<label>税务登记证号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="trCertificate" name="trCertificate" 
								value="${enterprise.trCertificate }" ></input>	
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>统一社会信用代码:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="uscCode" name="uscCode" value="${enterprise.uscCode }"></input>
									
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>企业地址:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="enterpriseAddress" name="enterpriseAddress" value="${enterprise.enterpriseAddress }"
								missingMessage="地址必须填写" data-options="required:true,validType:['addressLength[0,50]']" ></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>固定电话:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="telephone" name="telephone" value="${enterprise.telephone }"
							 missingMessage="固定电话必须填写" data-options="required:true,validType:['checktelephone']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>企业邮箱:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="email" name="email" value="${enterprise.email }"
							 missingMessage="企业邮箱必须填写" data-options="required:true,validType:['checkemail']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>邮政编码:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="postCode" name="postCode" value="${enterprise.postCode }"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>法人姓名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="adminName" name="adminName" value="${enterprise.adminName }"
								missingMessage="企业管理员姓名必须填写" data-options="required:true,validType:['adminLength[1,20]']"></input>
						</td>
					</tr>

					<tr>	
						<td>
							<label>法人身份证号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="adminIdCard" name="adminIdCard" value="${enterprise.adminIdCard }"
								missingMessage="管理员身份证号必须填写" data-options="required:true,validType:['checkAdminIdCard']"></input>
						</td>
					</tr>
					<tr>	
						<td>
							<label>代理人姓名:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="agentName" name="agentName" 
								value="${enterprise.agentName }" ></input>	
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>代理人身份证号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="agentIdCard" name="agentIdCard" 
								value="${enterprise.agentIdCard }" ></input>	
							
						</td>
					</tr>
					<tr>	
						<td>
							<label>代理人手机号:</label>
						</td>
						<td>
							<input class="easyui-validatebox" id="agentMobile" name="agentMobile" 
								value="${enterprise.agentMobile }" ></input>	
							
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

window.onload=function(){ 
	//要初始化的东西 
	var enterprisename = $("#enterpriseName").val();
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
	        message: "企业名需要在30位之内."
	    },
    businessNumberRule:{
    	validator : function(value, param) {      
    		var businessNumber = $("#businessNumber").val().trim();            
    		console.log(businessNumber);
    		var msg;
    		  $.ajax({               
    		     type : 'post',                
    		    async : false,                
    		    url : _basePath+"/enterprise/checkBusinessNumber.do",                
    		    data : { "businessNumber" : businessNumber},                
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
        message: "营业执照登记号已被占用."
    },
    BusinessLength: {
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
        message: "营业执照号为15位."
    },
    checkuscCode:{
    	validator : function(value, param) {
    		
   		 if(!value || !/[0-9A-Z]{18}/.test(value)){
   			 $.fn.validatebox.defaults.rules.checkuscCode.message ="格式错误，由18位数字或大写字母组成.";
   			 return false;
   		 }else {
   			 var uscCode = $("#uscCode").val().trim();   
   			 var eenterpriseId = "${enterprise.enterpriseId}";
   	    		console.log(uscCode);
   	    		var msg;
   	    		  $.ajax({               
   	    		     type : 'post',                
   	    		    async : false,                
   	    		    url : _basePath+"/enterprise/checkuscCode.do",                
   	    		    data : { "uscCode" : uscCode },                
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
   						$.fn.validatebox.defaults.rules.checkuscCode.message ="统一社会信用代码被占用";
   						return false;
   					}
   		 }	 
   	},
       message: ""
   },
   checktelephone:{
   	validator : function(value, param) {  
  		
  			 var telephone = $("#telephone").val().trim();   
  			 var eenterpriseId = "${enterprise.enterpriseId}";
  	    		console.log(telephone);
  	    		var msg;
  	    		  $.ajax({               
  	    		     type : 'post',                
  	    		    async : false,                
  	    		    url : _basePath+"/enterprise/checktelephone.do",                
  	    		    data : { "telephone" : telephone,"enterpriseId":eenterpriseId},                
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
  						$.fn.validatebox.defaults.rules.checktelephone.message ="固定电话被占用";
  						return false;
  					}
  		 	 
  	},
      message: ""
  },
  checkemail:{
	   	validator : function(value, param) {  
	  			 var email = $("#email").val().trim();   
	  			 var eenterpriseId = "${enterprise.enterpriseId}";
	  	    		console.log(email);
	  	    		var msg;
	  	    		  $.ajax({               
	  	    		     type : 'post',                
	  	    		    async : false,                
	  	    		    url : _basePath+"/enterprise/checkemail.do",                
	  	    		    data : { "email" : email,"enterpriseId":eenterpriseId },                
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
	  						$.fn.validatebox.defaults.rules.checkemail.message ="邮箱被占用";
	  						return false;
	  					}
	  		 	 
	  	},
	      message: ""
	  },
    addressLength: {
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
        message: "地址在50位之内."
    },
    adminLength: {
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
        message: "管理员姓名在20位之内."
    },
    checkAdminIdCard:{
    	validator : function(value, param) {  
    		 if(!value || !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value)){
    			 $.fn.validatebox.defaults.rules.checkAdminIdCard.message ="身份证号格式错误，应为15位数字或18位数字或17位数字+X.";
    			 return false;
    		 }else {
    			 var adminIdCard = $("#adminIdCard").val().trim();   
    			 var eenterpriseId = "${enterprise.enterpriseId}";
    	    		console.log(adminIdCard);
    	    		var msg;
    	    		  $.ajax({               
    	    		     type : 'post',                
    	    		    async : false,                
    	    		    url : _basePath+"/enterprise/checkAdminIdCard.do",                
    	    		    data : { "adminIdCard" : adminIdCard,"enterpriseId" : eenterpriseId},                
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
    						$.fn.validatebox.defaults.rules.checkAdminIdCard.message ="身份证号被占用";
    						return false;
    					}
    		 }	 
    	},
        message: ""
    }
    
});

	function toAddtype(){
		$('#fm').form('clear');
		$('#dlg').dialog('open').dialog('setTitle','后端企业管理');
	}
	
	function saveCrmUser(){
		
		var operateUrl = "";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		//判断是否选择行
		if (!rows || rows.length != 1) {
			operateUrl = "<%=path%>/enterprise/save.do";
		}else{
			var enterpriseId = "${enterprise.enterpriseId}";
			if(enterpriseId == null || enterpriseId == ""){
				operateUrl = "<%=path%>/enterprise/save.do";
			}else{
				operateUrl = "<%=path%>/enterprise/modify.do?enterpriseId=" + enterpriseId;
			}
			
		}
		console.log(operateUrl);
		$('#fm').form('submit',{
			url:operateUrl ,
			onSubmit:function(param){
				
				var ucode = $("#uscCode").val().trim();
	    		var bcode = $("#businessNumber").val().trim();  
	    		var uisempty = ucode==null||ucode=="";
	    		var bisempty = bcode==null||bcode=="";
	    		if(uisempty&&bisempty){
	    			alert("营业执照号和统一社会信用代码至少填写一个.");
	      			 return false;
	    		}
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
				if (!rows || rows.length != 1) {
					jtop('#tabs',window.top.document).tabs('close',"添加企业");
				}else{
					if(enterpriseId == null || enterpriseId == ""){
						jtop('#tabs',window.top.document).tabs('close',"添加企业");
					}else{
						jtop('#tabs',window.top.document).tabs('close',"修改企业信息");
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