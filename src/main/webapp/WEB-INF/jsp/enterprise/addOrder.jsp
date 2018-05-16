<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
	<style type="text/css">

		td{
			width: 100px;
		}
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
		input{
			width: 419px;
			height: 26px;
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
		<input type='hidden' name='id' id="id" value='' />
		<input type='hidden' name='Bkey' id="Bkey" value='' />
		<input type='hidden' name='comboName' id="comboName" value='' />
		<input type='hidden' name='content' id="content" value='' />
		<input type='hidden' name='cusId' id="cusId" value='${enterprise.enterpriseId }' />
		<input type='hidden' name='orderType' id="orderType" value='2' />
		<input type='hidden' name='paidMode' id='paidMode' value='' />
		<input type='hidden' name='status' id='status' value='1' />
		<table border="0">
			<tr>
				<td><label>套餐选择:</label></td>
				<td>
					<select class="col-sm-3 form-control" id="comboId"
							name="comboId">
						<option value="0">请选择套餐</option>
						<c:forEach items="${combo }" var="c">
							<option value="${c.id }">${c.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tbody id="rolesList">

			</tbody>
			<tr>
				<td><label>订单金额:</label></td>
				<td><input class="easyui" id="price"
						   name="price" ></input></td>
			</tr>

			<tr id="ci">
				<td><label>次数:</label></td>
				<td><input id="number" class="easyui"
						   name="number"></input></td>
			</tr>

			<tr id="tian">
				<td><label>天数:</label></td>
				<td><input id="days" class="easyui"
						   name="days"></input></td>
			</tr>
			<tr id="chu">
				<td><label>储存空间:</label></td>
				<td><input id="space" class="easyui"
						   name="space"></input>
					<span id="space" style="color: red;"></span></td>
			</tr>

			<tr>
				<td><label>开始时间:</label></td>
				<td><input id="edit_startTime" class="easyui-datebox"
						   name="startTime"
				></input>
				</td>
			</tr>

			<tr>
				<td><label>结束时间:</label></td>
				<td><input id="edit_endTime" class="easyui-datebox"
						   name="endTime" data-options="validType:['validateDate']"
				></input>
				</td>
			</tr>

			<tr>
				<td><label>绑定IP:</label></td>
				<td><textarea style="margin: 0px; width: 419px; height: 146px;" class="form-control" rows="5"  name="eValue" id="ip" placeholder="提示：IP与IP之间以';'相隔 例127.0.0.1;127.0.0.2"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><span id="ips" style="color: red;"></span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><span id="info" style="color: red; text-align:center;"></span></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
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

	function isValidIP()
	{
		var str = $("#ip").val();
		var ipText = $.trim(str);
		var ipNum = $("#bEFValue0").val();
		var arrInfo=ipText.split(';');
		var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		for(var i = 0;i<arrInfo.length;i++){
			if(reg.test(arrInfo[i])){
				if(ipText != null || ipText != ''){
					if(arrInfo.length==ipNum && arrInfo[arrInfo.length-1]!=''){
						$("#ips").html("");
						return true;
					}else{
						$("#ips").html("请填写套餐相对应的IP个数");
						return false;
					}
				}else{
					$("#ips").html("请填写套餐相对应的IP个数");
					return false;
				}
			}else{
				$("#ips").html("IP格式填写错误");
				return false;
			}
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


	$.extend($.fn.validatebox.defaults.rules, {
		validateDate : {
			validator : function(value) {

				var dateAStr=$('#edit_startTime').datebox('getValue');

				var arrA=dateAStr.split("-");

				var dateA=new Date(arrA[0],arrA[1],arrA[2]);

				var dateAT=dateA.getTime();

				var dateBStr=$('#edit_endTime').datebox('getValue');

				var arrB=dateBStr.split("-");

				var dateB=new Date(arrB[0],arrB[1],arrB[2]);

				var dateBT=dateB.getTime();
				if (dateAT>dateBT) {
					return false;
				}else{
					return true;
				}
			},
			message : "结束日期不能小于开始日期."
		}

	});


	//得到当前日期，开始时间
	formatterDate = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	};
	//结束时间
	formatterDates = function(date) {
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var dayNum = $("#days").val();
		var days = parseInt(day)+parseInt(dayNum)-1;
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + days;
	};

	$(function(){
		$("#fm .easyui-datebox").datebox({ disabled: false});
		$("#ip").removeAttr("disabled");
		$('#comboId').click(function(){
			$("#rolesList").html("");
			var comboId = $("#comboId").val().trim();
			if(comboId == 0){
				$("#rolesList").html("");
				$("#price").val("");
				$("#number").val("");
				$("#Bkey").val("");
				$("#comboName").val("");
				$("#content").val("");
				$("#paidMode").val("");
				$("#days").val("");
				$("#space").val("");
				$('#edit_startTime').datebox('setValue', '');
				$('#edit_endTime').datebox('setValue', '');
				$("#ci").show();
				$("#tian").show();
				$("#fm .easyui-datebox").datebox({ disabled: false});
				$("#ip").removeAttr("disabled");
			}else{
				$("#fm .easyui-datebox").datebox({ disabled: false});
				$("#ip").removeAttr("disabled");
				$.ajax({
					type: "POST",
					dataType:"JSON",
					url:"<%=path%>/enterprise/findByCid.do",
					data:{cid:comboId},
					beforeSend:ajaxLoading,
					async: true,
					error: function(request) {
						$.messager.alert('提示', request.responseJSON.reason, 'error');
					},
					success: function(data) {
						var obj = eval(data.cef);
						var a = data.combo;
						$("#price").val(a.price);
						$("#Bkey").val(a.bkey);
						$("#comboName").val(a.name);
						$("#content").val(a.content);
						$("#paidMode").val(a.paidMode);
						$("#space").val(a.space);
						var type = $("#paidMode").val();
						var Bkey = $("#Bkey").val();
						if(Bkey=="contract"){
							$("#ip").attr("disabled",true);
						}
						if(type == 1){
							$("#tian").hide();
							$("#ci").show();
							$("#chu").hide();
							$("#number").val(a.number);
							$("#fm .easyui-datebox").datebox({ disabled: true});
						}else if(type == 2){
							$("#ci").hide();
							$("#tian").show();
							$("#chu").hide();
							$("#days").val(a.days);
							$("#fm .easyui-datebox").datebox({ disabled: false});
							$('#edit_startTime').datebox('setValue', formatterDate(new Date()));
							$('#edit_endTime').datebox('setValue', formatterDates(new Date()));
						}else if(type == 3){
							$("#ci").show();
							$("#tian").show();
							$("#chu").hide();
							$("#number").val(a.number);
							$("#days").val(a.days);
							$("#fm .easyui-datebox").datebox({ disabled: false});
							$('#edit_startTime').datebox('setValue', formatterDate(new Date()));
							$('#edit_endTime').datebox('setValue', formatterDates(new Date()));
						}else if(type == 4){
							$("#ci").hide();
							$("#tian").hide();
							$("#chu").show();
							$("#number").hide();
							$("#days").hide();
							$("#space").val(a.space);
							$("#fm .easyui-datebox").datebox({ disabled: false});
							$('#edit_startTime').datebox('setValue', formatterDate(new Date()));
							$('#edit_endTime').datebox('setValue', formatterDates(new Date()));
						}else{
							$("#ci").hide();
							$("#chu").show();
							$("#tian").show();
							$("#number").hide();
							$("#days").val(a.days);
							$("#space").val(a.space);
							$("#fm .easyui-datebox").datebox({ disabled: false});
							$('#edit_startTime').datebox('setValue', formatterDate(new Date()));
							$('#edit_endTime').datebox('setValue', formatterDates(new Date()));
						}
						for (var j = 0; j < obj.length; j++) {
							$("<tr><td width='60px'>"+obj[j].bEFName+"</td><td>"+"<input "+" id='bEFValue"+j+"' value='"+obj[j].bEFValue+"' name='bEFValue'style='width: 419px')</td>").appendTo("#rolesList");
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
			}

		});

	});
	$(function()
	{
		"input",$("*").blur(function()
		{
			$("#info").text("");
		})
	});

	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d);
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
		var comboId = $("#comboId").val().trim();

		var number = $("#number").val().trim();
		var sd = $('#edit_startTime').datebox('getValue').trim();
		var ed = $('#edit_endTime').datebox('getValue').trim();
		var price = $("#price").val().trim();

		var days = $("#days").val().trim();
		var type = $("#Bkey").val().trim();
		var paidMode = $("#paidMode").val().trim();
		if(comboId == "0"){
			$("#info").text("请选择套餐！ ");
			return false;
		}else{
			if(type=="timestamp"){
				var ipNum = $("#bEFValue0").val().trim();
				var bin = $("#bEFValue1").val().trim();
				var ip = $("#ip").val().trim();
				if(ipNum == "" || ipNum == null){
					$("#info").text("IP数不能为空！");
					return false;
				}else if(ipNum == "0"){
					$("#info").text("IP必须大于0！");
					return false;
				}
				if(bin == "" || bin == null){
					$("#info").text("并发数不能为空！");
					return false;
				}else if(bin == "0"){
					$("#info").text("并发数必须大于0！");
					return false;
				}
			}

			if(price == "" || price == null){
				$("#info").text("订单金额不能为空！");
				return false;
			}else if(price == "0"){
				$("#info").text("订单金额必须大于0！");
				return false;
			}
			if(paidMode!=1 && days == "" || days == null){
				$("#info").text("天数不能为空！");
				return false;
			}else if(paidMode!=1 && days == "0"){
				$("#info").text("天数必须大于0！");
				return false;
			}
			if(paidMode!=2 && number == "" || number == null){
				$("#info").text("次数不能为空！");
				return false;
			}else if(paidMode!=2 && number == "0"){
				$("#info").text("次数必须大于0！");
				return false;
			}

			if(paidMode!=1 && sd == "" || sd == null){
				$("#info").text("请选择开始时间！");
				return false;
			}
			if(paidMode!=1 && ed == "" || ed == null){
				$("#info").text("请选择结束时间！");
				return false;
			}
			if(type=="timestamp"){
				if(ip == "" || ip == null){
					$("#info").text("请填写IP！");
					return false;
				}else{
					var arrInfo=ip.split(';');
					var reg =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
					for(var i = 0;i<arrInfo.length;i++){
						if(reg.test(arrInfo[i])){
							if(ip != null || ip != ''){
								if(arrInfo.length==ipNum && arrInfo[arrInfo.length-1]!=''){

									return true;
								}else{
									$("#info").text("请填写套餐相对应的IP个数");
									return false;
								}
							}else{
								$("#info").text("请填写套餐相对应的IP个数");
								return false;
							}
						}else{
							$("#info").text("IP格式填写错误");
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}

	//添加
	function saveOrder(){
		$("#info").text("");
		var result = validateNumAndDays();
		if (!result) {
			return;
		}
		var addurl = '<%=path %>/order/save.do';
		var iframeId = "${iframeId}";
		var jq = window.parent.frames[iframeId].jQuery;
		var rows = jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid('getSelections');
		console.log(addurl);
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
				var obj = eval("(" + data + ")");
				alert(obj.msg);
				$('#fm').form('clear');
				/* 	$('#datas_list').datagrid("reload");
				 $('#dlg').dialog('close');  */
				/*jq('#datas_list',window.parent.frames[iframeId].document).datagrid("reload");
				 jtop('#tabs',window.top.document).tabs('close',"新增订单");*/
				$('#fm').form('clear');
				jq('#enterprise_list',window.parent.frames[iframeId].document).datagrid("reload");
				var jtop = top.jQuery;
				jtop('#tabs',window.top.document).tabs('close',"添加订单");
			},
			complete:function() {
				//关闭覆盖层 loading
				$(".datagrid-mask").remove();
				$(".datagrid-mask-msg").remove();
			}
		});

	}

</script>
