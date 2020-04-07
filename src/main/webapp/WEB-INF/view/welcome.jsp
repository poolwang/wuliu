<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div{
width: 1700px;
height: 820px;
background: url("welcome.png") no-repeat;
background-size:100%;
overflow: hidden;
}
</style>
</head>
<body> 
<div></div>
</body>
</html>