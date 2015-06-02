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
	<a href="user/register.do">注册</a>
	<br>
	<a href="user/login.do">登录</a>
	<br>
	<a href="item/publish.do">发布商品</a>
	<br>
	<a href="item/post.do">查看商品</a>
	<br>
	<a href="role/add.do">添加角色</a>
	<br>
	<a href="item/type/add.do">添加商品种类</a>
</body>
</html>
