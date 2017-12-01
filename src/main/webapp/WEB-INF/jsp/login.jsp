
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.getAttribute("page");
%>
<html>
<head lang="en">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>登陆</title>
<script src="<%=path %>/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		if (window.top != window.self) { // are you trying to put self in an iframe?
			window.top.location.href='<%=basePath%>login.do';
		}
	}
	function checkUserForm() {
		var userName = $("#username").val();
		var userPassword = $("#password").val();
		if (userName == "" || userPassword == "") {
			alert("用户名或密码不能为空");
			return false;
		}
		$("#loginFlag").val("login");
		document.loginForm.submit();
	}
	//按回车键
	
</script>
<style type="text/css">
	html{background-color:#ffffff;height:100%;}
	body{background-color:#ffffff;height:100%;margin:0px;}
	.head{height:280px;background-color:#333333;width:auto;}
	.head .welcome{font-style:italic;font-size:42px;color:#ffffff;}
	.head .title{font-family:Microsoft YaHei;font-size:22px;color:#ffffff;
		height:50px;width:240px;border:1px solid #ffffff;text-align:center;padding-top:10px;}
	.content{background-color:#ffffff;margin-top:40px;}
	.text{height:38px;width:200px;border:1px solid #d4d4d4;
		border-radius:2px;font-size:14px;
		padding-left:65px;padding-right:13px;
		padding-top:0px;
		padding-bottom:0px;
	}
	.contentUser,.contentPass{
		width:279px;
		position: relative;
		margin-top:12px;
	}
 	.uImg,.pImg{
		position: absolute;
	}
	button{font-family:Microsoft YaHei;font-size:16px;color:#ffffff;margin-top:12px;
		height:40px;width:280px;background-color:#53af32;border-radius:2px;border:0px;}
	.validate{font-family:Microsoft YaHei;font-size:12px;color:#53af32;margin-top:15px;}
	hr{height:1px;background-color:#d4d4d4;border:0px;}
	.end{font-family:"Microsoft YaHei";color:#323232;font-size:12px;height:50px;margin-top:30px;}
</style>
</head>
<body>
		<table style="width:100%;height:100%" cellpadding="0" cellspacing="0">
			<tr><td><div class="head" align="center">
				<div style="height:100px;"></div>
				<div class="welcome">Welcome</div>
				<div style="height:20px;"></div>
				<div class="title">3A系统</div>
			</div></td></tr>
			<tr><td><div class="content" align="center">
				<form id="loginForm" name="loginForm"
					action="${pageContext.servletContext.contextPath }/check.do"
					method="post">
						<div class="contentUser">
							<img class="uImg" src="<%=basePath%>img/login/backuser.png">
							<input type="text" id="username" 
								name="username" class="text" placeholder="用户名">
						</div>
						<div class="contentPass">
							<img class="pImg" src="<%=basePath%>img/login/backpass.png">
							<input type="password" id="password" 
								name="password" class="text" placeholder="密码">
								<input id="loginFlag" name="loginFlag" value="login" type="hidden" />
						</div>
						<div>
							<button onclick="checkUserForm();">进入系统</button>
						</div>
				</form>
				<div class="validate">${message}</div>
			</div></td></tr>
		<tr height=45%><td></td></tr>
		<tr><td>
			<hr>	
			<div align="center">
				<div class="end">© 版权所有 北京联合信任技术服务有限公司</div>
			</div>
		</td></tr>
	</table>
</body>
</html>
