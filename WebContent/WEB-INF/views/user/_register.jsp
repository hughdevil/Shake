<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User register</title>
</head>
<body>
	<form action="register.do" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="uname" /></td>
			</tr>
			<tr>
				<td>密码 ：</td>
				<td><input type="password" name="psw" /></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="repsw" /></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>男<input type="radio" name="sex" value="male">女<input
					type="radio" name="sex" value="female">保密<input
					type="radio" name="sex" value="secret"></td>
			</tr>
			<tr>
				<td>手机：</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" name="mail" /></td>
			</tr>
			<tr>
				<td>地址：</td>
				<td><input type="text" name="addr" /></td>
			</tr>
			<tr>
				<td>QQ：</td>
				<td><input type="text" name="qq" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
		</table>


		<input type="submit" value="提交" />
	</form>
</body>
</html>