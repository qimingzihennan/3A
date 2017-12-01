<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/lightbox.css">
<script type="text/javascript" src="<%=path%>/js/lightbox-plus-jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/js/jquery-easyui-1.5/themes/icon.css">	
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/js/jquery.form.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript" src="<%=path %>/js/west.js"></script>

<script type="text/javascript">
var _basePath = '<%=path %>';

// loading 框
function ajaxLoading(){ 
    $("<div class=\"datagrid-mask\" style='z-index:9999'></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body"); 
    $("<div class=\"datagrid-mask-msg\" style='z-index:9999'></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2}); 
}
//关闭覆盖层 loading
function closeAjaxLoading() {
   	$(".datagrid-mask").remove(); 
   	$(".datagrid-mask-msg").remove();  
}

function errorHandler(arguments){
	if(arguments.status == 403){
   		$.messager.alert('系统提示', '您无权访问！', 'info');
   	}else if(arguments.status == 400){
   		$.messager.alert('系统提示', '服务器内部错误！', 'info');
   	}else{
   		$.messager.alert('系统提示', '未知错误!', 'info');
   	}
}

jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
</script>
</head>
</html>


