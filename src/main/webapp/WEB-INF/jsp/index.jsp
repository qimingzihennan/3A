<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>管理后台</title>
<script type="text/javascript" src="<%=path%>/js/west.js"></script>
<script type="text/javascript">

function aa(){
	ModifyTab("修改密码","/user/toModifySelfPwd");
}
function ModifyTab(subtitle, url) {
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
	var mydate = new Date().Format("yyyy-MM-ddhh:mm:ss");
	url = url + "?id=" + mydate;
	var s = '<iframe name="'+ mydate + '" id="' + mydate + '" scrolling="auto" frameborder="0"  src="'
			+ url + '" style="width:100%;height:100%;" ></iframe>';
	return s;
}
</script>
</head>
<body>
	<div id="mainContent" class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" style="height: 78px">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<!-- 引入顶部页面 -->
				<div
					data-options="region:'north',border:false,href:'<%=path%>/main/toTop.do'"
					style="height: 78px"></div>
			</div>
		</div>

		<!-- 引入左边菜单页面 -->
		<div
			data-options="region:'west',split:true,href:'<%=path%>/main/toLeftMenu.do'"
			style="width: 240px;"></div>
		<!-- 中间内容区显示页面 -->
		<div data-options="region:'center'" border="false">
			<div id="tabs" class="easyui-tabs" fit="true" border="false">
				<div title="欢迎使用" style="overflow: hidden; text-align: center;"
					id="home">
					<h1>欢迎使用3A系统</h1>
				</div>
			</div>
		</div>

		<!-- 引入版权与友好链接页面 -->
		<div data-options="region:'south',href:'<%=path%>/main/toBottom.do'"
			style="height: 60px; text-align: center; line-height: 58px;"></div>

	</div>


</body>
</html>