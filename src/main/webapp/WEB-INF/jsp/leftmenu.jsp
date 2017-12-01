<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>菜单</title>
 	<script type="text/javascript">
	
	</script>

</head>
<body>

	<div id="div_menu" fit="true" class="easyui-accordion" border="false">
		<!-- 菜單列表  -->
	</div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose" data-options="iconCls:'icon-clear'">关闭</div>
		<div id="mm-tabcloseall" data-options="iconCls:'icon-clear'">全部关闭</div>
		<div id="mm-tabcloseother" data-options="iconCls:'icon-clear'">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright" data-options="iconCls:'icon-clear'">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft" data-options="iconCls:'icon-clear'">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">取消</div>
	</div>
	
	<script type="text/javascript" >
		
		$(function(){
				var _menus = ${menu};
				console.log(_menus);
			    InitLeftMenu(_menus);
			    tabClose();
			    tabCloseEven();
			
		});
		    
	
	</script>
</body>
</html>