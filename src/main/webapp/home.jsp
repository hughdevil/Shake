<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>功能链接页面!</h1>
	<h1>
		<c:out value="${serverTime }"></c:out>
	</h1>
	<a href="user/login">登录</a>
</body>
</html>
