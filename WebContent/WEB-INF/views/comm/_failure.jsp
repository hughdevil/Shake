<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>F</title>
</head>
<body>
	<h1>失败</h1>
	<br> ${message}
	<br>
	<h3>
		<a href="<%=request.getContextPath()%>/item/post.do">首页</a>
	</h3>
</body>
</html>