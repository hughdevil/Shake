<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src=" <%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>
<title>User login</title>
</head>
<body>
	<form:form modelAttribute="user" method="post" action="login.do">     
        用户名：<form:input path="name" />
		<br />
        密 码：<form:password path="password" />
		<br />
		<input type="button" id="submit" value="Submit" />
	</form:form>

	<script type="text/javascript">
		$(function() {
			$("#submit").click(
					function() {
						var postdata = '{"name":"' + $('#name').val()
								+ '","password":"' + $('#password').val()
								+ '"}';
						alert(postdata);
						$.ajax({
							type : 'POST',
							contentType : 'application/json',
							url : 'login.do',
							processData : false,
							dataType : 'json',
							data : postdata,
							success : function(data) {
								alert('登录成功\nname : ' + data.name + '\npassword : '
										+ data.password);
								data.url="/shake/"
								location = data.url;
								
							},
							error : function() {
								alert('error...');
							}
						});
					});
		});
	</script>
</body>
</html>