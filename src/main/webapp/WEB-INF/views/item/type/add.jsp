<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Role add</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<form:form modelAttribute="itemtype" method="post">
		类型名：<form:input path="tname" />
		<br>
		描述：<form:input path="tdesc" />
		<br>
		<input type="submit">
	</form:form>

</body>
</html>