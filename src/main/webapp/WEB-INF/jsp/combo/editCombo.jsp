<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>套餐管理</title>
<style type="text/css">
.ui-button{background:#3cae56;border:0;width:88px;height:38px;border-radius:4px;margin-top:15px;margin-left:60px;}
.button{color:#ffffff;font-size:14px;}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">
<div data-options="region:'center'" style="background: #eee; overflow-y:hidden">

<div id="dlg" align="center" style="height:400px;padding:10px 20px;">
	<form action="" id="fm" method="post">
		<input type="hidden" name="id" id="id" value="${combo.id}">
		<input type="hidden" name="status" id="status" value="${combo.status}">
		<table border="0" style="border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td width="60px;">业务模块：</td>
				<td>
					<select class="col-sm-3 form-control" id="bkey"
							name="bkey" disabled>
							<c:forEach items="${business }" var="b">
								<option value="${b.bkey }" <c:if test="${combo.bkey == b.bkey }">selected</c:if>>${b.businessName }</option>
							</c:forEach>
					</select>
					
				</td>
			</tr>
			<c:if test="${cef != null }">
			<c:forEach items="${cef }" var="c">
			<tr>
				<td width="60px;">${c.bEFName }：</td><td><input class="easyui-validatebox" data-options="required:true,validType:'nameLength[1,25]'" 
				value="${c.bEFValue }"  id="bEFValue" name="bEFValue" style="width: 300px;"></td>
				<input type="hidden" name="eName" id="eName" value="${c.eName}">
			</tr>
			</c:forEach>
			</c:if>
			
			<tr>
				<td width="60px;">计费模式：</td>
				<td>
				<select class="col-sm-3 form-control" id="paidMode"
							name="paidMode" disabled>
								<option value="1" <c:if test="${combo.paidMode == 1}">selected</c:if> >次数</option>
							<option value="2" <c:if test="${combo.paidMode == 2}">selected</c:if>>天数</option>
							<option value="3" <c:if test="${combo.paidMode == 3}">selected</c:if>>次数+天数</option>
							<option value="4" <c:if test="${combo.paidMode == 4}">selected</c:if>>储存空间</option>
							<option value="5" <c:if test="${combo.paidMode == 5}">selected</c:if>>储存空间+天数</option>
				</select>
			</tr>				
			<tr>
				<td width="60px;">套餐名称：</td><td><input class="easyui-validatebox" 
				value="${combo.name}"  id="name" name="name" style="width: 300px;"></td>
			</tr>
			<tr id="ci">
				<td width="60px;">次数：</td><td><input class="easyui-validatebox" 
				value="${combo.number}" id="number" name="number" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">价格：</td><td><input class="easyui-validatebox" data-options="required:true,validType:['number','priceMax']"
				value="${combo.price}"  id="price" name="price" style="width: 300px;"></td>
			</tr>
			<tr>
				<td width="60px;">类型：</td>
				<td><input type="radio" name="coType" value="1" <c:if test="${combo.coType=='1'}">checked=checked</c:if>>个人
				<input type="radio" name="coType" value="2" <c:if test="${combo.coType=='2'}">checked=checked</c:if>>企业</td>
			</tr>
			<tr id="tian">
				<td width="60px;">天数：</td><td><input class="easyui-validatebox"  value="${combo.days}" id="days" name="days" style="width: 300px;">
												<div><font color="red">*按次收费套餐填0</font></div>
											</td>
			</tr>
			<tr id="chu">
				<td width="60px;">储存空间：</td><td><input class="easyui-validatebox"  value="${combo.space}" id="space" name="space" style="width: 300px;">
												<div><font color="red">*单位：G</font></div>
											</td>
			</tr>
			<tr>
				<td width="60px;">套餐内容：</td><td><input class="easyui-textbox" name="content" data-options="multiline:true" value="${combo.content}" style="width:300px;height:100px"></td>
			</tr>															
		</table>
	</form>
	<div align=center style="margin-top:20px;"><button onclick="updateCrmUser()" class="ui-button">
		    <span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span></button></div>
	</div>
</div>
</body>
<script type="text/javascript">



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
var iframeId = "${iframeId}";
var jq = window.parent.frames[iframeId].jQuery;
var jtop = top.jQuery;

//修改
function updateCrmUser(){
	var addurl = '<%=path %>/combo/modify.do';
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
		   	/* $('#datas_list').datagrid("reload");
			$('#dlg').dialog('close');  */
			jq('#datas_list',window.parent.frames[iframeId].document).datagrid("reload");
			jtop('#tabs',window.top.document).tabs('close',"编辑套餐");
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