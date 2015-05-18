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
	<a href="user/tologin.do">登录</a>
	<br>
	<a href="user/toregister.do">注册</a>
	<br>
	<a href="item/topublish.do">发布商品</a>
</body>
</html>
