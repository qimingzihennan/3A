<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>套餐管理</title>
<style type="text/css">
tr{height:40px;}
.ui-button{background:#3cae56;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.button{color:#ffffff;font-size:14px;}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">


<div id="dlg" align="center" style="height:400px;padding:10px 20px;">
	<form action="" id="fm" method="post">
		<input type="hidden" name="id" id="id">
		<input type="hidden" name="status" id="status">
		<table border="0" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td width="90px;">业务模块：</td>
				<td>
					<select class="col-sm-3 form-control" id="bkey"
							name="bkey">
							<c:forEach items="${business }" var="b">
								<option value="${b.bkey }">${b.businessName }</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tbody id="rolesList">
			
			</tbody>
			
			<tr>
				<td width="60px;">计费模式：</td>
				<td>
				<select class="col-sm-3 form-control" id="paidMode"
							name="paidMode">
							<option value="1">次数</option>
							<option value="2">天数</option>
							<option value="3">次数+天数</option>
							<option value="4">储存空间</option>
							<option value="5">储存空间+天数</option>
				</select>
				<!-- 
				<input class="easyui-validatebox" data-options="required:true,validType:['digits','numberMax']" id="number" name="number" style="width: 300px;"></td>
				 -->
			</tr>
			<tr>
				<td width="60px;">套餐类型：</td>
				<td>
					<select class="col-sm-3 form-control" id="coPayType"
							name="coPayType">
						<option value="1">活动套餐</option>
						<option value="2">赠送套餐</option>
						<option value="3">标准套餐</option>
					</select>
				</td>
			</tr>

			<tr>
				<td width="60px;">套餐名称：</td><td><input class="easyui-validatebox" data-options="required:true,validType:'nameLength[1,25]'"   id="name" name="name" style="width: 300px;"></td>
			</tr>
			<tr id="ci">
				<td width="60px;">次数：</td><td><input class="easyui-validatebox" data-options="" id="number" name="number" style="width: 300px;">次
				<div><font color="red" id="cinumber"></font></div>
				</td>
				
			</tr>
			<tr >
				<td width="60px;">价格：</td><td><input class="easyui-validatebox" data-options="required:true,validType:['number','priceMax']" id="price" name="price" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">类型：</td><td><input type="radio" checked="true"  name="coType" value="1">个人<input type="radio" name="coType" value="2" >企业</td>
			</tr>
			<tr id="tian">
				<td width="60px;">天数：</td><td><input class="easyui-validatebox"   id="days" name="days" style="width: 300px;">天
												<div><font color="red" id="tiandays"></font></div>
											</td>
			</tr>
			<tr id="chu">
				<td width="60px;">储存空间：</td><td><input class="easyui-validatebox"   id="space" name="space" style="width: 300px;">G
												<div><font color="red" id="chuspace"></font></div>
											</td>
			</tr>
			<tr>
				<td width="60px;">套餐内容：</td><td><input class="easyui-textbox" name="content" data-options="multiline:true" value="" style="width:300px;height:100px"></td>
			</tr>
																	
		</table>
	</form>
	<div align=center style="margin-top:20px;"><button onclick="saveCrmUser()" class="ui-button">
		    <span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span></button></div>
	</div>



</body>
<script type="text/javascript">
window.onload=function(){ 
	//要初始化的东西 
	var type = $("#paidMode").val().trim();
	var bkey = $("#bkey").val().trim();
    $.ajax({
		type: "POST",
		dataType:"JSON",
		url:"<%=path%>/combo/findByBkey.do",
		data:{bkey:bkey},
		beforeSend:ajaxLoading,
		async: true,
		error: function(request) {
			$.messager.alert('提示', request.responseJSON.reason, 'error');
		},
		success: function(data) {
			var obj = eval(data.bef);
			for (var j = 0; j < obj.length; j++) {
				$("<tr><td width='60px'>"+obj[j].bEFName+"</td><td>"+"<input class='easyui-validatebox' data-options='required:true,validType:nameLength[1,25]'"+" id='bEFValue' name='bEFValue' style='width: 300px')</td>").appendTo("#rolesList");
				$("<input type='hidden'  id='eName' name='eName'value='"+obj[j].eName+"'/>)").appendTo("#rolesList");
				$("<input type='hidden'  id='bEFName' name='bEFName'value='"+obj[j].bEFName+"'/>)").appendTo("#rolesList");
			}
			
		},
		complete:function(){
			//关闭覆盖层 loading
		   	$(".datagrid-mask").remove(); 
		   	$(".datagrid-mask-msg").remove();  
		}
	});
   
} 






$(function(){   
    $('#bkey').click(function(){   
    	$("#rolesList").html("");
        var bkey = $("#bkey").val().trim();
        $.ajax({
			type: "POST",
			dataType:"JSON",
			url:"<%=path%>/combo/findByBkey.do",
			data:{bkey:bkey},
			beforeSend:ajaxLoading,
			async: true,
			error: function(request) {
				$.messager.alert('提示', request.responseJSON.reason, 'error');
			},
			success: function(data) {
				var obj = eval(data.bef);
				for (var j = 0; j < obj.length; j++) {
					$("<tr><td width='60px'>"+obj[j].bEFName+"</td><td>"+"<input class='easyui-validatebox' data-options='required:true,validType:nameLength[1,25]'"+" id='bEFValue' name='bEFValue'style='width: 300px')</td>").appendTo("#rolesList");
					$("<input type='hidden'  id='eName' name='eName' value='"+obj[j].eName+"'/>)").appendTo("#rolesList");
					$("<input type='hidden'  id='bEFName' name='bEFName' value='"+obj[j].bEFName+"'/>)").appendTo("#rolesList");
				}
			},
			complete:function(){
				//关闭覆盖层 loading
			   	$(".datagrid-mask").remove(); 
			   	$(".datagrid-mask-msg").remove();  
			}
		});
 
     });
})



$.extend($.fn.validatebox.defaults.rules, {
    number: {
		validator: function(value,param){
			return !isNaN(value);
		},
		message: '输入合法数字.'
    },
    digits: {
		validator: function(value,param){
			return Math.floor(value) == value;
		},
		message: '必须整数.'
    },
    nameLength:{
    	validator : function(value, param) {  
    		var min = param[0];
    		var max = param[1];
    		if(value.length >= min && value.length <= max){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "套餐名称长度，1-25位."
    },
    numberMax:{
    	validator : function(value, param) {  
    		if(value <= 999999999){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "最大值999999999."
    },
    priceMax:{
    	validator : function(value, param) {  
    		var reg = /^[0-9]+(\.[0-9]{2})?$/;
    		return reg.test(value);
    		if(value*100 <= 9999999999){
				return true;
    		}else{
    			return false;
    		}
    	},
        message: "最大值99999999.99,只能保留两位小数."    	
    }
});

function validateNumAndDays() {
	$("#cinumber").html("");
	$("#tiandays").html("");
	$("#chuspace").html("");
	var type = $("#paidMode").val().trim();
	var ci = $("#number").val();
	var tian = $("#days").val();
	var chu = $("#space").val();
	var reg =  /^[0-9]*$/;
	if(type==1){
		if(ci == "" || ci == null){
			$("#cinumber").html("次数不能为空");
			return false;
		}else{
			if(!reg.test(ci)){
				$("#cinumber").html("次数必须为整数");
				return false;
			}
		}
		
	}
	if(type==2){
		if(tian == "" || tian == null){
			$("#tiandays").html("天数不能为空");
			return false;
		}else{
			if(!reg.test(tian)){
				$("#tiandays").html("天数必须为整数");
				return false;
			}
		}
	}
	if(type==3){
		if(ci == "" || ci == null){
			$("#cinumber").html("次数不能为空");
			return false;
		}else{
			if(!reg.test(ci)){
				$("#cinumber").html("次数必须为整数");
				return false;
			}
			
		}
		if(tian == "" || tian == null){
			$("#tiandays").html("天数不能为空");
			return false;
		}else{
			if(!reg.test(tian)){
				$("#tiandays").html("天数必须为整数");
				return false;
			}
		}
	}
	if(type==4){
		if(chu == "" || chu == null){
			$("#chuspace").html("储存空间不能为空");
			return false;
		}else{
			if(!reg.test(chu)){
				$("#chuspace").html("储存空间必须为整数");
				return false;
			}
		}
		
	}
	if(type == 5){
		if(tian == "" || tian == null){
			$("#tiandays").html("天数不能为空");
			return false;
		}else{
			if(!reg.test(tian)){
				$("#tiandays").html("天数必须为整数");
				return false;
			}
		}
		if(chu == "" || chu == null){
			$("#chuspace").html("储存空间不能为空");
			return false;
		}else{
			if(!reg.test(chu)){
				$("#chuspace").html("储存空间必须为整数");
				return false;
			}
		}
	}
	return true;
}

var iframeId = "${iframeId}";
var jq = window.parent.frames[iframeId].jQuery;
var jtop = top.jQuery;
//添加
function saveCrmUser(){
	var result = validateNumAndDays();
	if (!result) {
		return;
	}
	
	var addurl = '<%=path %>/combo/save.do';
	$('#fm').form('submit',{
		url: addurl,
		onSubmit:function(param){
			return $('#fm').form('validate');
		},
		type: "POST",
		beforeSend: ajaxLoading,
		error: function(request) {
			$.messager.alert('提示', request.responseJSON.reason, 'error');
		},
		success: function(data) {
			alert('保存成功');
			$('#fm').form('clear');
		   /* 	$('#datas_list').datagrid("reload");
			$('#dlg').dialog('close');  */
			jq('#datas_list',window.parent.frames[iframeId].document).datagrid("reload");
			jtop('#tabs',window.top.document).tabs('close',"新增套餐");
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