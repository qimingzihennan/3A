<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<style type="text/css">


.easyui-combobox {
	width: 175px;
}

.ui-button {
	background: #3cae56;
	border: 0;
	width: 88px;
	height: 38px;
	border-radius: 4px;
	margin-top: 15px;
	margin-left: 60px;
}

.button {
	color: #ffffff;
	font-size: 14px;
}
</style>
</head>
<body class="easyui-layout" style="overflow:auto;">
	<div id="dlg" align="center" style="height: 470px; padding-top: 50px">
		<form id="fm" method="post">
			<input type='hidden' name='id' id="id" value='${order.id }' />
			<table border="0">
				<tr>
					<td><label>订单编号:</label></td>
					<td><input class="easyui-textbox" id="edit_orderNO"
						name="orderNO" value="${order.orderNO}" readonly="readonly" disabled></input>
					</td>
				</tr>
				<tr>
					<td><label>业务模块名称:</label></td>
					<td><input class="easyui-textbox" id="edit_businessName"
						value="${order.businessName}" name="businessName"
						readonly="readonly" disabled></input></td>
				</tr>
				<tr>
					<td><label>套餐名称:</label></td>
					<td><input id="edit_comboName" class="easyui-textbox"
						value="${order.comboName}" name="comboName" readonly="readonly" disabled></input></td>
				</tr>
				<tr>
					<td><label>套餐内容:</label></td>
					<td><input id="edit_content" class="easyui-textbox"
						value="${order.content}" name="content" readonly="readonly" disabled
						data-options="multiline:true"></input></td>
				</tr>
				<tr>
					<td><label>实际价格:</label></td>
					<td><input type="text" id="edit_price"
						class="easyui-numberbox" missingMessage="价格不可以为空"
						value="${order.price}" name="price"
						data-options="required:true,min:0,precision:2"></input></td>
				</tr>
				<tr id="ci">
					<td><label>次数:</label></td>
					<td><input type="text" id="edit_number"
						class="easyui-numberbox" value="${order.number}" name="number"
						data-options="min:0,precision:0"></input></td>
				</tr>
				<tr id="tian">
					<td><label>天数:</label></td>
					<td><input id="edit_days" class="easyui-textbox"
						 value="${order.days}" name="days" ></input></td>
				</tr>
				<tr id="chu">
					<td><label>储存空间:</label></td>
					<td><input id="edit_days" class="easyui-textbox"
						 value="${order.space}" name="space" ></input></td>
				</tr>
				
				<tr>
				
				
					<td><label>下单时间:</label></td>
					<td><input id="edit_orderTime" class="easyui-textbox"
						readonly="readonly"  value="<fmt:formatDate value="${order.orderTime}"/> " disabled name="orderTime"
						data-options="formatter:myformatter,parser:myparser"></input></td>
				</tr>
				<tr>
					<td><label>支付时间:</label></td>
					<td><input id="edit_payTime" class="easyui-textbox"
						readonly="readonly" value="<fmt:formatDate value="${order.payTime}"/>" disabled  name="payTime"
						data-options="formatter:myformatter,parser:myparser"></input></td>
				</tr>

				<tr id="kai">
					<td><label>开始时间:</label></td>
					<td><input id="edit_startTime" class="easyui-datebox"
						value="<fmt:formatDate value="${order.startTime}"/>" name="startTime"
						data-options="formatter:myformatter,parser:myparser"></input>
					</td>
				</tr>
				<tr id="jie">
					<td><label>结束时间:</label></td>
					<td><input id="edit_endTime" class="easyui-datebox"
						value="<fmt:formatDate value="${order.endTime}"/>" name="endTime"
						data-options="formatter:myformatter,parser:myparser"></input>
					</td>
				</tr>
				
				<tr>
					<td><label>计费模式:</label></td>
					<td><select class="easyui-combobox" id="edit_paidMode" disabled
						name="paidMode">
							<c:if test="${order.paidMode=='1'}">
								<option>次数</option>
							</c:if>
							<c:if test="${order.paidMode=='2'}">
								<option>天数</option>
							</c:if>
							<c:if test="${order.paidMode=='3'}">
								<option>次数+天数</option>
							</c:if>
							<c:if test="${order.paidMode=='4'}">
								<option>储存空间</option>
							</c:if>
							<c:if test="${order.paidMode=='5'}">
								<option>储存空间+天数</option>
							</c:if>
					</select></td>
				</tr>
			</table>
		</form>
		<div id="dlg-buttons">
			<button onclick="saveOrder()" class="ui-button">
				<span class="button">保&nbsp;&nbsp;&nbsp;&nbsp;存</span>
			</button>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
window.onload=function(){ 
	var type = $("#edit_paidMode").val().trim();
	if(type == "天数"){
		$("#ci").hide();
	}
	if(type == "次数"){
		$("#kai").hide();
		$("#jie").hide();
		$("#tian").hide();
	}
}
$("#edit_startTime").datebox({ 
	 onSelect : function(beginDate){ 
	  $('#edit_endTime').datebox().datebox('calendar').calendar({ 
	   validator: function(date){ 
	    return beginDate<=date;// 
	   } 
	  }); 
	 } 
});
function myformatter(value) {
	var date = new Date(value);
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var M = date.getMinutes();
	var s = date.getSeconds();
	
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
			+ (d < 10 ? ('0' + d) : d) ;
}
function myparser(s) {
	if (!s)
		return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}

	function validateNumAndDays() {
		var paidMode = $("#edit_paidMode").val();
		var number = $("#edit_number").val();
		var sd = $('#edit_startTime').datebox('getValue');
		var ed = $('#edit_endTime').datebox('getValue');
		if (paidMode == '次数') {
			if (number != null || number != undefined || number != '') {
				return true;
			} else {
				alert("计费模式为次数，次数不可以为空！")
			}
		} else if (paidMode == '天数') {
			if (ed > sd) {
				return true;
			} else {
				alert("开始时间不能大于结束时间！")
			}
		} else if (paidMode == '次数+天数') {
			if ((number != null || number != undefined || number != '')
					&& ed > sd) {
				return true;
			} else {
				alert("计费模式为次数+时间，次数和时间内容都不可以为空！")
			}
		}else if(paidMode == '储存空间'){
			if ((number != null || number != undefined || number != '')
					&& ed > sd) {
				return true;
			} else {
				alert("计费模式为储存空间，储存空间内容都不可以为空！")
			}
		}else if(paidMode == '储存空间+天数'){
			if ((number != null || number != undefined || number != '')
					&& ed > sd) {
				return true;
			} else {
				alert("计费模式为储存空间+天数，储存空间和天数内容都不可以为空！")
			}
		}
		return false;
	}

	function saveOrder() {
		var result = validateNumAndDays();
		if (!result) {
			return;
		}
		var operateUrl = "<%=path%>/order/modify.do";
		//判断是否选择行
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#order_list', window.parent.frames[iframeId].document)
				.datagrid('getSelections');
		//判断是否选择行
		
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
			success: function(result) {
				var obj = eval("("+result+")");
				alert(obj.msg);
				$('#fm').form('clear');
				jq('#order_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				jtop('#tabs',window.top.document).tabs('close',"修改订单");
			
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});
	}
</script>