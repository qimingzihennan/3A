<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<style type="text/css">
</style>
</head>

<body>
	<div style="width: 100%;height: 100%;background-color: #2c2c2c;">
	<div style='float:left;margin-top:15px;margin-left:50px;'>
		<img class="signImg" src="<%=path%>/img/layout/logo.png">
		<div style="margin-left:70px;font-family:Microsoft YaHei;color:#ffffff;font-size:22px;
		margin-left:70px;margin-top:7px;float:right;">3A管理系统
		</div>
	</div>
	<div style="float:right;text-align: right;margin-top:20px;margin-right: 30px">
			<a href="#" onclick="aa()" 
							style='text-decoration: none; color: white;font-family:Microsoft YaHei;font-size:22px;'>修改密码</a>&nbsp;&nbsp;
			<a href="${pageContext.servletContext.contextPath }/logout.do"
							style='text-decoration: none; color: white;font-family:Microsoft YaHei;font-size:22px;' target="_top">退出登录</a>
		</div>
	</div>
</body>

</html>
